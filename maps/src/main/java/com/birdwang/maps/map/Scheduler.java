package com.birdwang.maps.map;

public interface Scheduler {

    Disposable schedule(Runnable r);
}
