package cn.itcast.url;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo1  extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//���������� /����ǰӦ��
//���������	/������վ����վ���ж��Ӧ��	
request.getRequestDispatcher("/form1.html").include(request, response);

response.sendRedirect("/day06/form1.html");

this.getServletContext().getRealPath("/form1.html");

this.getServletContext().getResourceAsStream("/fomr1.html");

/**
 * 
 <a href="/day06/form.html">xx</a>
 
 <form action="/day06/form.html">
 </form>
 */

		

		
		
	}

}
