package cn.itcast.response;
//ͨ��response��write������������ݣ����������Ӱ�ȶ�������
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//��servlet��getOutputStream�����������
public class ResponseDemo2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		test1(response);
	}

	private void test2(HttpServletResponse response) throws IOException {
		String data="�й�q";
		//�þ����ȼ������������
		response.setContentType("text/html;charset=GB2312");
		//response.setCharacterEncoding("GB2312");
		//response.setHeader("Content-type", "text/html;charset=ISO-8859-1");
		PrintWriter out=response.getWriter();
		out.write(data);//
	}
	private void test1(HttpServletResponse response) throws IOException {
		String data="�й�q";
		//����responseʹ�õ�����Կ���response��ʲô������������������,Ĭ��ΪISO-8859-1
		response.setCharacterEncoding("GB2312");
		//ָ���������ʲô���򿪷��������͵�����
		response.setHeader("Content-type", "text/html;charset=GB2312");
		//response.setHeader("Content-type", "text/html;charset=ISO-8859-1");
		PrintWriter out=response.getWriter();
		out.write(data);//
	}



}
