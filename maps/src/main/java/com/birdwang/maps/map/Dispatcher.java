package com.birdwang.maps.map;

import com.birdwang.maps.map.model.CallMo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class Dispatcher<T, R> {
    private final BlockingQueue<CallMo<T, R>> syncCalls = new LinkedBlockingQueue<>();


    public synchronized CallMo<T, R> execute(CallMo<T, R> callMo){
        CallMo runningCallMo = syncCalls.peek();
        syncCalls.offer(callMo);
        return runningCallMo != null ? null : syncCalls.peek();
    }

    public synchronized CallMo<T, R> execute(){
        return syncCalls.poll();
    }

    public synchronized void finish(CallMo<T, R> callMo){
        try {
            syncCalls.remove(callMo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelAll(){
        for (CallMo<T, R>  callMo: syncCalls){
            RealCall call = callMo.call;
            if (call != null){
                call.cancel();
            }
        }
        syncCalls.clear();
    }
}
