package com.birdwang.maps.map;

import android.os.Handler;
import android.os.Looper;

public class MainThread {

    public static Disposable schedule(Runnable r){
        Handler handler = new Handler(Looper.getMainLooper());
        Scheduler scheduler = new MainScheduler(handler);
        return scheduler.schedule(r);
    }
}
