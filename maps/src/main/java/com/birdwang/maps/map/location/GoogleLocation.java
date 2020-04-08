package com.birdwang.maps.map.location;

import android.content.Context;
import android.location.Location;
import android.os.Looper;

import com.birdwang.maps.map.util.Function1;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class GoogleLocation implements com.birdwang.maps.map.location.Location<Location> {
    private FusedLocationProviderClient mLocationClient;
    private long interval;
    private long fastestInterval;
    private Function1<Location> callback;
    private final long DEFAULT_INTERVAL = 10000;
    private final long DEFAULT_FASTEST_INTERVAL = 5000;
    private final LocationRequest locationRequest = LocationRequest.create();
    private LocationCallback locationCallback;

    public GoogleLocation(Context context){
        mLocationClient = LocationServices.getFusedLocationProviderClient(context);
        interval = DEFAULT_INTERVAL;
        fastestInterval = DEFAULT_FASTEST_INTERVAL;
    }

    @Override
    public void start() {
        removeLocationUpdates();
        locationRequest.setInterval(interval);
        locationRequest.setFastestInterval(fastestInterval);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                android.location.Location location = locationResult.getLastLocation();
                if (callback != null){
                    callback.invoke(location);
                }
            }
        };
        mLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
    }

    @Override
    public void stop() {
        removeLocationUpdates();
    }

    private void removeLocationUpdates(){
        if (locationCallback != null){
            mLocationClient.removeLocationUpdates(locationCallback);
            locationCallback = null;
        }
    }

    @Override
    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public void setFastestInterval(long fastestInterval) {
        this.fastestInterval = fastestInterval;
    }

    @Override
    public void setLocationCallback(Function1<android.location.Location> callback) {
        this.callback = callback;
    }
}
