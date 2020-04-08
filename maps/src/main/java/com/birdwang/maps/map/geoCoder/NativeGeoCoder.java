package com.birdwang.maps.map.geoCoder;


import android.content.Context;
import android.location.Address;


import com.birdwang.maps.map.Dispatcher;
import com.birdwang.maps.map.model.CallMo;
import com.birdwang.maps.map.util.Function1;

import java.lang.ref.WeakReference;

public class NativeGeoCoder implements GeoCoder<Address> {
    private Dispatcher<Object, Address> dispatcher = new Dispatcher<>();
    private WeakReference<Context> context;

    public NativeGeoCoder(Context context) {
        this.context = new WeakReference<>(context);
    }

    @Override
    public void convertToAddress(double lat, double lng, Function1<Address> callback) {
        CallMo<Object, Address> curCallMo = new CallMo<>(new NativeGeoRealCall(dispatcher, context), callback);
        CallMo<Object, Address>  pendingCallMo = dispatcher.execute(curCallMo);
        if (pendingCallMo != null && pendingCallMo.call != null) {
            pendingCallMo.call.execute(pendingCallMo);
        }
    }

    @Override
    public void convertToLatLng() {

    }

    @Override
    public void cancel() {
        dispatcher.cancelAll();
    }

    @Override
    public Dispatcher<Object, Address> getDispatcher() {
        return dispatcher;
    }
}
