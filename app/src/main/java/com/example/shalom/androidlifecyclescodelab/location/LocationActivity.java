package com.example.shalom.androidlifecyclescodelab.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shalom.androidlifecyclescodelab.R;

public class LocationActivity extends AppCompatActivity{

    private static final int REQUEST_LOCATION_PERMISSION_CODE = 1;

    private LocationListener gpsListener = new MyLocationListener();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_DENIED) {
            bindLocationListener();
        } else {
            Toast.makeText(this, "This sample requires location access",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void bindLocationListener() {
        BoundLocationManager.bindLocationListenerIn(this, gpsListener,
                getApplicationContext());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            /*Request permissions to access fine and coarse locations if they weren't granted*/
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION_PERMISSION_CODE);
        } else {
            /*Permissions were granted*/
            bindLocationListener();
        }
    }

    private class MyLocationListener implements LocationListener {
        /**
         * Changes {@code textView} text to reflect the current location
         * @param location  Where device is geographically located
         */
        @Override
        public void onLocationChanged(Location location) {
            TextView textView = (TextView) findViewById(R.id.location);
            textView.setText(location.getLatitude() + ", " + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(LocationActivity.this, "Provider enabled: " + provider,
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    }
}
