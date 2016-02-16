/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: BroadcastActivity.java
 * Author: 张维亚
 * All Rights Reserved
 * 所有版权保护
 * Copyright 2014, Funshion Online Technologies Ltd.
 * 版权 2014，北京风行在线技术有限公司
 * This is UNPUBLISHED PROPRIETARY SOURCE CODE of Funshion Online Technologies Ltd.;
 * the contents of this file may not be disclosed to third parties, copied or
 * duplicated in any form, in whole or in part, without the prior written
 * permission of Funshion Online Technologies Ltd.
 * 这是北京风行在线技术有限公司未公开的私有源代码。本文件及相关内容未经风行在线技术有
 * 限公司事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
 ***************************************************************************************/
package com.study.mobile.activity;

import com.study.mobile.R;
import com.study.mobile.broadcast.DynamicBroadcastReceiver;
import com.study.mobile.common.Common;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Author: 张维亚
 * 创建时间：2016年2月16日 下午3:27:59
 * 修改时间：2016年2月16日 下午3:27:59
 * Description: 
 **/
public class BroadcastActivity extends BaseActivity implements View.OnClickListener{

	private DynamicBroadcastReceiver mDynamicBroadcastReceiv = null;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_broadcast);
		findViewById(R.id.broadcast_register).setOnClickListener(this);
		findViewById(R.id.broadcast_senddynamic).setOnClickListener(this);
		findViewById(R.id.broadcast_sendstatic).setOnClickListener(this);
		showMsg(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		showMsg(intent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.broadcast_register:
			registerBroadcast();
			break;
		case R.id.broadcast_senddynamic:
			sendDynamic();
			break;
		case R.id.broadcast_sendstatic:
			sendStatic();
			break;
		default:
			break;
		}
	}

	private void registerBroadcast() {
		mDynamicBroadcastReceiv = new DynamicBroadcastReceiver();
		IntentFilter dynamic = new IntentFilter();
		dynamic.addAction(Common.BROADCASTDYNAMIC);
        registerReceiver(mDynamicBroadcastReceiv, dynamic, Common.PMSSENDSTUDY, null);
	}

	private void sendDynamic() {
		Intent intent = new Intent();
		intent.setAction(Common.BROADCASTDYNAMIC);
		intent.putExtra(Common.BUNDLE_MSG, "接收动态注册广播成功！");
		sendBroadcast(intent, Common.PMSSTUDYRECEIVER);
	}

	private void sendStatic() {
		Intent intent = new Intent();
		intent.setAction(Common.BROADCASTSTATIC);
		intent.putExtra(Common.BUNDLE_MSG, "接收静态注册广播成功！");
		sendBroadcast(intent, Common.PMSSTUDYRECEIVER);//具有Common.PMSSTUDYRECEIVER权限的app才能接收该广播
	}

	private void showMsg(Intent intent){
		if (intent == null || intent.getExtras() == null)
			return;
		TextView view = (TextView)findViewById(R.id.broadcast_text);
		Bundle bundle = intent.getExtras();
		StringBuffer buffer = new StringBuffer();
		if (!TextUtils.isEmpty(view.getText())) {
			buffer.append(view.getText());
			buffer.append('\n');
		}
		if (bundle.containsKey(Common.BUNDLE_REGISTER)){
			buffer.append(bundle.getString(Common.BUNDLE_REGISTER));
			buffer.append('\n');
		}
		if (bundle.containsKey(Common.BUNDLE_REGISTER)){
			buffer.append(bundle.getString(Common.BUNDLE_MSG));
		}
		view.setText(buffer.toString());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mDynamicBroadcastReceiv != null)
			unregisterReceiver(mDynamicBroadcastReceiv);
	}
}