/*************************************************************************************
* Module Name:com.arithmetic.sort
* File Name:Bubbling.java
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
package com.arithmetic.sort;

import com.arithmetic.common.Common;

/**
 * Author: zhangwy
 * 创建时间：2016年3月26日 下午4:23:02
 * 修改时间：2016年3月26日 下午4:23:02
 * Description: 
 **/
public class Bubbling {

	public static void main(String[] args) {
		int[] array = {714,710,637,526,511,316,265,235,182,52};
		Common.log(array);
		bubbling(array);
		Common.log(array);
	}

	public static void bubbling(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = array.length - 1; j >= i + 1; j--) {
				if (array[j] < array[j - 1]) {
					int current = array[j];
					array[j] = array[j - 1];
					array[j - 1] = current;
				}
				System.out.print("i:" + i + ",j:" + j);
				Common.log(array);
			}
		}
	}
}
