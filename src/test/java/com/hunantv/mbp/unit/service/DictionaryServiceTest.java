/************************************************************************************
 * @File name   :      DictionaryServiceTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月4日
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
 * XuYanbo				1.0				Initial Version				2014年12月4日 下午1:28:11
 ************************************************************************************/
package com.hunantv.mbp.unit.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.hunantv.mbp.commons.HttpServiceConstants;
import com.hunantv.mbp.entity.admin.Dictionary;
import com.hunantv.mbp.service.DictionaryService;
import com.hunantv.mbp.unit.BaseJUnitTest;

/**
 * @author XuYanbo
 */
public class DictionaryServiceTest extends BaseJUnitTest {

	@Resource
	private DictionaryService dictionaryService;
	
	@Test
	public void testGetDictionariesByCode() throws Exception {
		String dictCode = HttpServiceConstants.DICT_PAY_CHANNEL;
		List<Dictionary> dicts = dictionaryService.getDictionariesByCode(dictCode);
		assertTrue(dicts!=null && dicts.size()>0);
	}

}
