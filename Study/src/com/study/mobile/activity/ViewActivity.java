/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: ViewActivity.java
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

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;

import com.study.mobile.R;
import com.zwy.utils.Logger;

/**
 * Author: 张维亚
 * 创建时间：2016年2月29日 下午5:05:10
 * 修改时间：2016年2月29日 下午5:05:10
 * Description: 
 **/
public class ViewActivity extends BaseActivity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_view);
		findViewById(R.id.view_button).setOnClickListener(this);
	}

	private void globalLayout(){
		Logger.d(TAG, "btnRunnable");
		int[] location = new int[2];
		findViewById(R.id.view_button).getLocationOnScreen(location);
		int butX = location[0];
		int butY = location[1];
		View view = findViewById(R.id.view_hint);
		view.getLocationOnScreen(location);
		int hintX = location[0];
		int hintY = location[1];
		int paddingLeft = butX - hintX - findViewById(R.id.view_hint_img).getWidth() + findViewById(R.id.view_button).getWidth() - 20;
		int paddingtop = butY - hintY - findViewById(R.id.view_hint_img).getHeight();
		view.setPadding(paddingLeft, paddingtop, view.getPaddingRight(), view.getPaddingBottom());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_button:
			((Button)v).setText(R.string.button_view_end);
			findViewById(R.id.view_button).getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onGlobalLayout() {
					findViewById(R.id.view_button).getViewTreeObserver().removeGlobalOnLayoutListener(this);
					globalLayout();
				}
			});
			break;

		default:
			break;
		}
	}
}