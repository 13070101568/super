package com.jiangyu.test;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestFreeMarker{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args)  throws Exception {
		//����FreeMarker����ʵ��
		Configuration cfg = new Configuration();
		
//		URL resource = TestFreeMarker.class.getResource("templates");
//		System.out.println(resource);
		cfg.setDirectoryForTemplateLoading(new File(//setClassForTemplateLoading(this.getClass(), "../../../");
				"D:\\workspace\\java-eclipse\\freemarkDemo\\WebContent\\templates"));
		
		//��������ģ��
		Map root = new HashMap();
		root.put("user", "С����");
		root.put("num0", 1);
		root.put("����è��", "123/321");
		
		//javabean�����Զ�ת��   ����������ǰ��ѧϰ��EL���ʽ
		/**
		 * User u = new User();
		 * u.setUname("����");
		 * root.put("user1", u);
		 */
		
		//����ģ���ļ�
		Template t =cfg.getTemplate("abc.ftl");
		
		//��freemarkerģ����д������
//		Writer out = new OutputStreamWriter(System.out);
//		t.process(root, out);
		
		FileWriterWithEncoding out = null;
		//ͨ��һ���ļ���������Ϳ���д����Ӧ���ļ���
		out = new FileWriterWithEncoding(new File(
				"D:/workspace/java-eclipse/freemarkDemo/WebContent/templates/static.htm"),
				"UTF-8");
		
		t.process(root, out);
		out.flush();
		
	}
	
}
