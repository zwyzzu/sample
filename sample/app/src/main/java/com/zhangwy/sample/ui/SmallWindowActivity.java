package com.zhangwy.sample.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.zhangwy.alertWindow.AlertPermission;
import com.zhangwy.alertWindow.AlertWindow;
import com.zhangwy.alertWindow.MIUI;
import com.zhangwy.sample.R;

public class SmallWindowActivity extends BaseActivity implements View.OnClickListener, AlertWindow.OnStateListener {

    private boolean showWindow = false;
    private AlertWindow alertWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_window);
        this.findViewById(R.id.smallWindowShow).setOnClickListener(this);
        this.findViewById(R.id.smallWindowClose).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.smallWindowShow:
                this.showWindow();
                break;
            case R.id.smallWindowClose:
                this.close();
                break;
        }
    }

    private void showWindow() {
        if (showWindow) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                //没有悬浮窗权限,跳转申请
                Toast.makeText(getApplicationContext(), "请开启悬浮窗权限", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            } else {
                initWindow();
            }
        } else {
            //6.0以下　只有MUI会修改权限
            if (MIUI.rom()) {
                if (AlertPermission.hasPermission(this)) {
                    initWindow();
                } else {
                    MIUI.req(this);
                }
            } else {
                initWindow();
            }
        }
    }

    private void initWindow() {
        View alertView = LayoutInflater.from(this).inflate(R.layout.view_alert_window, null);
        if (this.alertWindow != null) {
            this.alertWindow.close();
        }
        this.alertWindow = new AlertWindow(this, alertView, this);
    }

    private void close() {
        if (this.alertWindow != null) {
            this.alertWindow.close();
        }
    }

    @Override
    public void onState(AlertWindow.WindowState state) {
        this.showWindow = state == AlertWindow.WindowState.show;
    }
}