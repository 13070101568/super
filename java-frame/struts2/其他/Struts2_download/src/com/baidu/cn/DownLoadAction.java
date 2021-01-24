package com.baidu.cn;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownLoadAction extends ActionSupport {
	// contentType: �������
	// contentLength: ���ص��ļ��ĳ���
	// contentDisposition: �趨 Content-Dispositoin ��Ӧͷ. 
	// ����Ӧͷָ����Ӧ��һ���ļ���������, һ��ȡֵΪ attachment;
	// filename="document.pdf".
	// inputName: ָ���ļ��������� getter ������Ǹ����Ե�����. Ĭ��Ϊ inputStream
	// bufferSize: ����Ĵ�С. Ĭ��Ϊ 1024
	// allowCaching: �Ƿ�����ʹ�û���
	// contentCharSet: ָ�����ص��ַ���
	private String contentType;
	private long contentLength;
	private String contentDisposition;
	private InputStream inputStream;

	public String getContentType() {
		return contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public String execute() throws Exception {

		// ȷ��������Ա������ֵ
		contentType = "text/html";
		contentDisposition = "attachment;filename=hidden.html";
		// servletContext�ӿ���Servlet������һ���ӿڣ�������webӦ�õ�Servlet��ͼ��
		// ServletContextʵ����ͨ�� getServletContext()������õģ�
		// ����HttpServlet�̳�Servlet�Ĺ�ϵ��GenericServlet���HttpServlet��ͬʱ���и÷�����
		ServletContext servletContext = ServletActionContext
				.getServletContext();
		String fileName = servletContext.getRealPath("/files/hidden.html");
		inputStream = new FileInputStream(fileName);
		contentLength = inputStream.available();

		return SUCCESS;
	}
}
