package com.example.shalom.androidlifecyclescodelab;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;


public class BoundLocationManager {
    public static final String LOG_TAG = BoundLocationListener.class.getSimpleName();

    /*Static initialization of BoundLocationListener*/
    public static void bindLocationListenerIn(LifecycleOwner lifecycleOwner,
                                              LocationListener locationListener, Context context) {
        new BoundLocationListener(lifecycleOwner, locationListener, context);
    }

    @SuppressWarnings("MissingPermission")
    static class BoundLocationListener implements LifecycleObserver {
        private final Context context;
        private LocationManager locationManager;
        private final LocationListener locationListener;

        public BoundLocationListener(LifecycleOwner lifecycleOwner,
                                     LocationListener locationListener, Context context) {
            this.context = context;
            this.locationListener = locationListener;

            /*LifecycleObserver enables usage of OnLifecycleEvent annotations*/
            lifecycleOwner.getLifecycle().addObserver(this);
        }

        /**
         * Adds a location listener
         *
         * OnLifecycleEvent annotation means the method will be called when the
         * {@code Lifecycle.Event.ON_RESUME} occurs
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void addLocationListener() {
            // Note: Use the Fused Location Provider from Google Play Services instead.
            // https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderApi

            /*Initialize LocationManager*/
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            /*Add LocationListener*/
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                    0, locationListener);

            Log.d(LOG_TAG, "Listener added");

            /*Get location from LocationManager and supply it*/
            Location lastLocation = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) locationListener.onLocationChanged(lastLocation);
        }

        /**
         * Removes the location listener
         *
         * OnLifecycleEvent annotation means the method will be called when the
         * {@code Lifecycle.Event.ON_PAUSE} occurs
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void removeLocationListener() {
            if (locationManager == null) return;

            /*Remove LocationListener, and set LocationManager to null*/
            locationManager.removeUpdates(locationListener);
            locationManager = null;
            Log.d(LOG_TAG, "Listener removed");
        }
    }
}
