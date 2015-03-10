/************************************************************************************
 * @File name   :      LambdaTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月19日
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
 * XuYanbo				1.0				Initial Version				2014年12月19日 上午10:54:10
 ************************************************************************************/
package com.hunantv.mbp.unit.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;

import com.hunantv.mbp.dao.admin.RightMapper;

/**
 * Lambda测试类
 * @author XuYanbo
 *
 */
public class LambdaTest {
	
	@Resource
	private RightMapper rightMapper;
	
	@Test
	public void testListForEach(){

		List<Integer> data = new ArrayList<Integer>();
		Random r = new Random();
		for(int i=0;i<100;i++){
			data.add(r.nextInt(1000));
		}
		
		long start = System.currentTimeMillis();
		for(Integer d: data){System.out.print(d);}
		long end = System.currentTimeMillis();
		System.out.println("\ncommon: " + (end-start));
		long start1 = System.currentTimeMillis();
		data.forEach(d->{System.out.print(d);});
		long end1 = System.currentTimeMillis();
		System.out.println("\nlambda: " + (end1-start1));
		
	}

}
