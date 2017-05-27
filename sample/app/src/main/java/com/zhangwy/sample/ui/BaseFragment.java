/*************************************************************************************
* Module Name:com.zwy.demo.fragment
* File Name:BaseFragment.java
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
package com.zwy.demo.fragment;

import com.zhangwy.util.Logger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: zhangwy
 * 创建时间：2015年9月17日 下午4:53:29
 * 修改时间：2015年9月17日 下午4:53:29
 * Description: fragment的基类
 **/
public class BaseFragment extends Fragment {

	protected final String TAG = this.getClass().getSimpleName();
	protected final String LAYOUT = "layout";
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Logger.d(TAG, "onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logger.d(TAG, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Logger.d(TAG, "onCreateView");
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	protected View onCreateView(LayoutInflater inflater, ViewGroup container, int layout){
		return inflater.inflate(layout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Logger.d(TAG, "onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		Logger.d(TAG, "onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Logger.d(TAG, "onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Logger.d(TAG, "onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Logger.d(TAG, "onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Logger.d(TAG, "onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Logger.d(TAG, "onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Logger.d(TAG, "onDetach");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		Logger.d(TAG, "finalize." + this.hashCode());
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Logger.d(TAG, "onViewCreated");
	}

	@Override
	public void registerForContextMenu(View view) {
		super.registerForContextMenu(view);
		Logger.d(TAG, "registerForContextMenu");
	}

	@Override
	public void unregisterForContextMenu(View view) {
		super.unregisterForContextMenu(view);
		Logger.d(TAG, "unregisterForContextMenu");
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		Logger.d(TAG, "setUserVisibleHint");
	}
}