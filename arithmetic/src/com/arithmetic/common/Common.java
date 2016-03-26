/*************************************************************************************
* Module Name:com.arithmetic.common
* File Name:Common.java
* Description:TODO
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
package com.arithmetic.common;

import java.util.Random;

/**
 * Author: zhangwy
 * 创建时间：2016年3月26日 上午10:30:53
 * 修改时间：2016年3月26日 上午10:30:53
 * Description: 
 **/
public class Common {

	public static final int MAX_ELEMENT = 1000;

	public static int[] random(int count) {
		int[] array = new int[count];
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			array[i] = random.nextInt(MAX_ELEMENT);
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