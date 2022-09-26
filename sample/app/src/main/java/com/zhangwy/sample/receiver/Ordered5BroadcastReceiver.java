package com.zhangwy.sample.receiver;

import android.content.Context;
import android.content.Intent;

import com.zhangwy.util.Logger;

import java.util.Objects;

/**
 * CreateTime 2022/9/24 14:38
 * Author zhangwy
 * desc:
 * 有序广播
 * -------------------------------------------------------------------------------------------------
 * use:
 * 接受权限2147483647
 **/
public class Ordered5BroadcastReceiver extends BaseReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (Objects.equals(intent.getAction(), COMMON_RECEIVER)) {
            Logger.d(String.format("广播类%s", this.getClass().getSimpleName()));
        }
    }
}
