/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: CycleData.java
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
package com.study.mobile.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zwy.utils.Logger;

/**
 * Author: 张维亚
 * 创建时间：2016年2月15日 下午12:42:42
 * 修改时间：2016年2月15日 下午12:42:42
 * Description: 生命周期数据
 **/
public class CycleData {
	private List<String> mCycleData = new ArrayList<String>();
	private Cycle mCycle = null;
	private static HashMap<Model, CycleData> mInstances = new HashMap<Model, CycleData>();
	public static CycleData getInstance(Model key) {
		if (mInstances.containsKey(key))
			return mInstances.get(key);
		CycleData instance = new CycleData();
		mInstances.put(key, instance);
		return instance;
	}

	public void collection(String msg) {
		mCycleData.add(msg);
		StringBuffer cycle = new StringBuffer();
		for (String string : mCycleData) {
			cycle.append(string);
			cycle.append('\n');
		}
		try {
			mCycle.cycle(cycle.toString());
		} catch (Exception e) {
			Logger.d(this.getClass().getSimpleName(), e.getMessage());
		}
	}

	public void register(Cycle cycle) {
		mCycle = cycle;
		mCycleData.clear();
	}

	public void unRegister() {
		mCycle = null;
	}

	public static interface Cycle {
		public void cycle(String data);
	}

	public static enum Model {
		activity, fragment;
	}
}