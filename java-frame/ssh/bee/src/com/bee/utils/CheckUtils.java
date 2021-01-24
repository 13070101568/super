package com.bee.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class CheckUtils<T> {

	
	/**
	 * AJAX��֤������
	 * 
	 * С����ר��AJAX��֤CheckUtils<T>
	 * by 1405javaB ���� 
	 * ����è�� 2015-09-22 by JiangYu
	 */
	public static <T> void getAjaxResult(Class<T> clazz,String name) {
		HibernateDao<T> dao = new HibernateDao<T>();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String simpleName = clazz.getSimpleName();
		String hql=" from  "+ simpleName +"  where uname = ? ";
		
		if(name==null||"".equals(name.trim())){
			out.print("<font color=red>����Ϊ�գ�!</font>");
		}else if(!name.matches("\\w{3,8}")){
			out.print("<font color=red>���ȱ�����3-8֮�䣡!</font>");
		}else {
			boolean flag = dao.findOneByHql2(hql, name);
			if(!flag){
				out.print("<font color=blue>��ϲ�����ƿ��ã�!</font>");
			}else{
				out.print("<font color=red>�����Ѵ��ڣ�������!</font>");
			}

		}
		
	}
	
//	$(function(){
//		 $("#uname").blur(function(){
//				var $uname=$("#uname").val();
//				
//				$.ajax({
//						type:"post",
//						url:"user!check.action",
//						data:{"uname":$uname},
//						success:function(msg){
//							alert(msg);
//							$("#span").html(msg);
//						}
//				});
//				
//			});
//		  
//		  $("#uname").focus(function(){
//			  $("#span").empty();
//		  });
//	});
}

