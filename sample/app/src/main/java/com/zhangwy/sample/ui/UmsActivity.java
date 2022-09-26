package com.zhangwy.sample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.autohome.ums.UmsAgent;
import com.zhangwy.sample.R;
import com.zhangwy.util.Device;

public class UmsActivity extends BaseActivity implements View.OnClickListener {

    private TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ums);
        this.status = this.findViewById(R.id.umsStatus);
        this.findViewById(R.id.umsDestroy).setOnClickListener(this);
        this.findViewById(R.id.umsInitialization).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.umsInitialization:
                UmsAgent.initSDK(this.getApplicationContext());
                status.setText(getString(R.string.desc_ums_status, "是"));
                break;
            case R.id.umsDestroy:
                Device.Dev.getDeviceID(this);
                break;
        }
    }
}