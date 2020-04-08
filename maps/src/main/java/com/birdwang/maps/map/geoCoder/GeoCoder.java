package com.birdwang.maps.map.geoCoder;


import com.birdwang.maps.map.Dispatcher;
import com.birdwang.maps.map.util.Function1;

public interface GeoCoder<T> {

    void convertToAddress(double lat, double lng, Function1<T> callback);

    void convertToLatLng();

    void cancel();

    Dispatcher getDispatcher();
}
