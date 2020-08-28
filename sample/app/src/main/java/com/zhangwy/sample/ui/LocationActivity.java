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
        String builder = "Latitude：" + location.getLatitude() + '\n' +
                "Longitude：" + location.getLongitude() + '\n' +
                "Altitude：" + location.getAltitude() + '\n' +
                "Altitude：" + location.hasAltitude() + '\n' +
                "Time：" + location.getTime() + '\n' +
                "Provider：" + location.getProvider() + '\n' +
                "Speed：" + location.getSpeed() + '\n' +
                "Speed：" + location.hasSpeed() + '\n' +
                "Bearing：" + location.getBearing() + '\n' +
                "Bearing：" + location.hasBearing() + '\n' +
                "Accuracy：" + location.getAccuracy() + '\n' +
                "Accuracy：" + location.hasAccuracy() + '\n';
        return builder;
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