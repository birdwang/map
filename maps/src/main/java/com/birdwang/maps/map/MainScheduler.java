package com.birdwang.maps.map;

import android.os.Handler;
import android.os.Message;

public class MainScheduler implements Scheduler {
    private Handler handler;

    MainScheduler(Handler handler){
        this.handler = handler;
    }

    @Override
    public Disposable schedule(Runnable r) {
        if (r == null) throw new NullPointerException("runnable == null");
        DisposableRunnable disposable = new DisposableRunnable(r, handler);
        Message message = Message.obtain(handler, disposable);
        handler.sendMessage(message);
        return disposable;
    }

    private static final class DisposableRunnable implements Runnable, Disposable {
        private Runnable delegate;
        private Handler handler;

        DisposableRunnable(Runnable runnable, Handler handler){
            this.delegate = runnable;
            this.handler = handler;
        }

        @Override
        public void run() {
            try {
                delegate.run();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void dispose() {
            handler.removeCallbacks(this);
        }
    }
}
