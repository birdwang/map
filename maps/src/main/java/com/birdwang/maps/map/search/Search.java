package com.birdwang.maps.map.search;


import com.birdwang.maps.map.util.Function1;

public interface Search<T, R> {

    void searchAddressList(String keyword, Function1<T> callback);

    void fetchLatLng(String placeId, Function1<R> callback);

    void cancel();
}
