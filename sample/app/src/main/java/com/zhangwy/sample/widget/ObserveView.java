package com.zhangwy.sample.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zhangwy.util.Logger;

/**
 * Author: zhangwy(张维亚)
 * Email:  zhangweiya@yixia.com
 * 创建时间:2017/5/5 上午11:28
 * 修改时间:2017/5/5 上午11:28
 * Description:TODO
 */

public class ObserveView extends View implements View.OnFocusChangeListener, View.OnHoverListener, View.OnSystemUiVisibilityChangeListener {
    public ObserveView(Context context) {
        super(context);
        this.init();
    }

    public ObserveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public ObserveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ObserveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    private void init() {
        this.setOnFocusChangeListener(this);
        this.setOnHoverListener(this);
        this.setOnSystemUiVisibilityChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Logger.d("this.id=" + this.getId() + "; View.id=" + v.getId() + ";hasFocus=" + hasFocus);
    }

    @Override
    public boolean onHover(View v, MotionEvent event) {
        Logger.d("this.id=" + this.getId() + "; View.id=" + v.getId() + ";MotionEvent=" + event.getAction());
        return false;
    }

    @Override
    public void onSystemUiVisibilityChange(int visibility) {
        Logger.d("this.id=" + this.getId() + ";visibility=" + visibility);
    }
}
