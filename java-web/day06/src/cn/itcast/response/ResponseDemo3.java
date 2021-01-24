package cn.itcast.response;
//ͨ��responseʵ���ļ�����
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
public class ResponseDemo3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {

		test1(response);	
	}

	private void test2(HttpServletResponse response)
			throws FileNotFoundException, IOException {
		String path=this.getServletContext().getRealPath("/download/��ͼƬ.jpg");
		//C:\apache-tomcat-7.0.22\webapps\day06\download\��ͼƬ.jsp
		String filename=path.substring(path.lastIndexOf("\\")+1);
		
		InputStream in=null;
		OutputStream out=null;
		//��������ļ��������ļ������ļ�����Ҫ����url����
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(filename,"UTF-8"));
		try {
			in=new FileInputStream(path);
			int len=0;
			byte buffer[]=new byte[1024];
			out=response.getOutputStream();
			while((len=in.read(buffer))>0){
				out.write(buffer,0,len);
			}
		}finally{
			if(in!=null){
				try{
					
					in.close();
				}catch(Exception e){
					e.printStackTrace();
				}				
			}
		}
	}
	private void test1(HttpServletResponse response)
	throws FileNotFoundException, IOException {
		String path=this.getServletContext().getRealPath("/download/new.jpg");
		//C:\apache-tomcat-7.0.22\webapps\day06\download\new.jsp
		String filename=path.substring(path.lastIndexOf("\\")+1);
		
		InputStream in=null;
		OutputStream out=null;
		response.setHeader("content-disposition", "attachment;filename="+filename);
		

		try {
			in=new FileInputStream(path);
			int len=0;
			byte buffer[]=new byte[1024];
			out=response.getOutputStream();
			while((len=in.read(buffer))>0){
				out.write(buffer,0,len);
			}
		}finally{
			if(in!=null){
				try{
					
					in.close();
				}catch(Exception e){
					e.printStackTrace();
				}				
			}
		}
	}
}
