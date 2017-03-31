package com.zhangwy.sample.ui;

import android.app.Activity;
import android.os.Bundle;
import com.zhangwy.utils.Logger;
/**
 * Created by 张维亚(zhangwy) on 2016/12/20 下午4:46.
 * Updated by zhangwy on 2016/12/20 下午4:46.
 * Description
 */
public class BaseActivity extends Activity{

    protected String TAG = this.getClass().getSimpleName();
    private boolean destroyed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        destroyed = true;
        super.onDestroy();
        Logger.d(TAG, "onDestroy");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Logger.d(TAG, "finalize." + this.hashCode());
    }

    protected boolean destroyed() {
        return destroyed;
    }
}
