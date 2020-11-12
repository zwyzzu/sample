package com.zhangwy.sample.ui;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.zhangwy.sample.R;
import com.zhangwy.util.Logger;

public class PermissionActivity extends BaseActivity {

    private int maxVolume, currentVolume;
    private final int INTERVAL_VOLUME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        this.refreshVolume();
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
}