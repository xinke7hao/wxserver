/************************************************************************************
 * @File name   :      Legend.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月20日
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
 * XuYanbo				1.0				Initial Version				2014年11月20日 上午9:48:51
 ************************************************************************************/
package com.hunantv.mbp.entity.echarts;

import java.util.ArrayList;
import java.util.List;

/**
 * ECharts - Legend
 * @author XuYanbo
 */
public class Legend {

	private List<String> data = new ArrayList<String>();

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

}
