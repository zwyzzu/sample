package com.zhangwy.sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhangwy.sample.R;

public class SuspensionBarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspension_bar);
        this.findViewById(R.id.suspensionBarSingle).setOnClickListener(this);
        this.findViewById(R.id.suspensionBarMulti).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.suspensionBarSingle:
                this.startActivity(new Intent(this, SuspensionBarSingleActivity.class));
                break;
            case R.id.suspensionBarMulti:
                this.startActivity(new Intent(this, SuspensionBarMultiActivity.class));
                break;
        }
    }
}
