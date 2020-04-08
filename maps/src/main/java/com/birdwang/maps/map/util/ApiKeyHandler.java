package com.birdwang.maps.map.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

public class ApiKeyHandler {
    private static final String KEY = "com.google.android.geo.API_KEY";
    private static String apiKey;

    public static String getApiKey(Context context){
        if (TextUtils.isEmpty(apiKey)){
            ApplicationInfo appInfo;
            try {
                appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                Bundle metaData = appInfo.metaData;
                if (metaData != null) {
                    apiKey = metaData.getString(KEY);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return apiKey;
    }
}
