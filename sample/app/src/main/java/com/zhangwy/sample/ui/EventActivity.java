package com.zhangwy.sample.ui;

import android.os.Bundle;
import android.view.View;

import com.zhangwy.sample.R;
import com.zhangwy.sample.widght.EventLinearLayout;
import com.zhangwy.sample.widght.EventLinearLayout.EventMethodValue;
import com.zhangwy.sample.widght.EventLinearLayout.EventValue;
import com.zhangwy.utils.Logger;

/**
 * Created by zhangwy on 2017/2/23.
 * 调试事件分发机制
 */

public class EventActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        this.setEventMethodValue(R.id.event_level0, "level0", EventMethodValue.newInstance(EventValue.VSUPER, EventValue.VSUPER, EventValue.VSUPER));
        this.setEventMethodValue(R.id.event_level1, "level1", EventMethodValue.newInstance(EventValue.VSUPER, EventValue.VSUPER, EventValue.VSUPER));
        this.setEventMethodValue(R.id.event_level2, "level2", EventMethodValue.newInstance(EventValue.VSUPER, EventValue.VSUPER, EventValue.VSUPER, EventValue.UNKNOWN, EventValue.VTRUE, EventValue.UNKNOWN));
        this.setEventMethodValue(R.id.event_level3, "level3", EventMethodValue.newInstance(EventValue.VSUPER, EventValue.VSUPER, EventValue.VSUPER));
        this.setEventMethodValue(R.id.event_level4, "level4", EventMethodValue.newInstance(EventValue.VSUPER, EventValue.VSUPER, EventValue.VTRUE));
        this.setEventMethodValue(R.id.event_level5, "level5", EventMethodValue.newInstance(EventValue.VSUPER, EventValue.VSUPER, EventValue.VSUPER));
        this.setEventMethodValue(R.id.event_level6, "level6", EventMethodValue.newInstance(EventValue.VSUPER, EventValue.VTRUE, EventValue.VFALSE));
        this.setEventMethodValue(R.id.event_level7, "level7", EventMethodValue.newInstance(EventValue.VSUPER, EventValue.VSUPER, EventValue.VSUPER));
        this.setEventMethodValue(R.id.event_level8, "level8", EventMethodValue.newInstance(EventValue.VFALSE, EventValue.VSUPER, EventValue.VSUPER));
        this.setEventMethodValue(R.id.event_level9, "level9", EventMethodValue.newInstance(EventValue.VSUPER, EventValue.VSUPER, EventValue.VSUPER));
        final View view = findViewById(R.id.event_level99);
        view.post(new Runnable() {
            @Override
            public void run() {
                Logger.d("Height:" + view.getHeight());
                Logger.d("MeasuredHeight:" + view.getMeasuredHeight());
                Logger.d("Background.IntrinsicHeight:" + view.getBackground().getIntrinsicHeight());
                Logger.d("Background.MinimumHeight:" + view.getBackground().getMinimumHeight());
            }
        });
    }

    private void setEventMethodValue(int id, String alias, EventLinearLayout.EventMethodValue methodValue) {
        View view = findViewById(id);
        if (view instanceof EventLinearLayout) {
            EventLinearLayout layout = (EventLinearLayout) view;
            layout.setEventMethodValue(methodValue);
            layout.setAlias(alias);
        }
    }
}
