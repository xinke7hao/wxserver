/************************************************************************************
 * @File name   :      PassGenerator.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月9日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2014年12月9日 上午10:41:05
 ************************************************************************************/
package com.hunantv.mbp.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 生成指定长度的随机密码
 * @author XuYanbo
 */
public class PassGenerator {

	private static final int length = 6;
	private static final char[] symbols;
	
	static {
		StringBuilder tmp = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch)
			tmp.append(ch);
		for (char ch = 'A'; ch <= 'Z'; ++ch)
			tmp.append(ch);
		for (char ch = 'a'; ch <= 'z'; ++ch)
			tmp.append(ch);
		symbols = tmp.toString().toCharArray();
	}   

	public static String next() {
		return RandomStringUtils.random(length, symbols);
	}
	
}
