/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: TestFragment.java
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
package com.study.mobile.fragment;

import com.study.mobile.R;
import com.study.mobile.common.Common;
import com.study.mobile.common.CycleData;
import com.study.mobile.common.CycleData.Model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Author: 张维亚
 * 创建时间：2016年2月19日 下午5:04:14
 * 修改时间：2016年2月19日 下午5:04:14
 * Description: 
 **/
public class TestFragment extends BaseFragment {

	public static Fragment newInstance(String title, String desc) {
		Fragment fragment = new TestFragment();
		Bundle bundle = new Bundle();
		bundle.putString(Common.BUNDLE_TITLE, title);
		bundle.putString(Common.BUNDLE_DESC, desc);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
		return inflater.inflate(R.layout.fragment_test, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle bundle) {
		super.onViewCreated(view, bundle);
		refreshText();
	}

	private void refreshText() {
		TextView title = (TextView) findViewById(R.id.test_title);
		TextView desc = (TextView) findViewById(R.id.test_desc);
		Bundle bundle = getArguments();
		title.setText(bundle.getString(Common.BUNDLE_TITLE));
		desc.setText(bundle.getString(Common.BUNDLE_DESC));
	}

	@Override
	protected String makeCycleMsg(String cycle) {
		String msg = super.makeCycleMsg(cycle);
		CycleData.getInstance(Model.fragment).collection(msg);
		return msg;
	}
}