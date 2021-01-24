package cn.itcast.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//��servlet��getOutputStream�����������
public class ResponseDemo1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		test2(response);
	}

	private void test2(HttpServletResponse response) throws IOException,
			UnsupportedEncodingException {
		String data="�й�2 ";
		//html: <meta>��ǩģ��һ��http��Ӧͷ
		OutputStream out=response.getOutputStream();
		//out.write(data.getBytes());
		out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>".getBytes());
		out.write(data.getBytes("UTF-8"));
	}
	private void test1(HttpServletResponse response) throws IOException,
	UnsupportedEncodingException {
		String data="�й� ";
		//������ʲô��������һ��Ҫ�����������ʲô����
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		OutputStream out=response.getOutputStream();
		//out.write(data.getBytes());
		out.write(data.getBytes("UTF-8"));
	}

}
