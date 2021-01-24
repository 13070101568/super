package com.baidu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;



public class PropertiesUtil {

	// �����ļ���·��
//	static String classPath = ClassLoader.getSystemResource("").getPath()+"/";
	static String allFilePath = "";
	static String fileName = "WEB-INF\\classes\\jdbc.properties";
	static File file = null;
	
	static{
		allFilePath =ServletActionContext.getServletContext().getRealPath(fileName);
//		classPath =ClassLoader.getSystemResource("").getPath()+"/";
		file = new File(allFilePath);
	}
	
	public static PropertiesUtil getInstance(){
		file = new File(allFilePath);
		return new PropertiesUtil();
	}
	
	public static PropertiesUtil getInstance(String filePath){
		file = new File(filePath);
		return new PropertiesUtil();
	}
	
	public static String getProperties(String key){
		InputStream fis = null;
		try {
			Properties prop = new Properties();
			if(file==null){
				file = new File(allFilePath);
			}
			fis = new FileInputStream(file.getAbsolutePath());
			prop.load(fis);
			
			return prop.getProperty(key);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{if(fis != null){fis.close();}}catch(Exception e){}
		}
		
		return "";
	}
	
	public static String getProperties(String key,String defaultVal){
		InputStream fis = null;
		try {
			Properties prop = new Properties();
			if(file==null){
				file = new File(allFilePath);
			}
			fis = new FileInputStream(file.getAbsolutePath());
			prop.load(fis);
			
			return prop.getProperty(key)==null?defaultVal:prop.getProperty(key);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{if(fis != null){fis.close();}}catch(Exception e){}
		}
		
		return defaultVal;
	}
	
	public static String getEncryptKey(){
		try {
			String s =getProperties("encrypt");
			DES des = new DES();
			return des.decrypt(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getPropertiesByPath(String filePath,String key){
		InputStream fis = null;
		try {
			Properties prop = new Properties();
			if(filePath!=null){
				file = new File(filePath);
			}
			fis = new FileInputStream(file.getAbsolutePath());
			
			prop.load(fis);
			
			return prop.getProperty(key);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{if(fis != null){fis.close();}}catch(Exception e){}
		}
		
		return "";
	}
	
	public static void setProperties(String key,String value){
		Properties prop = new Properties();
		
		FileOutputStream outputFile = null;
		InputStream fis = null;
		try {
			if(file==null){
				file = new File(allFilePath);
			}
			//�������������Ҫ�ֿ����� ��һ������д��ʱ������ǰ������
			fis = new FileInputStream(file.getAbsolutePath());
			//�������Ѿ��е������ļ�
			prop.load(fis);
			
			//׷���µ�����
			prop.setProperty(key, value);
			
			//д������
			outputFile = new FileOutputStream(file.getAbsolutePath()); 
			prop.store(outputFile, "");
			
			outputFile.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{if(fis != null){fis.close();}}catch(Exception e){}
			try{if(outputFile != null){outputFile.close();}}catch(Exception e){}
		}
	}

	
	public static void setProperties(String filePath,String key,String value){
		Properties prop = new Properties();
		
		
		FileOutputStream outputFile = null;
		InputStream fis = null;
		try {
			if(filePath!=null){
				file = new File(filePath);
			}
			//�������������Ҫ�ֿ����� ��һ������д��ʱ������ǰ������
			fis = new FileInputStream(file.getAbsolutePath());
			//�������Ѿ��е������ļ�
			prop.load(fis);
			
			//׷���µ�����
			prop.setProperty(key, value);
			
			//д������
			outputFile = new FileOutputStream(file.getAbsolutePath()); 
			prop.store(outputFile, "");
			
			outputFile.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{if(fis != null){fis.close();}}catch(Exception e){}
			try{if(outputFile != null){outputFile.close();}}catch(Exception e){}
		}
	}
		// ���Դ���
		public static void main(String[] args) {
			//��ȡĬ�������ļ�
			System.out.println(PropertiesUtil.getProperties("jdbc.username"));
			//�޸�Ĭ�������ļ�
			PropertiesUtil.getInstance().setProperties("5555", "327@qq.com");
		}

}
