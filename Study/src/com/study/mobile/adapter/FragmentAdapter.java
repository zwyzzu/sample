/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: FragmentAdapter.java
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
package com.study.mobile.adapter;

import java.util.List;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.study.mobile.fragment.TestFragment;

/**
 * Author: 张维亚
 * 创建时间：2016年2月19日 下午5:49:42
 * 修改时间：2016年2月19日 下午5:49:42
 * Description: 
 **/
public class FragmentAdapter extends FragmentStatePagerAdapter {
	private List<FragmentData> mDatas;
	public FragmentAdapter(FragmentManager fm, List<FragmentData> datas) {
		super(fm);
		this.mDatas = datas;
	}

	@Override
	public int getCount() {
		return this.mDatas.size();
	}

	@Override
	public Fragment getItem(int position) {
		FragmentData data = mDatas.get(position);
		return TestFragment.newInstance(data.getTitle(), data.getDesc());
	}

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    	if (observer == null)
			return;

    	super.unregisterDataSetObserver(observer);
    }

	public static class FragmentData {
		public String title;
		public String desc;

		public FragmentData(String title, String desc) {
			this.title = title;
			this.desc = desc;
		}

		public String getTitle() {
			return title;
		}

		public String getDesc() {
			return desc;
		}
	}
}