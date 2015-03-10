/************************************************************************************
 * @File name   :      JcaptchaTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月7日
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
 * XuYanbo				1.0				Initial Version				2015年1月7日 上午10:27:10
 ************************************************************************************/
package com.hunantv.mbp.unit.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.junit.Test;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hunantv.mbp.unit.BaseJUnitTest;

/**
 * @author XuYanbo
 *
 */
public class CaptchaTest extends BaseJUnitTest {

	@Resource
	private DefaultKaptcha captchaProducer;
	
	@Test
	public void generateImageCaptchaTest() throws IOException{
		BufferedImage captchaImage = captchaProducer.createImage(captchaProducer.createText());
		ImageIO.write(captchaImage, "JPEG", new File("D:/test.jpg"));
		System.out.println("ok");
	}
}
