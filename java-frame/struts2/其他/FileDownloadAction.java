package com.rain.sponsor.file;

import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	// ����������������ص���ʽ����
	private static String contentType = "application/x-msdownload";
	// ������������ݽ�Ҫ���ļ����ļ���
	private static String contentDisposition = "attachment;filename=";
	// ���÷�������ȡ�ļ��Ļ�������С
	private static String BUFFER_SIZE = "2048";

	// ǰ̨����������Ҫ���ص��ļ�����·��
	private String fnamePath;
	
	public String downLoad(){
		File file = new File(this.fnamePath);
		
		this.contentDisposition+=file.getName();
		
		return "fileDown";
	}
	
	public InputStream getFiles(){
		return ServletActionContext.getServletContext().getResourceAsStream("/file/"+this.fnamePath);
	}

	public String getFnamePath() {
		return fnamePath;
	}

	public void setFnamePath(String fnamePath) {
		this.fnamePath = fnamePath;
	}

}
