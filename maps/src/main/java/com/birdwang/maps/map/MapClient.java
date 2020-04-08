package com.birdwang.maps.map;

import android.content.Context;
import android.location.Location;

import com.birdwang.maps.map.geoCoder.GeoCoder;
import com.birdwang.maps.map.geoCoder.GoogleGeoCoder;
import com.birdwang.maps.map.geoCoder.NativeGeoCoder;
import com.birdwang.maps.map.location.GoogleLocation;
import com.birdwang.maps.map.model.DetailsMo;
import com.birdwang.maps.map.model.GeoCode;
import com.birdwang.maps.map.model.SearchMo;
import com.birdwang.maps.map.search.GoogleSearch;
import com.birdwang.maps.map.search.Search;
import com.birdwang.maps.map.util.Function1;

public class MapClient {
    private GeoCoder geoCoder;
    private Context context;
    private com.birdwang.maps.map.location.Location<Location> locationClient;
    private Search searchClient;
    private Dispatcher dispatcher;
    private GeoCoder curGeoCoder;

    private MapClient(Builder builder){
        this.dispatcher = builder.dispatcher != null ? builder.dispatcher : new Dispatcher();
        this.context = builder.context;
        this.geoCoder = builder.geoCoder != null ? builder.geoCoder : new NativeGeoCoder(context);
        this.locationClient = builder.locationClient != null ? builder.locationClient : new GoogleLocation(context);
        this.searchClient = builder.searchClient != null ? builder.searchClient : new GoogleSearch(context);
        this.curGeoCoder = builder.curGeoCoder != null ? builder.curGeoCoder : new GoogleGeoCoder(context);
    }

    <T> void convertToAddress(double lat, double lng, Function1<T> callback){
        geoCoder.convertToAddress(lat, lng, callback);
    }

    void retrieveAddressByLatlng(double lat, double lng, Function1<GeoCode> callback){
        curGeoCoder.convertToAddress(lat, lng, callback);
    }

    void stopLocation(){
        locationClient.stop();
    }

    void startLocation(Function1<android.location.Location> callback){
        locationClient.setLocationCallback(callback);
        locationClient.start();
    }

    void setInterval(long interval) {
        locationClient.setInterval(interval);
    }

    void searchAddressList(String keyword, Function1<SearchMo> callback){
        searchClient.searchAddressList(keyword, callback);
    }

    void cancel(){
        dispatcher.cancelAll();
        if (geoCoder != null){
            geoCoder.getDispatcher().cancelAll();
        }
    }

    void fetchLatLng(String placeId, Function1<DetailsMo> callback) {
        searchClient.fetchLatLng(placeId, callback);
    }

    public static class Builder {
        private GeoCoder geoCoder;
        private Context context;
        private com.birdwang.maps.map.location.Location locationClient;
        private Search searchClient;
        private Dispatcher dispatcher;
        private GeoCoder curGeoCoder;

        Builder(Context context){
            this.context = context;
        }

        Builder geoCoder(GeoCoder geoCoder){
            this.geoCoder = geoCoder;
            return this;
        }

        MapClient build(){
            return new MapClient(this);
        }

        Builder location(com.birdwang.maps.map.location.Location locationClient){
            this.locationClient = locationClient;
            return this;
        }

        Builder search(Search searchClient){
            this.searchClient = searchClient;
            return this;
        }

        Builder dispatcher(Dispatcher dispatcher){
            this.dispatcher = dispatcher;
            return this;
        }

        Builder curGeoCoder(GeoCoder curGeoCoder){
            this.curGeoCoder = curGeoCoder;
            return this;
        }
    }
}
