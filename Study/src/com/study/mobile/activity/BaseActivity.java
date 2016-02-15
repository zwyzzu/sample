/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: BaseActivity.java
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

import com.zwy.utils.Logger;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Author: 张维亚
 * 创建时间：2016年2月14日 下午2:52:28
 * 修改时间：2016年2月14日 下午2:52:28
 * Description: Activity基类
 **/
public class BaseActivity extends ActionBarActivity {

	protected final String TAG = this.getClass().getSimpleName();
	private final String CYCLE_TAG = "cycle";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logger.d(CYCLE_TAG, makeCycleMsg("onCreate"));
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Logger.d(CYCLE_TAG, makeCycleMsg("onRestart"));
	}

	@Override
	protected void onStart() {
		super.onStart();
		Logger.d(CYCLE_TAG, makeCycleMsg("onStart"));
	}

	@Override
	protected void onResume() {
		super.onResume();
		Logger.d(CYCLE_TAG, makeCycleMsg("onResume"));
	}

	@Override
	protected void onPause() {
		super.onPause();
		Logger.d(CYCLE_TAG, makeCycleMsg("onPause"));
	}

	@Override
	protected void onStop() {
		super.onStop();
		Logger.d(CYCLE_TAG, makeCycleMsg("onStop"));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Logger.d(CYCLE_TAG, makeCycleMsg("onDestroy"));
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