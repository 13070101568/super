package cn.itcast.response;
//��ʱˢ��
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ResponseDemo5 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			test3(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void test3(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception {
		String message="<meta http-equiv='refresh' content='3;url=/day06/index.jsp'>��¼�ɹ�������3�����ת�����û�У����<a href=''>������</a>";
		request.setAttribute("message",message);
		this.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
	}
	private void test2(HttpServletResponse response) throws IOException {
		response.setHeader("refresh", "3;url='/day06/index.jsp'");
		response.setContentType("text/html;charset=GB2312");
		String data = new Random().nextInt(100000)+"";
		response.getWriter().write("��¼�ɹ�������3�����ת�����û�У����<a href=''>������</a>");
	}
	private void test1(HttpServletResponse response) throws IOException {
		response.setHeader("refresh", "3");
		String data = new Random().nextInt(100000)+"";
		response.getWriter().write(data);
	}

}
