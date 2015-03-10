/************************************************************************************
 * @File name   :      BitTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年3月2日
 *
 * @Copyright Notice: 
 * Copyright (c) 2015 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2015年3月2日 上午11:21:25
 ************************************************************************************/
package com.hunantv.mbp;

import java.util.Random;

/**
 * @author XuYanbo
 *
 */
public class BitTest {

	public static void main(String[] args){
		Random r = new Random();
		for(int i=0;i<10;i++){
			int a = r.nextInt(100);
			System.out.println(a+" & 1="+(a&1));
		}
	}
}
