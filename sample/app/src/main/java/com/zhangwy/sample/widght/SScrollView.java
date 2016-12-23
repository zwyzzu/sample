package com.zhangwy.sample.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.zwy.utils.Logger;

/**
 * Created by 张维亚(zhangwy) on 2016/12/22 下午4:25.
 * Updated by zhangwy on 2016/12/22 下午4:25.
 * Description 自定义ScrollView
 */
public class SScrollView extends ScrollView {
    public SScrollView(Context context) {
        super(context);
    }

    public SScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean req = super.dispatchTouchEvent(ev);
        Logger.d("SScrollView.dispatchTouchEvent:" + req + ";event.action" + ev.getAction());
        return req;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean req = super.onInterceptTouchEvent(ev);
        Logger.d("SScrollView.onInterceptTouchEvent:" + req + ";event.action" + ev.getAction());
        return req;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean req = super.onTouchEvent(ev);
        Logger.d("SScrollView.onTouchEvent:" + req + ";event.action" + ev.getAction());
        return req;
    }
}
