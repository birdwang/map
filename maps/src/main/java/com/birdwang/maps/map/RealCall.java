package com.birdwang.maps.map;


import com.birdwang.maps.map.model.CallMo;

public interface RealCall<T, R> {
    void execute(CallMo<T, R> callMo);

    void cancel();
}
