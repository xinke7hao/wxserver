/************************************************************************************
 * @File name   :      ReflectionUtils.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月15日
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
 * XuYanbo				1.0				Initial Version				2014年11月12日 上午11:17:21
 ************************************************************************************/
package com.xuyanbo.wx.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 反射工具类
 */
public class ReflectionUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	/**
	 * 功能说明：调用Getter方法.
	 * @Author：XuYanbo
	 * @Date：2014-11-15
	 * @param：obj，待调用的类名
	 * @param：propertyName，要提取的属性名
	 * @return：无
	 * @throws：无
	 */
	public static Object invokeGetterMethod(Object obj, String propertyName) {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		return invokeMethod(obj, getterMethodName, new Class[] {}, new Object[] {});
	}

	/**
	 * 功能说明：调用Setter方法.使用value的Class来查找Setter方法.
	 * @Author：XuYanbo
	 * @Date：2014-11-15
	 * @param：obj，待调用的类名
	 * @param：propertyName，要提取的属性名
	 * @param：value，
	 * @return：无
	 * @throws：无
	 */
	public static void invokeSetterMethod(Object obj, String propertyName, Object value) {
		invokeSetterMethod(obj, propertyName, value, null);
	}

	/**
	 * 功能说明：调用Setter方法.
	 * @Author：XuYanbo
	 * @Date：2014-11-15
	 * @param：obj，待调用的类名
	 * @param：propertyName，要提取的属性名
	 * @param：value，
	 * @param：propertyType，用于查找Setter方法,为空时使用value的Class替代.
	 * @return：无
	 * @throws：无
	 */
	public static void invokeSetterMethod(Object obj, String propertyName, Object value, Class<?> propertyType) {
		Class<?> type = propertyType != null ? propertyType : value.getClass();
		String setterMethodName = "set" + StringUtils.capitalize(propertyName);
		invokeMethod(obj, setterMethodName, new Class[] { type }, new Object[] { value });
	}

	/**
	 * 功能说明：直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 * @Author：XuYanbo
	 * @Date：2014-11-15
	 * @param：obj，待调用的类名
	 * @param：fieldName，属性名
	 * @return：result，返回相关属性的值
	 * @throws：无
	 */
	public static Object getFieldValue(final Object obj, final String fieldName) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		Object result = null;
		try {
			result = field.get(obj);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 功能说明：直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 * @Author：XuYanbo
	 * @Date：2014-11-15
	 * @param：obj，待调用的类名
	 * @param：fieldName，属性名
	 * @param：value，
	 * @return：无
	 * @throws：IllegalArgumentException
	 */
	public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/**
	 * 功能说明：循环向上转型, 获取对象的DeclaredField,	 并强制设置为可访问.
	 * 			 如向上转型到Object仍无法找到, 返回null.
	 * @Author：XuYanbo
	 * @Date：2014-11-15
	 * @param：obj，待调用的类名
	 * @param：fieldName，属性名
	 * @return：field，
	 * @throws：无
	 */
	public static Field getAccessibleField(final Object obj, final String fieldName) {
		Assert.notNull(obj, "object不能为空");
		Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {//NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 功能说明：直接调用对象方法, 无视private/protected修饰符.
	 * 			 用于一次性调用的情况.
	 * @Author：XuYanbo
	 * @Date：2014-11-15
	 * @param：obj，待调用的类名
	 * @param：methodName，方法名
	 * @param：parameterTypes，
	 * @param：method.invoke，
	 * @return：无
	 * @throws：convertReflectionExceptionToUnchecked
	 */
	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
			final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 功能说明：循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 			 如向上转型到Object仍无法找到, 返回null.
	 * 			 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object... args)
	 * @Author：XuYanbo
	 * @Date：2014-11-15
	 * @param：obj，待调用的类名
	 * @param：methodName，方法名
	 * @param：parameterTypes，
	 * @return：method，
	 * @throws：无
	 */
	public static Method getAccessibleMethod(final Object obj, final String methodName,
			final Class<?>... parameterTypes) {
		Assert.notNull(obj, "object不能为空");

		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Method method = superClass.getDeclaredMethod(methodName, parameterTypes);

				method.setAccessible(true);

				return method;

			} catch (NoSuchMethodException e) {//NOSONAR
				// Method不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 功能说明：将反射时的checked exception转换为unchecked exception.
	 * @Author：XuYanbo
	 * @Date：2014-11-15
	 * @param：e
	 * @return：RuntimeException
	 * @throws：无
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
}