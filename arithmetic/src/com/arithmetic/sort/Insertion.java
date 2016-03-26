/*************************************************************************************
 * Module Name: 具体模块见相应注释
 * File Name: Insertion.java
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
 * 创建时间：2016年3月17日 上午11:29:46
 * 修改时间：2016年3月17日 上午11:29:46
 * Description: 
 **/
public class Insertion extends Base{

	public static void main(String[] args) {

		int[] array = random(400);
		log(array);
		int[] ascending = ascending(array);
		log(ascending);
		int[] descending = descending(array);
		log(descending);
		insertion(8);
	}

	public static void insertion(int count){
		for (int i = 0; i < count; i++) {
			int[] array = random((int)Math.pow(10, i));
//			log(array);
			long time1 = System.currentTimeMillis();
			ascending(array);
			long time2 = System.currentTimeMillis();
			descending(array);
			long time3 = System.currentTimeMillis();
			System.out.println("ascending.time:" + (time2 - time1) + "	,	descending.time:" + (time3 - time2));
			System.out.println();
		}
	}

	/**
	 * 升序
	 * @param src
	 * @return
	 */
	public static int[] ascending(int[] src) {
		if (src == null || src.length <= 0)
			return src;
		int[] desc = new int[src.length];
		desc[0] = src[0];
		for (int i = 1; i < desc.length; i++) {
			int current = src[i];
			int j = i - 1;
			while (j >= 0 && desc[j] > current) {
				desc[j+1] = desc[j];
				--j;
			}
			desc[j + 1] = current;
		}
		return desc;
	}

	/**
	 * 降序
	 * @param src
	 * @return
	 */
	public static int[] descending(int[] src) {
		if (src == null || src.length <= 0)
			return src;
		int[] desc = new int[src.length];
		desc[0] = src[0];
		for (int i = 1; i < desc.length; i++) {
			int current = src[i];
			int j = i - 1;
			while (j >= 0 && desc[j] < current) {
				desc[j + 1] = desc[j];
				--j;
			}
			desc[j + 1] = current;
		}
		return desc;
	}

	public static int[] random(int count) {
		int max = count * 100;
		int[] array = new int[count];
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			array[i] = random.nextInt(max);
		}
		return array;
	}

}