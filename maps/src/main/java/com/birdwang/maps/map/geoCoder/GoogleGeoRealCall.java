package com.birdwang.maps.map.geoCoder;

import android.support.annotation.NonNull;


import com.birdwang.maps.map.Dispatcher;
import com.birdwang.maps.map.Disposable;
import com.birdwang.maps.map.MainThread;
import com.birdwang.maps.map.RealCall;
import com.birdwang.maps.map.model.CallMo;
import com.birdwang.maps.map.model.GeoCode;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GoogleGeoRealCall implements RealCall<Call, GeoCode> {
    private Dispatcher<Call, GeoCode> dispatcher;
    private Call call;
    private final List<Disposable> disposables = Collections.synchronizedList(new ArrayList<>());

    GoogleGeoRealCall(@NonNull Call call, @NonNull Dispatcher<Call, GeoCode> dispatcher) {
        this.dispatcher = dispatcher;
        this.call = call;
    }

    @Override
    public void execute(CallMo<Call, GeoCode> callMo) {
        Schedulers.io().scheduleDirect(() -> {
            try {
                Response response = call.execute();
                ResponseBody body = response.body();
                if (body != null) {
                    String json = body.string();
                    GeoCode geoCode = new Gson().fromJson(json, GeoCode.class);
                    Disposable disposable = MainThread.schedule(() -> callMo.callback.invoke(geoCode));
                    disposables.add(disposable);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                dispatcher.finish(callMo);
                CallMo<Call, GeoCode> pendingCallMo = dispatcher.execute();
                if (pendingCallMo != null) {
                    pendingCallMo.call.execute(pendingCallMo);
                }
            }
        });
    }

    @Override
    public void cancel() {
        if (call != null) {
            call.cancel();
        }
        for (Disposable disposable: disposables){
            disposable.dispose();
        }
        disposables.clear();
    }
}
