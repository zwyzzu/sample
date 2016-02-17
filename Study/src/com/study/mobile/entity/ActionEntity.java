/*************************************************************************************
* Module Name:com.zwy.demo.entity
* File Name:StartActEntity.java
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
package com.study.mobile.entity;

import android.os.Bundle;

/**
 * Author: zhangwy
 * 创建时间：2015年9月18日 下午1:40:02
 * 修改时间：2015年9月18日 下午1:40:02
 * Description: 启动Activity的对象
 **/
public class ActionEntity extends BaseEntity {
	private static final long serialVersionUID = -5097722677183788417L;
	private String id;
	private String name;
	private String desc;
	private Class<?> clazz;
	private Bundle mBundle;

	public ActionEntity(int id, String name, String desc, Class<?> clazz) {
		this.setId(String.valueOf(id));
		this.setName(name);
		this.setDesc(desc);
		this.setClazz(clazz);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public ActionEntity putString(String key, String value){
		if (mBundle == null)
			mBundle = new Bundle();
		mBundle.putString(key, value);
		return this;
	}

	public Bundle getBundle(){
		return this.mBundle;
	}
}