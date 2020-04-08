package com.birdwang.maps.map.model;


import com.birdwang.maps.map.RealCall;
import com.birdwang.maps.map.util.Function1;

public class CallMo<T, R> {
    public RealCall<T, R> call;
    public Function1<R> callback;

    public CallMo(RealCall<T, R> call, Function1<R> callback){
        this.call = call;
        this.callback = callback;
    }
}
