/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: BaseService.java
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
package com.study.mobile.service;

import com.zwy.utils.Logger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Author: 张维亚
 * 创建时间：2016年2月15日 下午3:08:34
 * 修改时间：2016年2月15日 下午3:08:34
 * Description: service基类
 **/
public class BaseService extends Service {
	protected final String TAG = this.getClass().getSimpleName();
	private final String CYCLE_TAG = "cycle";
	@Override
	public void onCreate() {
		super.onCreate();
		Logger.d(CYCLE_TAG, makeCycleMsg("onCreate"));
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		Logger.d(CYCLE_TAG, makeCycleMsg("onRebind"));
	}

	@Override
	public IBinder onBind(Intent intent) {
		Logger.d(CYCLE_TAG, makeCycleMsg("onBind"));
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Logger.d(CYCLE_TAG, makeCycleMsg("onStart"));
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Logger.d(CYCLE_TAG, makeCycleMsg("onStartCommand"));
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Logger.d(CYCLE_TAG, makeCycleMsg("onDestroy"));
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Logger.d(CYCLE_TAG, makeCycleMsg("onUnbind"));
		return super.onUnbind(intent);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		Logger.d(CYCLE_TAG, makeCycleMsg("finalize"));
	}

	protected String makeCycleMsg(String cycle) {
		return TAG + "," + cycle + "," + this.hashCode();
	}
}
