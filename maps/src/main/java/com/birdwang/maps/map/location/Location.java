package com.birdwang.maps.map.location;


import com.birdwang.maps.map.util.Function1;

public interface Location<T> {

    void start();

    void stop();

    void setInterval(long interval);

    void setFastestInterval(long fastestInterval);

    void setLocationCallback(Function1<T> callback);
}
