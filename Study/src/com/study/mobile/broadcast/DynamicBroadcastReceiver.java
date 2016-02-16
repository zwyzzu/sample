/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: DynamicBroadcastReceiver.java
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

import com.study.mobile.R;
import com.study.mobile.activity.BroadcastActivity;
import com.study.mobile.common.Common;

import android.content.Context;
import android.content.Intent;

/**
 * Author: 张维亚
 * 创建时间：2016年2月16日 下午3:52:27
 * 修改时间：2016年2月16日 下午3:52:27
 * Description: 动态注册BroadcastReceiver
 **/
public class DynamicBroadcastReceiver extends BaseBroadcastReceiver {

	@Override
	public void onReceive(Context ctx, Intent intent) {
		Intent broadcast = new Intent(ctx, BroadcastActivity.class);
		broadcast.putExtras(intent.getExtras());
		broadcast.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		broadcast.putExtra(Common.BUNDLE_REGISTER, ctx.getString(R.string.desc_dynamic_broadcast));
		ctx.startActivity(broadcast);
	}

}