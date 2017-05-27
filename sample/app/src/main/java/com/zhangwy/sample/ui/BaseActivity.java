package com.zhangwy.sample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhangwy.util.Logger;
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

    public void showMsg(int msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_LONG).show();
    }

    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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
