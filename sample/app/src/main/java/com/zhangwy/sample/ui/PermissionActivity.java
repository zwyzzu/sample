package com.zhangwy.sample.ui;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PermissionActivity extends BaseActivity {

    private int maxVolume, currentVolume;
    private final int INTERVAL_VOLUME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        this.refreshVolume();
        this.refreshSensor();
        this.refreshCall();
        this.refreshPrivileged();
    }

    private void refreshVolume() {
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && manager != null && manager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            this.startActivity(intent);
        }
        final AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager == null) {
            return;
        }
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.RINGER_MODE_NORMAL);
        currentVolume = audioManager.getStreamVolume(AudioManager.RINGER_MODE_NORMAL);
        final TextView textView = this.findViewById(R.id.permissionAccessNotificationPolicyVolumeText);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.permissionAccessNotificationPolicyVolumeIncrease: {
                        Logger.d("音量+");
                        this.volume(true);
                        break;
                    }
                    case R.id.permissionAccessNotificationPolicyVolumeReduce: {
                        Logger.d("音量-");
                        this.volume(false);
                        break;
                    }
                    case R.id.permissionAccessNotificationPolicyVolumeRefresh: {
                        break;
                    }
                }
                this.refreshVolume();
            }

            private void refreshVolume() {
                currentVolume = audioManager.getStreamVolume(AudioManager.RINGER_MODE_NORMAL);
                textView.setText(getString(R.string.notification_manager_volume_value, currentVolume, maxVolume));
            }

            private void volume(boolean increase) {
                if (increase) {
                    currentVolume += INTERVAL_VOLUME;
                } else {
                    currentVolume -= INTERVAL_VOLUME;
                }
                audioManager.setStreamVolume(AudioManager.RINGER_MODE_NORMAL, currentVolume, 0);
            }
        };

        this.findViewById(R.id.permissionAccessNotificationPolicyVolumeIncrease).setOnClickListener(listener);
        this.findViewById(R.id.permissionAccessNotificationPolicyVolumeReduce).setOnClickListener(listener);
        this.findViewById(R.id.permissionAccessNotificationPolicyVolumeRefresh).setOnClickListener(listener);
        int ringerMode = audioManager.getRingerMode();
        this.findViewById(R.id.permissionAccessNotificationPolicyVolumeIncrease).setEnabled(!(ringerMode == AudioManager.RINGER_MODE_VIBRATE));
        this.findViewById(R.id.permissionAccessNotificationPolicyVolumeReduce).setEnabled(!(ringerMode == AudioManager.RINGER_MODE_VIBRATE));
        this.findViewById(R.id.permissionAccessNotificationPolicyVolumeRefresh).performClick();
    }

    private void refreshSensor() {
        final SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        final TextView allText = this.findViewById(R.id.permissionSensorsText);
        this.findViewById(R.id.permissionSensorsRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manager == null) {
                    return;
                }
                List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
                StringBuilder builder = new StringBuilder();
                builder.append(getString(R.string.sensor_list));
                builder.append(System.getProperty("line.separator"));
                JSONArray array = new JSONArray();
                for (Sensor sensor : sensors) {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("name", sensor.getName());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            json.put("id", sensor.getId());
                            json.put("SType", sensor.getStringType());
                        }
                        json.put("type", sensor.getType());
                        json.put("resolution", sensor.getResolution());
                        json.put("Ventor", sensor.getVendor());
                        json.put("Version", sensor.getVersion());
                        json.put("power", sensor.getPower());
                    } catch (JSONException e) {
                        Logger.e("", e);
                    }
                    array.put(json);
                }
                builder.append(array.toString());
                allText.setText(builder.toString());
            }
        });
        this.findViewById(R.id.permissionSensorsRefresh).performClick();
        final TextView typeSensor = this.findViewById(R.id.permissionSensorTypeText);
        final EditText typeInput = this.findViewById(R.id.permissionSensorTypeInput);
        this.findViewById(R.id.permissionSensorTypeRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manager == null) {
                    return;
                }
                String typeString = typeInput.getText().toString();
                Sensor sensor = manager.getDefaultSensor(Integer.parseInt(typeString));
                StringBuilder builder = new StringBuilder();
                builder.append(getString(R.string.sensor_type));
                if (sensor == null) {
                    typeSensor.setText(builder.toString());
                    return;
                }
                builder.append(System.getProperty("line.separator"));
                JSONObject json = new JSONObject();
                try {
                    json.put("name", sensor.getName());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        json.put("id", sensor.getId());
                        json.put("SType", sensor.getStringType());
                    }
                    json.put("type", sensor.getType());
                    json.put("resolution", sensor.getResolution());
                    json.put("Ventor", sensor.getVendor());
                    json.put("Version", sensor.getVersion());
                    json.put("power", sensor.getPower());
                } catch (JSONException e) {
                    Logger.e("", e);
                }
                builder.append(json.toString());
                typeSensor.setText(builder.toString());
            }
        });
    }

    private void refreshCall() {
        final EditText text = this.findViewById(R.id.permissionCallPhoneInput);
        this.findViewById(R.id.permissionCallPhoneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textString = text.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + textString));
                if (ActivityCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });
    }

    private void refreshPrivileged() {
        final EditText text = this.findViewById(R.id.permissionCallPrivilegedInput);
        this.findViewById(R.id.permissionCallPrivilegedButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textString = text.getText().toString();
                Intent intent = new Intent("android.intent.action.CALL_PRIVILEGED");
                intent.setData(Uri.parse("tel:" + textString));
                if (ActivityCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PRIVILEGED) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });
    }

    private void refreshCache() {

    }
}