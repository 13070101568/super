package com.jiangyu.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {

	//getTemplate("01.ftl")
	public Template getTemplate(String name){
		
		try {
			//ͨ��FreeMarker��Configuration��ȡ��Ӧ��ftl
			Configuration cfg = new Configuration();
			//�趨ȥ�����ȡ��Ӧ��ftlģ���ļ�(���·��)
			cfg.setClassForTemplateLoading(this.getClass(), "../../../");
			
			//��ģ���ļ�Ŀ¼���ҵ�����Ϊname���ļ�
			Template temp = cfg.getTemplate(name, "UTF-8");
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void print(String name,Map<String,Object> root){
		
		try {
			//ͨ��Template���Խ�ģ���ļ��������Ӧ����
			Template temp = this.getTemplate(name);
			temp.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void fprint(String name,Map<String,Object> root,String outFileName){
		FileWriterWithEncoding out = null;
		try {
			//ͨ��Template���Խ�ģ���ļ��������Ӧ����
			out = new FileWriterWithEncoding(new File(outFileName),"UTF-8");
			Template temp = this.getTemplate(name);
			temp.process(root, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
