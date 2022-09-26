package com.zhangwy.sample.ui;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.zhangwy.sample.R;
import com.zhangwy.sample.receiver.BaseReceiver;
import com.zhangwy.sample.receiver.*;

public class BroadcastReceiverActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        this.findViewById(R.id.broadcastReceiverSend).setOnClickListener(this);
        this.findViewById(R.id.broadcastReceiverRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.broadcastReceiverSend: {
                Intent intent = new Intent();
                intent.setAction(BaseReceiver.COMMON_RECEIVER);
                intent.putExtra("name", "奥特曼");
                this.sendOrderedBroadcast(intent, null);
                break;
            }
            case R.id.broadcastReceiverRegister: {
                this.registerReceiver(new Ordered1BroadcastReceiver(), 1);
                this.registerReceiver(new Ordered3BroadcastReceiver(), 1000);
                this.registerReceiver(new Ordered5BroadcastReceiver(), 2147483647);
                this.registerReceiver(new Ordered2BroadcastReceiver(), 100);
                this.registerReceiver(new Ordered4BroadcastReceiver(), 32768);
                break;
            }
            case R.id.broadcastReceiverUnRegister: {
                break;
            }
        }
    }

    private void registerReceiver(BroadcastReceiver receiver, int priority) {
        IntentFilter filter = new IntentFilter(BaseReceiver.COMMON_RECEIVER);
        filter.setPriority(priority);
        filter.addAction("android.intent.action.PHONE_STATE");
        filter.addAction(Intent.ACTION_CALL);
        filter.addAction(Intent.ACTION_DIAL);
        filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        filter.addAction(Intent.ACTION_ANSWER);
        this.registerReceiver(receiver, filter);
    }
}