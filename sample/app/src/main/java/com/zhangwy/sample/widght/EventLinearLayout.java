package com.zhangwy.sample.widght;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.zwy.utils.Logger;

/**
 * Created by zhangwy on 2017/2/23.
 * 调试事件分发机制
 */

public class EventLinearLayout extends LinearLayout {

    private EventMethodValue methodValue;
    private String alias;

    public EventLinearLayout(Context context) {
        super(context);
    }

    public EventLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    private String getAlias() {
        if (TextUtils.isEmpty(this.alias)) {
            this.alias = String.valueOf(this.hashCode());
        }
        return this.alias;
    }

    public void setEventMethodValue(EventMethodValue methodValue) {
        this.methodValue = methodValue;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        EventValue value = this.methodValue == null ? EventValue.VSUPER : ev.getAction() == MotionEvent.ACTION_MOVE ? this.methodValue.moveDispatchValue() : this.methodValue.dispatchValue();
        Logger.d(this.getAlias() + "    " + value.name() + "   " + "    dispatchTouchEvent  " + getEventAction(ev.getAction()));
        switch (value) {
            case VTRUE:
                return true;
            case VFALSE:
                return false;
            case VSUPER:
            default:
                return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        EventValue value = this.methodValue == null ? EventValue.VSUPER : e.getAction() == MotionEvent.ACTION_MOVE ? this.methodValue.moveInterceptValue() : this.methodValue.interceptValue();
        Logger.d(this.getAlias() + "    " + value.name() + "   " + "    onInterceptTouchEvent   " + getEventAction(e.getAction()));
        switch (value) {
            case VTRUE:
                return true;
            case VFALSE:
                return false;
            case VSUPER:
            default:
                return super.onInterceptTouchEvent(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        EventValue value = this.methodValue == null ? EventValue.VSUPER : e.getAction() == MotionEvent.ACTION_MOVE ? this.methodValue.moveTouchEventValue() : this.methodValue.touchEventValue();
        Logger.d(this.getAlias() + "    " + value.name() + "   " + "    onTouchEvent    " + getEventAction(e.getAction()));
        switch (value) {
            case VTRUE:
                return true;
            case VFALSE:
                return false;
            case VSUPER:
            default:
                return super.onTouchEvent(e);
        }
    }

    public static class EventMethodValue {

        public static EventMethodValue newInstance(EventValue dispatch, EventValue intercept, EventValue touch) {
            return new EventMethodValue(dispatch, intercept, touch);
        }

        public static EventMethodValue newInstance(EventValue dispatch, EventValue intercept, EventValue touch, EventValue moveDispatch, EventValue moveIntercept, EventValue moveTouch) {
            return new EventMethodValue(dispatch, intercept, touch, moveDispatch, moveIntercept, moveTouch);
        }

        private final EventValue dispatch;
        private final EventValue intercept;
        private final EventValue touch;
        private final EventValue moveDispatch;
        private final EventValue moveIntercept;
        private final EventValue moveTouch;

        EventMethodValue(EventValue dispatch, EventValue intercept, EventValue touch) {
            this(dispatch, intercept, touch, EventValue.UNKNOWN, EventValue.UNKNOWN, EventValue.UNKNOWN);
        }

        EventMethodValue(EventValue dispatch, EventValue intercept, EventValue touch, EventValue moveDispatch, EventValue moveIntercept, EventValue moveTouch) {
            this.dispatch = dispatch;
            this.intercept = intercept;
            this.touch = touch;
            this.moveDispatch = moveDispatch;
            this.moveIntercept = moveIntercept;
            this.moveTouch = moveTouch;
        }

        EventValue dispatchValue() {
            return dispatch;
        }

        EventValue moveDispatchValue() {
            if (this.moveDispatch == EventValue.UNKNOWN) {
                return this.dispatch;
            }
            return this.moveDispatch;
        }

        EventValue interceptValue() {
            return intercept;
        }

        EventValue moveInterceptValue() {
            if (this.moveIntercept == EventValue.UNKNOWN) {
                return this.intercept;
            }
            return moveIntercept;
        }

        EventValue touchEventValue() {
            return touch;
        }

        EventValue moveTouchEventValue() {
            if (this.moveTouch == EventValue.UNKNOWN) {
                return this.touch;
            }
            return moveTouch;
        }
    }

    public enum EventValue {
        VTRUE, VFALSE, VSUPER, UNKNOWN
    }

    private String getEventAction(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP";
            default:
                return "UNKNOWN";
        }
    }
}
