/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: Merge.java
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

import com.arithmetic.common.Common;

/**
 * Author: 张维亚
 * 创建时间：2016年3月25日 下午6:38:17
 * 修改时间：2016年3月25日 下午6:38:17
 * Description: 
 **/
public class Merge{

	public static void main(String[] args) {

		int[] array = Common.random(80);
		Common.log(array);
		mergeSort(array, 0, array.length - 1);
		Common.log(array);
	}

	public static void mergeSort(int[] array, int start, int end) {
		if (start >= end)
			return;
		int middle = (start + end) / 2;
		mergeSort(array, start, middle);
		mergeSort(array, middle + 1, end);
		merge2(array, start, middle, end);
	}

	public static int[] left = null;
	public static int[] right = null;
	public static void merge(int[] array, int start, int middle, int end) {
		int ll = middle - start + 1;
		int rl = end - middle;

		System.out.println("start:" + start + ",middle:" + middle + ",end:" + end + ",ll:" + ll + ",rl:" + rl);
		if (left == null)
			left = new int[array.length];
		if (right == null)
			right = new int[array.length];

		System.arraycopy(array, start, left, 0, ll);
		System.arraycopy(array, middle + 1, right, 0, rl);

		left[ll] = Common.MAX_ELEMENT;
		right[rl] = Common.MAX_ELEMENT;

		Common.log(left);
		Common.log(right);

		int lp = 0;
		int rp = 0;
		for (int i = start; i <= end; i++) {
			if (left[lp] <= right[rp]) {
				array[i] = left[lp];
				lp++;
			} else {
				array[i] = right[rp];
				rp++;
			}
		}
		Common.log(array);
	}

	public static void merge2(int[] array, int start, int middle, int end) {
		int ll = middle - start + 1;
		int rl = end - middle;

		System.out.println("start:" + start + ",middle:" + middle + ",end:" + end + ",ll:" + ll + ",rl:" + rl);
		int[] left = new int[ll];
		int[] right = new int[rl];

		System.arraycopy(array, start, left, 0, ll);
		System.arraycopy(array, middle + 1, right, 0, rl);
//
//		Common.log(left);
//		Common.log(right);

		int lp = 0;
		int rp = 0;
		for (int i = start; i <= end; i++) {
			if (left[lp] <= right[rp]) {
				array[i] = left[lp++];
				if (lp >= ll) {
//					System.out.print("lp:");
//					Common.log(array);
					System.arraycopy(right, rp, array, i+1, rl - rp);
					break;
				}
			} else {
				array[i] = right[rp++];
				if (rp >= rl) {
//					System.out.print("rp:");
//					Common.log(array);
					System.arraycopy(left, lp, array, i+1, ll - lp);
					break;
				}
			}
		}
		Common.log(array);
	}
	
}
