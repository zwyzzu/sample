/*************************************************************************************
 * Module Name:com.arithmetic.sort
 * File Name:Recursion.java
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
 * 创建时间：2016年3月26日 下午2:49:47 
 * 修改时间：2016年3月26日 下午2:49:47
 * Description:
 **/
public class Recursion {

	public static void main(String[] args) {
		int[] array = Common.random(10);
		Common.log(array);
		insertion(array, array.length - 1);
		Common.log(array);
	}

	public static void insertion(int[] array, int pos) {
		if (pos <= 0)
			return;
		insertion(array, (pos - 1));
		dichotomy(array, pos, 0, pos - 1);
	}

	public static void cycle(int[] array, int pos){
		int current = array[pos];
		for (int i = pos - 1; i >= 0; i--) {
			if (array[i] > current) {
				array[i + 1] = array[i];
				array[i] = current;
			} else {
				array[i + 1] = current;
				break;
			}
		}
	}

	public static void dichotomy(int[] array, int pos, int start, int end) {
		int current = array[pos];
		int middle = (start + end) / 2;
		if (start == middle) {
			if (array[end] < current) {
				moveRight(array, pos, end + 1);
			} else if (array[start] < current){
				moveRight(array, pos, end);
			} else {
				moveRight(array, pos, start);
			}
			return;
		}
		if (array[middle] > current) {
			dichotomy(array, pos, start, middle - 1);
		} else if (array[middle] < current) {
			dichotomy(array, pos, middle + 1, end);
		} else {
			moveRight(array, pos, middle);
		}
	}

	public static void moveRight(int[] array, int pos, int start){
		if (pos <= start)
			return;
		int current = array[pos];
		int length = pos - start;
		System.arraycopy(array, start, array, start + 1, length);
		array[start] = current;
		Common.log(array);
	}
}
