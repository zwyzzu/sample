package com.zhangwy.sample.ui;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.zhangwy.sample.R;
import com.zhangwy.util.Logger;

import java.util.List;

@SuppressLint("MissingPermission")
public class LocationActivity extends BaseActivity implements View.OnClickListener {
    private LocationManager manager;
    private TextView requestMessage;
    private TextView requestProviders;
    private TextView locationChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_location);
        this.findViewById(R.id.locationRequest).setOnClickListener(this);
        this.manager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        this.requestMessage = this.findViewById(R.id.locationRequestMessage);
        this.requestProviders = this.findViewById(R.id.locationRequestProviders);
        this.locationChanged = this.findViewById(R.id.locationRequestOnLocationChanged);
        this.initLocation();
    }

    private void initLocation() {
        if (this.manager == null) {
            return;
        }
        List<String> providers = this.manager.getAllProviders();
        this.requestProviders.setText(JSONArray.toJSONString(providers));
        for (String provider : providers) {
            manager.requestLocationUpdates(provider, 100, 0, new RLocationListener());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.locationRequest) {
            if (this.manager != null) {
                Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                String locationString = location2String(location);
                Logger.d(locationString);
                this.requestMessage.setText(locationString);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private String location2String(Location location) {
        StringBuilder builder = new StringBuilder();
        builder.append("Latitude：").append(location.getLatitude()).append('\n');
        builder.append("Longitude：").append(location.getLongitude()).append('\n');
        builder.append("Altitude：").append(location.getAltitude()).append('\n');
        builder.append("Altitude：").append(location.hasAltitude()).append('\n');
        builder.append("Speed：").append(location.getSpeed()).append('\n');
        builder.append("Speed：").append(location.hasSpeed()).append('\n');
        builder.append("Bearing：").append(location.getBearing()).append('\n');
        builder.append("Bearing：").append(location.hasBearing()).append('\n');
        builder.append("Accuracy：").append(location.getAccuracy()).append('\n');
        builder.append("Accuracy：").append(location.hasAccuracy()).append('\n');
        builder.append("Provider：").append(location.getProvider()).append('\n');
        builder.append("Time：").append(location.getTime()).append('\n');
        return builder.toString();
    }

    private class RLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            String locationString = location2String(location);
            Logger.d(locationString);
            locationChanged.setText(locationString);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    }
}