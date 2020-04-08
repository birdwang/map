package com.birdwang.maps.map.geoCoder;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.birdwang.maps.map.Dispatcher;
import com.birdwang.maps.map.Disposable;
import com.birdwang.maps.map.MainThread;
import com.birdwang.maps.map.RealCall;
import com.birdwang.maps.map.model.CallMo;
import com.birdwang.maps.map.util.Function1;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.reactivex.schedulers.Schedulers;

public class NativeGeoRealCall implements RealCall<Object, Address> {
    private Dispatcher<Object, Address> dispatcher;
    private WeakReference<Context> context;
    private LocationManager locationM;
    private MyLocationListener locationListener;

    NativeGeoRealCall(@NonNull Dispatcher<Object, Address> dispatcher, WeakReference<Context> context) {
        this.dispatcher = dispatcher;
        this.context = context;
    }

    @Override
    public void execute(CallMo<Object, Address> callMo) {
        try {
            MainThread.schedule(() -> startGeoCode(callMo.callback));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dispatcher.finish(callMo);
            CallMo<Object, Address> pendingCallMo = dispatcher.execute();
            if (pendingCallMo != null) {
                pendingCallMo.call.execute(pendingCallMo);
            }
        }
    }

    private void startGeoCode(Function1<Address> callback) {
        if (context == null || context.get() == null) {
            return;
        }
        Context realContext = context.get();
        //如果系统版本号在23及其以上则检查权限
        if (Build.VERSION.SDK_INT >= 23 &&
                (ContextCompat.checkSelfPermission(realContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(realContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            return;
        }
        cancel();
        //获取LocationManager对象
        if (locationM == null) {
            locationM = (LocationManager) realContext.getSystemService(Context.LOCATION_SERVICE);
        }
        //实例化MyLocationListener
        locationListener = new MyLocationListener(realContext, callback);
        //配置Criteria耗电低
        Criteria cri = new Criteria();
        cri.setAccuracy(Criteria.ACCURACY_FINE); // 设置准确而非粗糙的精度
        cri.setPowerRequirement(Criteria.POWER_LOW); // 设置相对省电而非耗电，一般高耗电量会换来更精确的位置信息
        cri.setAltitudeRequired(false); // 不需要提供海拔信息
        cri.setSpeedRequired(false); // 不需要速度信息
        cri.setCostAllowed(false); // 不能产生费用
        // 获取可用的provider,第二个参数标识 provider是否可用.
        String locationProvider = locationM.getBestProvider(cri, true);
        if (locationProvider == null) {
            List<String> providers = locationM.getProviders(true);
            if (providers != null && providers.size() > 0) {
                locationProvider = providers.get(0);
            }
        }
        // 都不支持则直接返回
        if (TextUtils.isEmpty(locationProvider)) {
            return;
        }
        Location location = locationM.getLastKnownLocation(locationProvider);
        if (location != null) {
            locationListener.onLocationChanged(location);
        } else {
            locationM.requestLocationUpdates("network", 60 * 1000, 0, locationListener);
        }
    }

    @Override
    public void cancel() {
        if (locationM != null && locationListener != null) {
            locationM.removeUpdates(locationListener);
            locationListener.dispose();
        }
    }

    private static class MyLocationListener implements LocationListener {
        private Context mContext;
        private Function1<Address> callback;
        private final List<Disposable> disposables = Collections.synchronizedList(new ArrayList<>());

        MyLocationListener(Context context, Function1<Address> callback) {
            this.mContext = context;
            this.callback = callback;
        }

        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Schedulers.io().scheduleDirect(() -> {
                    disposables.add(parseLocationAddress(mContext, location, callback));
                });
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        public void dispose(){
            for (Disposable disposable: disposables){
                disposable.dispose();
            }
            disposables.clear();
        }
    }

    private static Disposable parseLocationAddress(Context context, Location location, Function1<Address> callback) {
        Geocoder geoCoder = new Geocoder(context, Locale.ENGLISH);
        Disposable disposable = null;
        try {
            List<Address> addresses = geoCoder.getFromLocation(
                    location.getLatitude(), location.getLongitude(),
                    1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                if (address != null) {
                    disposable = MainThread.schedule(() -> callback.invoke(address));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return disposable;
    }
}
