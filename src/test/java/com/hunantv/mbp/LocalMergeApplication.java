/************************************************************************************
 * @File name   :      LocalMergeApplication.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月3日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2014年10月30日 下午2:39:10			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.hunantv.mbp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 将本版本的修改同步到本地另一个目录
 * 指定扫描目录
 * 文件覆盖操作
 * 目前只支持文件的修改和新增(重命名操作不会删除原有文件)
 * @author xuyanbo
 *
 */
public class LocalMergeApplication {

	static String srcDir = "D:\\Develop\\GitRepository\\boss-mbp";
	static String destDir = "D:\\Develop\\HTVRepository\\bmc";
	
	static String[] scanEscapes = {"target","logs"};
	
	/**
	 * 扫描根目录下的文件及文件夹
	 * 排除.开头的文件
	 * 排除scanEscapes中定义的文件夹
	 * @throws IOException 
	 */
	public static void scan() throws IOException{
		File f = new File(srcDir);
		if(f.exists() && f.isDirectory()){
			File[] files = f.listFiles();
			for(File file: files){
				if(!file.getName().startsWith(".") && !escape(file.getName())){
					scanFile(file);
				} else {
					System.out.println(" **** [ignoring] " + file.getAbsolutePath() + " ****");
				}
			}
		}
	}
	
	/**
	 * 扫描文件是否需要被忽略
	 * @param filename
	 * @return
	 */
	public static boolean escape(String filename){
		for(String temp: scanEscapes){
			if(temp.equals(filename)) return true;
		}
		return false;
	}
	
	/**
	 * 递归扫描文件
	 * @param file
	 * @throws IOException
	 */
	public static void scanFile(File file) throws IOException{
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f: files){
				scanFile(f);
			}
		} else {
			System.out.println("**** " + file.getAbsolutePath() + " ****");
			transfer(file);
		}
	}
	
	/**
	 * 执行文件同步
	 * @param file
	 * @throws IOException
	 */
	public static void transfer(File file) throws IOException {
		String destFilePath = file.getAbsolutePath().replace(srcDir, destDir);
		File destFile = new File(destFilePath);
		if(!destFile.exists()){
			File dir = new File(destFile.getParent());
			if(!dir.exists()){
				dir.mkdirs();
			}
			destFile.createNewFile();
		}
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(destFile));
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		byte[] buf = new byte[1024];
		int len = -1;
		while((len = dis.read(buf)) != -1){
			dos.write(buf, 0, len);
		}
		dos.flush();
		dos.close();
		dis.close();
	}
	
	//Run
	public static void main(String[] args){
		
//		String[] cmds = {"cmd", "cd \\", srcDir.substring(0, 1), ("cd " + srcDir), "git status"};
//		String[] cmds = {"cmd", "ping 192.168.1.94"};
//		try {
//			Process p = Runtime.getRuntime().exec("D:\\Develop\\GitRepository\\boss-mbp\\src\\test\\java\\com\\hunantv\\test\\compare.bat");
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			String line;
//			while((line = br.readLine()) != null){
//				System.out.println(line);
//			}
//			br.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		try {
			scan();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
