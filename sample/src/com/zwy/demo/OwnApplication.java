package com.zwy.demo;

import android.app.Application;

/**
 * Author: 张维亚
 * 创建时间：2014年6月19日 下午3:11:17
 * 修改时间：2014年6月19日 下午3:11:17
 **/
public class OwnApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

}