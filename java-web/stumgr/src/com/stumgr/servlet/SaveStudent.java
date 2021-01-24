package com.stumgr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javasky.daos.StudentDao;
import com.stumgr.pojo.Student;

public class SaveStudent extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//��doPost�����еĵ�һ�д������������
		req.setCharacterEncoding("UTF-8");//�����ַ��� ��HTML�����õ�Ҫ��ͬ
		//�ӱ��л������ װ����
		Student student = new Student();
		student.setSname(req.getParameter("sname"));//�ӱ��е�name���Ի�ȡ��Ϣ
		student.setGender(req.getParameter("gender"));
		student.setMobile(req.getParameter("mobile"));
		student.setClasses(req.getParameter("classes"));
		//���������ѡ ���һ����û��ѡ �õ�����null ���ѡ���� ����Ӧ�ð���װ���ַ�������
		String[] hobby = req.getParameterValues("hobby");
		if(null==hobby){
			hobby = new String[]{""};
		}
		String hobbyStr = "";
		for(String str : hobby){
			hobbyStr+=str+",";
		}
		student.setHobby(hobbyStr);
		
		StudentDao dao = new StudentDao();
		
		//�ӱ��л�ȡsno ���û�л�ȡ�� �������� ����Ϊ�޸�
		String sno = req.getParameter("sno");
		boolean flag = false;
		if("".equals(sno)){
			flag = dao.save(student);
		}else{
			student.setSno(Integer.parseInt(sno));
			flag = dao.update(student);
		}
		if(flag){
			req.getRequestDispatcher("/czcg.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("/czsb.jsp").forward(req, resp);
		}
	}

	
}
