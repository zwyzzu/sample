package com.zhangwy.sample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import com.zhangwy.util.Logger;

/**
 * Created by 张维亚(zhangwy) on 2016/12/22 下午4:33.
 * Updated by zhangwy on 2016/12/22 下午4:33.
 * Description
 */
public class SHorizontalScrollView extends HorizontalScrollView{
    public SHorizontalScrollView(Context context) {
        super(context);
    }

    public SHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean req = super.dispatchTouchEvent(ev);
        Logger.d("SHorizontalScrollView.dispatchTouchEvent:" + req + ";event.action" + ev.getAction());
        return req;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean req = super.onInterceptTouchEvent(ev);
        Logger.d("SHorizontalScrollView.onInterceptTouchEvent:" + req + ";event.action" + ev.getAction());
        return req;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean req = super.onTouchEvent(ev);
        Logger.d("SHorizontalScrollView.onTouchEvent:" + req + ";event.action" + ev.getAction());
        return req;
    }
}
