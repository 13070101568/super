package com.stumgr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javasky.daos.LoginDao;
import com.stumgr.pojo.User;

public class LoginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//������Ӧ����
		response.setContentType("text/html;charset=UTF-8");
		//���������ַ���
		request.setCharacterEncoding("UTF-8");
		
		User user = null;
		//�ӵ�½���л��sname��spwd
		String uname =request.getParameter("sname");
		String upwd =request.getParameter("spwd");
		//����dao
		LoginDao logindao= new LoginDao();
		user=logindao.getUserByName(uname);
		if(null!=user){//�д��û�
			if(user.getPwd().equals(upwd)){
				//��½�ɹ���ת����ϵͳ���˵�
				request.getRequestDispatcher("/master/master.jsp").forward(request, response);
				
			}else{
				//������󣬻ص���¼ҳ�棬��ʾ���벻��ȷ
				/*�����½���󣬱�������� ���Դ���������ȡ ��Ϣ*/
				request.setAttribute("err", "�û����������벻��");
				//��תҳ��
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				
			}
		}else{
			request.setAttribute("err", "�޴��û�");//err�����������JSPҳ����EL���ʽ�е���һ��
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
	}

}
