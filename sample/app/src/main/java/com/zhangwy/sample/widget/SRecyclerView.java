package com.zhangwy.sample.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zhangwy.util.Logger;

/**
 * Created by 张维亚(zhangwy) on 2016/12/22 下午4:36.
 * Updated by zhangwy on 2016/12/22 下午4:36.
 * Description TODO
 */
public class SRecyclerView extends RecyclerView {
    public SRecyclerView(Context ctx) {
        super(ctx);
    }

    public SRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean req = super.dispatchTouchEvent(ev);
        Logger.d("SRecyclerView.dispatchTouchEvent:" + req + ";event.action" + ev.getAction());
        return req;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean req = super.onInterceptTouchEvent(ev);
        Logger.d("SRecyclerView.onInterceptTouchEvent:" + req + ";event.action" + ev.getAction());
        return req;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean req = super.onTouchEvent(ev);
        Logger.d("SRecyclerView.onTouchEvent:" + req + ";event.action" + ev.getAction());
        return req;
    }
}
