package com.birdwang.maps.map.geoCoder;

import android.content.Context;

import com.birdwang.maps.map.Dispatcher;
import com.birdwang.maps.map.model.CallMo;
import com.birdwang.maps.map.model.GeoCode;
import com.birdwang.maps.map.util.ApiKeyHandler;
import com.birdwang.maps.map.util.Function1;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class GoogleGeoCoder implements GeoCoder<GeoCode> {
    private String apiKey;
    private OkHttpClient okHttpClient;
    private Dispatcher<Call, GeoCode> dispatcher = new Dispatcher<>();

    public GoogleGeoCoder(Context context) {
        apiKey = ApiKeyHandler.getApiKey(context);
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }

    @Override
    public void convertToAddress(double lat, double lng, Function1<GeoCode> callback) {
        Call call = okHttpClient.newCall(buildReverseRequest(lat, lng));
        CallMo<Call, GeoCode> curCallMo = new CallMo<>(new GoogleGeoRealCall(call, dispatcher), callback);
        CallMo<Call, GeoCode>  pendingCallMo = dispatcher.execute(curCallMo);
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

    private Request buildReverseRequest(double lat, double lng) {
        StringBuilder url = new StringBuilder();
        url.append("https://maps.googleapis.com/maps/api/geocode/json?key=");
        url.append(apiKey);
        url.append("&latlng=");
        url.append(lat);
        url.append(",");
        url.append(lng);
        return new Request.Builder()
                .method("GET", null)
                .url(url.toString())
                .build();
    }

    @Override
    public Dispatcher<Call, GeoCode> getDispatcher() {
        return dispatcher;
    }
}
