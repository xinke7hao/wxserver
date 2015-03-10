/************************************************************************************
 * @File name   :      PassGeneratorTest.java
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
 * XuYanbo				1.0				Initial Version				2014年12月9日 上午10:54:10
 ************************************************************************************/
package com.hunantv.mbp.unit.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.hunantv.mbp.utils.PassGenerator;

/**
 * 随机密码生成器测试类
 * @author XuYanbo
 *
 */
public class PassGeneratorTest {
	
	@Test
	public void testPassString(){
		String newPass = PassGenerator.next();
		System.out.println(newPass);
		System.out.println(DigestUtils.md5Hex("laWD8O"));
		//75eba654de93be1234f36f5e8baff766
	}

}
