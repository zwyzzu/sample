package com.zhangwy.sample.ui;

import android.os.Bundle;
import android.view.View;

import com.zhangwy.sample.R;
import com.zhangwy.widget.PowerView;

/**
 * Created by zhangwy on 2018/1/29 下午3:25.
 * Updated by zhangwy on 2018/1/29 下午3:25.
 * Description
 */

public class WidgetActivity extends BaseActivity implements View.OnClickListener {
    private PowerView powerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_widget);
        this.powerView = (PowerView) findViewById(R.id.widgetPower);
        this.powerView.setOnClickListener(this);
        this.powerView.setPower(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.widgetPower:
                this.powerView.setPower(this.powerView.power+=5);
                break;
        }
    }
}
