/*************************************************************************************
* Module Name:com.zwy.demo.activity
* File Name:BaseActivity.java
* Author: 张维亚
* Copyright 2007-, Funshion Online Technologies Ltd.
* All Rights Reserved
* 版权 2007-，北京风行在线技术有限公司
* 所有版权保护
* This is UNPUBLISHED PROPRIETARY SOURCE CODE of Funshion Online Technologies Ltd.;
* the contents of this file may not be disclosed to third parties, copied or
* duplicated in any form, in whole or in part, without the prior written
* permission of Funshion Online Technologies Ltd.
* 这是北京风行在线技术有限公司未公开的私有源代码。本文件及相关内容未经风行在线技术有
* 限公司事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
package com.zwy.demo.activity;

import com.zwy.utils.Logger;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Author: zhangwy
 * 创建时间：2015年9月17日 下午3:29:13
 * 修改时间：2015年9月17日 下午3:29:13
 * Description: Activity基类
 **/
public class BaseActivity extends ActionBarActivity {

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