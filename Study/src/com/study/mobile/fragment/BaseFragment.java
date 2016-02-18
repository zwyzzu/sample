/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: BaseFragment.java
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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zwy.utils.Logger;
import com.zwy.utils.Utils;

/**
 * Author: 张维亚
 * 创建时间：2016年2月18日 下午2:21:18
 * 修改时间：2016年2月18日 下午2:21:18
 * Description: BaseFragment
 **/
public class BaseFragment extends Fragment {

	protected final String TAG = this.getClass().getSimpleName();
	private final String CYCLE_TAG = "cycle";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Logger.d(CYCLE_TAG, makeCycleMsg("onAttach"));
	}

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		Logger.d(CYCLE_TAG, makeCycleMsg("onCreate"));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
		Logger.d(CYCLE_TAG, makeCycleMsg("onCreateView"));
		return super.onCreateView(inflater, container, bundle);
	}

	@Override
	public void onActivityCreated(Bundle bundle) {
		super.onActivityCreated(bundle);
		Logger.d(CYCLE_TAG, makeCycleMsg("onActivityCreated"));
	}

	@SuppressLint("NewApi") 
	@Override
	public void onViewCreated(View view, Bundle bundle) {
		super.onViewCreated(view, bundle);
		Logger.d(CYCLE_TAG, makeCycleMsg("onViewCreated"));
	}

	@Override
	public void onStart() {
		super.onStart();
		Logger.d(CYCLE_TAG, makeCycleMsg("onStart"));
	}

	@Override
	public void onResume() {
		super.onResume();
		Logger.d(CYCLE_TAG, makeCycleMsg("onResume"));
	}

	@Override
	public void onPause() {
		super.onPause();
		Logger.d(CYCLE_TAG, makeCycleMsg("onPause"));
	}

	@Override
	public void onStop() {
		super.onStop();
		Logger.d(CYCLE_TAG, makeCycleMsg("onStop"));
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Logger.d(CYCLE_TAG, makeCycleMsg("onDestroyView"));
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Logger.d(CYCLE_TAG, makeCycleMsg("onDestroy"));
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Logger.d(CYCLE_TAG, makeCycleMsg("onDetach"));
	}

	protected String makeCycleMsg(String cycle) {
		return Utils.makeCycleMsg(this, cycle);
	}
}