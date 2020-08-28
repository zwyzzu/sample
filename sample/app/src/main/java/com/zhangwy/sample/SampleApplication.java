package com.zhangwy.sample;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.os.Bundle;
import android.text.TextUtils;

import com.zhangwy.util.Logger;

public class SampleApplication extends Application implements Application.ActivityLifecycleCallbacks {
    protected String TAG = this.getClass().getSimpleName();
    private Activity activity;
    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        this.log(activity, "onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        this.log(activity, "onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        this.log(activity, "onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        this.log(activity, "onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        this.log(activity, "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        this.log(activity, "onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        this.log(activity, "onActivityDestroyed");
    }

    private void log(Activity activity, String method) {
        String message = "%s(%s, %s)";
        switch (method) {
            case "onActivityCreated":
            case "onActivityStarted":
            case "onActivityResumed":
                this.activity = activity;
                break;
            case "onActivityPaused":
                this.activity = null;
                break;
        }
        Logger.d(TAG, String.format(message, method, activity.getPackageName(), activity.getClass().getSimpleName()));
        if (this.activity == null) {
            Logger.d(TAG, "background Activity");
        } else {
            Logger.d(TAG, "foreground Activity");
        }

//        ActivityManager manager = (ActivityManager)activity.getSystemService(ACTIVITY_SERVICE);
//        if (manager != null && manager.getRunningTasks(1) != null && manager.getRunningTasks(1).size() != 0) {
//            ComponentName componentName = manager.getRunningTasks(1).get(0).topActivity;
//            String currentPackageName = componentName.getPackageName();
//            if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(activity.getPackageName())) {
//                Logger.d(TAG, "foreground Activity1");
//                return;
//            }
//        }
//        Logger.d(TAG, "background Activity1");
    }
}