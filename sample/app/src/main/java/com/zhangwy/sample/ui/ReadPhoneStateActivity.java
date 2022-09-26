package com.zhangwy.sample.ui;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhangwy.sample.R;
import com.zhangwy.util.Logger;
import com.zhangwy.util.VSPermission;

public class ReadPhoneStateActivity extends AppCompatActivity implements View.OnClickListener {

    private View viewImei;
    private View viewImsi;
    private View viewImei2;
    private View viewImsi2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_phone_state);
        this.viewImei = this.findViewById(R.id.readPhoneStateImei);
        this.viewImsi = this.findViewById(R.id.readPhoneStateImsi);
        this.viewImei2 = this.findViewById(R.id.readPhoneStateImei2);
        this.viewImsi2 = this.findViewById(R.id.readPhoneStateImsi2);
        this.viewImei.setOnClickListener(this);
        this.viewImsi.setOnClickListener(this);
        this.viewImei2.setOnClickListener(this);
        this.viewImsi2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.readPhoneStateImei:
            case R.id.readPhoneStateImsi:
                if (VSPermission.hasPermission(this, Manifest.permission.READ_PHONE_STATE)) {
                    Logger.d(String.format("has permission for %s", Manifest.permission.READ_PHONE_STATE));
                }
                break;
            case R.id.readPhoneStateImei2:
            case R.id.readPhoneStateImsi2:
                break;
        }
    }
}