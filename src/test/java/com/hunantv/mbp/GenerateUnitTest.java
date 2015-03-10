/************************************************************************************
 * @File name   :      GenerateUnitTest.java
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
 * XuYanbo				1.0				Initial Version				2014年12月4日 下午4:19:55
 ************************************************************************************/
package com.hunantv.mbp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 根据Service构建JUnit基础代码
 * @author XuYanbo
 */
public class GenerateUnitTest {

	static String templatePath = "D:\\Develop\\GitRepository\\boss-mbp\\src\\test\\java\\com\\hunantv\\mbp\\JUnitTemplate.tpl";
	static String servicePackage = "com.hunantv.mbp.service";
	static String codeDestDir = "D:\\Develop\\GitRepository\\boss-mbp\\src\\test\\java\\com\\hunantv\\mbp\\unit\\temp";
	static String templateContent;
	
	/**
	 * 读取模板文件
	 * @Author：XuYanbo
	 * @Date：2014年12月4日
	 * @throws IOException
	 */
	private static void readTemplate() throws IOException {
		File templateFile = new File(templatePath);
		StringBuffer content = new StringBuffer();
		BufferedReader br = new BufferedReader(new FileReader(templateFile));
		String line;
		while((line = br.readLine()) != null){
			content.append(line).append("\r\n");
		}
		br.close();
		templateContent = content.toString();
	}
	
	/**
	 * 创建UnitTest
	 * @throws IOException 
	 * @Author：XuYanbo
	 * @Date：2014年12月4日
	 * @param cls
	 */
	private static void createUnitTest(Class cls) throws IOException {
		String className = cls.getName().substring(cls.getName().lastIndexOf('.') + 1);
		System.out.println(" ***** Create " + className + " Unit Test Begin *****");
		File unitTest = new File(codeDestDir + File.separatorChar + className + "Test.java");
		if(!unitTest.exists()){
			unitTest.createNewFile();
		}
		
		//Generate Code(Unformatted)
		StringBuffer methodBuf = new StringBuffer();
		
		Method[] methods = cls.getDeclaredMethods();
		int len = methods.length;
		for(int i=0;i<len;i++){
			methodBuf.append("@Test").append("\r\n");
			methodBuf.append("public void " + methods[i].getName() + "Test() throws Exception {").append("\r\n");
			methodBuf.append("\r\n");
			methodBuf.append("assertTrue(true);\r\n");
			methodBuf.append("}").append("\r\n").append("\r\n");
		}

		String code = templateContent.replaceAll("#serviceName#", className).replace("#filename#", className+"Test").replace("#testBody#", methodBuf.toString());
		
		FileWriter fw = new FileWriter(unitTest);
		StringReader sr = new StringReader(code);
		char[] cbuf = new char[1024];
		int readlen = -1;
		while((readlen = sr.read(cbuf)) != -1){
			fw.write(cbuf, 0, readlen);
		}
		fw.flush();
		fw.close();
		sr.close();
		
		System.out.println(" ***** Create " + cls.getName() + " Unit Test End *****");
	}
	
	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Class[] getClasses(String packageName, boolean scanChild) throws ClassNotFoundException, IOException {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile()));
	    }
	    ArrayList<Class> classes = new ArrayList<Class>();
	    for (File directory : dirs) {
	        classes.addAll(findClasses(directory, packageName, scanChild));
	    }
	    return classes.toArray(new Class[classes.size()]);
	}
	
	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class> findClasses(File directory, String packageName, boolean scanChild) throws ClassNotFoundException {
	    List<Class> classes = new ArrayList<Class>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	    	if(scanChild){
		        if (file.isDirectory()) {
		            assert !file.getName().contains(".");
		            classes.addAll(findClasses(file, packageName + "." + file.getName(), true));
		        } else if (file.getName().endsWith(".class")) {
		            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
		        }
	    	} else {
		        if (file.isDirectory()) {
		        	continue;
		        } else if (file.getName().endsWith(".class")) {
		            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
		        }
	    	}
	    }
	    return classes;
	}
	
	//Run
	public static void main(String[] args){
		try {
			readTemplate();
			Class[] classes = getClasses(servicePackage, false);
			for(Class cls: classes){
				createUnitTest(cls);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
