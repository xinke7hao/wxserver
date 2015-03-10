/************************************************************************************
 * @File name   :      BaseService.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月12日
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
 * XuYanbo				1.0				Initial Version				2014年11月12日 上午10:23:40
 ************************************************************************************/
package com.hunantv.mbp.commons;

/**
 * BaseService 
 * @author XuYanbo
 * @param <T>
 */
public interface BaseService<T> {

	public T get(int id) throws BmcException;
	
	public void insert(T obj) throws BmcUpdateException;

	public void update(T obj) throws BmcUpdateException;

	public void delete(int id) throws BmcUpdateException;
}
