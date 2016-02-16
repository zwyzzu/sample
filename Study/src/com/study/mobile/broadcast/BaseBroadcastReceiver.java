/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: BaseBroadcastReceiver.java
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
package com.study.mobile.broadcast;

import com.zwy.utils.Logger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * Author: 张维亚
 * 创建时间：2016年2月16日 下午2:48:04
 * 修改时间：2016年2月16日 下午2:48:04
 * Description: BroadcastReceiver基类
 **/
public abstract class BaseBroadcastReceiver extends BroadcastReceiver {

	protected final String TAG = this.getClass().getSimpleName();

	@Override
	public IBinder peekService(Context myContext, Intent service) {
		Logger.d(TAG, "peekService");
		return super.peekService(myContext, service);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		Logger.d(TAG, "finalize");
	}

}
