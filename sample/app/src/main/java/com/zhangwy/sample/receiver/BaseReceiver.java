package com.zhangwy.sample.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zhangwy.util.Logger;

/**
 * CreateTime 2021/6/1 14:56
 * Author zhangwy
 * desc:
 *
 * -------------------------------------------------------------------------------------------------
 * use:
 *
 **/
public class BaseReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d("BaseReceiver.action:" + intent.getAction());
    }
}
