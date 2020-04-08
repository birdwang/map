package com.birdwang.maps.map.search;

import android.support.annotation.NonNull;

import com.birdwang.maps.map.Dispatcher;
import com.birdwang.maps.map.Disposable;
import com.birdwang.maps.map.MainThread;
import com.birdwang.maps.map.RealCall;
import com.birdwang.maps.map.model.CallMo;
import com.birdwang.maps.map.model.SearchMo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GoogleSearchRealCall implements RealCall<Call, SearchMo> {
    private Dispatcher<Call, SearchMo> dispatcher;
    private Call call;
    private final List<Disposable> disposables = Collections.synchronizedList(new ArrayList<>());

    GoogleSearchRealCall(@NonNull Call call, @NonNull Dispatcher<Call, SearchMo> dispatcher) {
        this.dispatcher = dispatcher;
        this.call = call;
    }

    @Override
    public void execute(CallMo<Call, SearchMo> callMo) {
        Schedulers.io().scheduleDirect(() -> {
            try {
                Response response = call.execute();
                ResponseBody body = response.body();
                if (body != null){
                    String json = body.string();
                    SearchMo searchMo = new Gson().fromJson(json, SearchMo.class);
                    Disposable disposable = MainThread.schedule(() -> callMo.callback.invoke(searchMo));
                    disposables.add(disposable);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                dispatcher.finish(callMo);
                CallMo<Call, SearchMo> pendingCallMo = dispatcher.execute();
                if (pendingCallMo != null) {
                    pendingCallMo.call.execute(pendingCallMo);
                }
            }
        });
    }

    @Override
    public void cancel() {
        if (call != null) {
            call.cancel();
        }
        for (Disposable disposable: disposables){
            disposable.dispose();
        }
        disposables.clear();
    }
}
