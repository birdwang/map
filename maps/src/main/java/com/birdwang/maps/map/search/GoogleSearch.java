package com.birdwang.maps.map.search;

import android.content.Context;

import com.birdwang.maps.map.Dispatcher;
import com.birdwang.maps.map.model.CallMo;
import com.birdwang.maps.map.model.DetailsMo;
import com.birdwang.maps.map.model.SearchMo;
import com.birdwang.maps.map.util.ApiKeyHandler;
import com.birdwang.maps.map.util.Function1;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GoogleSearch implements Search<SearchMo, DetailsMo> {
    private String apiKey;
    private OkHttpClient okHttpClient;
    private Dispatcher<Call, SearchMo> dispatcher = new Dispatcher<>();

    public GoogleSearch(Context context){
        apiKey = ApiKeyHandler.getApiKey(context);
        okHttpClient = new OkHttpClient();
    }

    @Override
    public void searchAddressList(String keyword, Function1<SearchMo> callback) {
        Call call = okHttpClient.newCall(buildSearchRequest(keyword));
        CallMo<Call, SearchMo> curCallMo = new CallMo<>(new GoogleSearchRealCall(call, dispatcher), callback);
        CallMo<Call, SearchMo>  pendingCallMo = dispatcher.execute(curCallMo);
        if (pendingCallMo != null && pendingCallMo.call != null) {
            pendingCallMo.call.execute(pendingCallMo);
        }
    }

    private Request buildSearchRequest(String keyword){
        StringBuilder url = new StringBuilder();
        url.append("https://maps.googleapis.com/maps/api/place/textsearch/json?key=");
        url.append(apiKey);
        url.append("&query=");
        url.append(keyword);
        url.append("+拱墅区");
        url.append("&language=cn");
        return new Request.Builder()
                .method("GET", null)
                .url(url.toString())
                .build();
    }

    @Override
    public void fetchLatLng(String placeId, Function1<DetailsMo> callback) {

    }

    private Request buildDetailsRequest(String placeId){
        StringBuilder url = new StringBuilder();
        url.append("https://maps.googleapis.com/maps/api/place/details/json?key=");
        url.append(apiKey);
        url.append("&place_id=");
        url.append(placeId);
        return new Request.Builder()
                .method("GET", null)
                .url(url.toString())
                .build();
    }

    @Override
    public void cancel() {
        dispatcher.cancelAll();
    }
}
