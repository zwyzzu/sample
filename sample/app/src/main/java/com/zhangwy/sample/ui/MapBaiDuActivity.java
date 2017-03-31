package com.zhangwy.sample.ui;

import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.zhangwy.sample.R;
import com.zhangwy.utils.Logger;

/**
 * Created by 张维亚(zhangwy) on 2016/12/27 下午3:47.
 * Updated by zhangwy on 2016/12/27 下午3:47.
 * Description 百度地图界面
 */
public class MapBaiDuActivity extends BaseActivity implements BaiduMap.OnMarkerDragListener, BaiduMap.OnMapClickListener {
    private MapView mapView;
    private LocationClient locationClient;
    private LocationListener locationListener = new LocationListener();
    private LatLng latLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        mapView = (MapView) findViewById(R.id.mapView);

        mapView.getMap().setOnMarkerDragListener(this);
        mapView.getMap().setOnMapClickListener(this);
        myLocation();
    }

    private void myLocation() {
        mapView.getMap().setMyLocationEnabled(true);
        locationClient = new LocationClient(this);
        locationClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        locationClient.setLocOption(option);
        locationClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.getMap().setMyLocationEnabled(false);
        locationClient.unRegisterLocationListener(locationListener);
        locationClient.stop();
        mapView.onDestroy();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        latLng = marker.getPosition();
        Logger.d(TAG, "latitude:" + latLng.latitude + ";longitude" + latLng.longitude);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        addMarkPoint(latLng);
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    public class LocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (locationClient != null) {
                locationClient.unRegisterLocationListener(this);
                locationClient.stop();
                mapView.getMap().setMyLocationEnabled(false);
            }
            if (location == null || mapView == null) {
                return;
            }
            // 此处设置开发者获取到的方向信息，顺时针0-360
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mapView.getMap().setMyLocationData(locData);

            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mapView.getMap().animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }

    private void addMarkPoint(LatLng latLng) {
        this.latLng = latLng;
        // 此处设置开发者获取到的方向信息，顺时针0-360
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(0)
                .direction(100).latitude(latLng.latitude)
                .longitude(latLng.longitude).build();
        mapView.getMap().setMyLocationData(locData);
    }
}
