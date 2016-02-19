/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: FragmentActivity.java
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

import java.util.ArrayList;

import com.study.mobile.R;
import com.study.mobile.adapter.FragmentAdapter;
import com.study.mobile.adapter.FragmentAdapter.FragmentData;
import com.study.mobile.common.CycleData;
import com.study.mobile.common.CycleData.Cycle;
import com.study.mobile.common.CycleData.Model;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

/**
 * Author: 张维亚
 * 创建时间：2016年2月19日 下午4:42:39
 * 修改时间：2016年2月19日 下午4:42:39
 * Description: fragment 调研
 **/
public class FragmentActivity extends BaseActivity implements Cycle{

	private ViewPager mPager;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_fragment);
		CycleData.getInstance(Model.fragment).register(this);
		FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), getFragments());
		mPager = (ViewPager) findViewById(R.id.fragment_viewpager);
		mPager.setAdapter(adapter);
	}

	private ArrayList<FragmentData> getFragments() {
		ArrayList<FragmentData> list = new ArrayList<FragmentData>();
		list.add(new FragmentData(getString(R.string.name_testfragment, "Ⅰ"), getString(R.string.desc_testfragment, "Ⅰ")));
		list.add(new FragmentData(getString(R.string.name_testfragment, "Ⅱ"), getString(R.string.desc_testfragment, "Ⅱ")));
		list.add(new FragmentData(getString(R.string.name_testfragment, "Ⅲ"), getString(R.string.desc_testfragment, "Ⅲ")));
		list.add(new FragmentData(getString(R.string.name_testfragment, "Ⅳ"), getString(R.string.desc_testfragment, "Ⅳ")));
		list.add(new FragmentData(getString(R.string.name_testfragment, "Ⅴ"), getString(R.string.desc_testfragment, "Ⅴ")));
		return list;
	}

	@Override
	public void cycle(String data) {
		TextView view = (TextView) findViewById(R.id.fragment_data);
		view.setText(data);
	}
}