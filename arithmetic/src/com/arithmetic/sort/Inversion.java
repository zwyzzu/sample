/*************************************************************************************
* Module Name:com.arithmetic.sort
* File Name:Inversion.java
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

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;


/**
 * Author: zhangwy
 * 创建时间：2016年3月26日 下午5:17:35
 * 修改时间：2016年3月26日 下午5:17:35
 * Description: 求逆序对
 **/
public class Inversion {

	public static void main(String[] args) {
		int[] array = { 2, 3, 8, 6, 1 };
		List<Pair<Integer, Integer>> list = inversion(array);
		log(list);
	}

	public static List<Pair<Integer, Integer>> inversion(int[] array) {
		ArrayList<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					list.add(new Pair<Integer, Integer>(i, j));
				}
			}
		}
		return list;
	}

	public static void log(List<Pair<Integer, Integer>> list){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				buffer.append('{');
			} else {
				buffer.append(',');
			}
			Pair<Integer, Integer> pair = list.get(i);
			buffer.append("<" + pair.getKey() + ", " + pair.getValue() + ">");
		}
		buffer.append('}');
		System.out.println(buffer.toString());
	}
}
