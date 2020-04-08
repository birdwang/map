package com.birdwang.maps.map;

import android.content.Context;
import android.location.Address;

import com.birdwang.maps.map.model.DetailsMo;
import com.birdwang.maps.map.model.GeoCode;
import com.birdwang.maps.map.model.SearchMo;
import com.birdwang.maps.map.util.Function1;

public class GoogleMapClient {
    private MapClient mapClient;

    private static class Holder {
        private static final GoogleMapClient INSTANCE = new GoogleMapClient();
    }

    private GoogleMapClient() {

    }

    public static GoogleMapClient getInstance() {
        return Holder.INSTANCE;
    }

    public void init(Context context) {
        if (mapClient == null) {
            mapClient = new MapClient.Builder(context)
                    .build();
        }
    }

    /**
     * 获取当前位置对应的地址
     * @param callback
     */
    public void retrieveCurrentAddress(Function1<Address> callback) {
        if (mapClient != null) {
            mapClient.convertToAddress(0, 0, callback);
        }
    }

    /**
     * 根据经纬度查询对应的地址
     * @param lat
     * @param lng
     * @param callback
     * Geocode
     */
    public void retrieveAddressByLatlng(double lat, double lng, Function1<GeoCode> callback){
        if (mapClient != null){
            mapClient.retrieveAddressByLatlng(lat, lng, callback);
        }
    }

    public void setInterval(long interval) {
        if (mapClient != null) {
            mapClient.setInterval(interval);
        }
    }

    public void stopLocation() {
        mapClient.stopLocation();
    }

    public void release() {
        stopLocation();
        cancel();
    }

    /**
     * 定位获取经纬度
     * @param callback Location是个bean，通过get方法获取经纬度
     */
    public void startLocation(Function1<android.location.Location> callback) {
        mapClient.startLocation(callback);
    }

    public void searchAddressList(String keyword, Function1<SearchMo> callback){
        mapClient.searchAddressList(keyword, callback);
    }

    public void fetchLatLng(String placeId, Function1<DetailsMo> callback){
        mapClient.fetchLatLng(placeId, callback);
    }

    public void cancel(){
        mapClient.cancel();
    }
}
