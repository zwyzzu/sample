/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: CycleActivity.java
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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.study.mobile.R;
import com.study.mobile.common.CycleData;
import com.study.mobile.common.CycleData.Cycle;
import com.study.mobile.common.CycleData.Model;

/**
 * Author: 张维亚
 * 创建时间：2016年2月14日 下午4:07:48
 * 修改时间：2016年2月14日 下午4:07:48
 * Description: 生命周期
 **/
public class CycleActivity extends BaseActivity implements OnClickListener, Cycle{

	@Override
	protected void onCreate(Bundle bundle) {
		CycleData.getInstance(Model.ACTIVITY).register(this);
		super.onCreate(bundle);
		setContentView(R.layout.activity_cycle);
		findViewById(R.id.cycle_blend).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cycle_blend:
			startActivity(new Intent(this, CycleBlendActivity.class));
			break;

		default:
			break;
		}
	}

	@Override
	public void cycle(String data) {
		TextView view = (TextView) findViewById(R.id.cycle_data);
		view.setText(data);
	}

	@Override
	protected String makeCycleMsg(String cycle) {
		String msg = super.makeCycleMsg(cycle);
		CycleData.getInstance(Model.ACTIVITY).collection(msg);
		return msg;
	}
}