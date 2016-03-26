/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: Base.java
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
package com.arithmetic.sort;

import java.util.Random;

/**
 * Author: 张维亚
 * 创建时间：2016年3月25日 下午6:57:59
 * 修改时间：2016年3月25日 下午6:57:59
 * Description: 
 **/
public class Base {

	public static final int MAX = 1000;

	public static int[] random(int count) {
		int[] array = new int[count];
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			array[i] = random.nextInt(MAX);
		}
		return array;
	}

	public static void log(int[] array) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				buffer.append('{');
			} else {
				buffer.append(',');
			}
			buffer.append(array[i]);
		}
		buffer.append('}');
		System.out.println(buffer.toString());
	}
}
