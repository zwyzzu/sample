package com.zhangwy.sample.receiver;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.zhangwy.util.Logger;

import java.util.ArrayList;
import java.util.Objects;

/**
 * CreateTime 2022/9/24 14:38
 * Author zhangwy
 * desc:
 * 有序广播
 * -------------------------------------------------------------------------------------------------
 * use:
 * 接受权限32768
 **/
public class Ordered4BroadcastReceiver extends BaseReceiver {
    TelephonyManager telMgr;
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (Objects.equals(intent.getAction(), COMMON_RECEIVER)) {
            Logger.d(String.format("广播类%s", this.getClass().getSimpleName()));
            abortBroadcast();
        } else if (Objects.equals(intent.getAction(), "android.intent.action.PHONE_STATE")) {
            telMgr = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            Logger.d(String.format("呼入%s", this.getClass().getSimpleName()));
            switch (telMgr.getCallState()) {
                // 当电话呼入时
                case TelephonyManager.CALL_STATE_RINGING:
                    String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    Logger.d("拦截电话2:" + number);
                    //我这里是获取手机通讯录的联系人，如果是呼入电话是手机联系人就不拦截，反之你懂的，如果嫌麻烦当然也可以只设置一个号码
                    break;
                //电话被挂起
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                //手机空闲了
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
            }
//            abortBroadcast();
        }
    }
}
