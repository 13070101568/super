package com.zh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import com.zh.dao.CheckDao;
import com.zh.po.Type;
import com.zh.service.TypeService;
import com.zh.utils.PageBean;

public class TypeAction extends ActionSupport implements ModelDriven<Type> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8515276615234994583L;
	
	private Type type = new Type();
	private PageBean<Type> pagebean;
	private CheckDao check = new CheckDao();
	private TypeService service = new TypeService();
	
	/**
	 * ���ܣ�չʾ�б�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String list(){
		
		String pages = ServletActionContext.getRequest().getParameter("page");
		
		int page =1;int pageSize = 3;
		
		if(pages != null && !"".equals(pages.trim()))
			page = Integer.parseInt(pages);
		
		pagebean = service.getList(page, pageSize);
		
		return "list";		
	}
	
	/**
	 * ���ܣ���ת�����ҳ��
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String toAdd(){
		
		return "add";
	}
	
	/**
	 * ���ܣ��������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String add(){
		
		System.out.println("=========="+type);
		
		service.insert(type);
		
		return list();
	}
	
	/**
	 * ���ܣ���ת������ҳ��
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String toUpdate(){
				
		System.out.println("-----------------------------����toUpdate-----------------------------");
		
		String id = ServletActionContext.getRequest().getParameter("oid");
		
		Type type2 = service.getObject(id);
		
		try {
			BeanUtils.copyProperties(type, type2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("-----------------------------toupdate����-----------------------------");
		
		return "update";
	}
	
	/**
	 * ���ܣ���������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String update(){
		
		System.out.println("-----------------------------����Update-----------------------------");
		
		System.out.println(type);
		
		service.update(type);
		
		System.out.println("-----------------------------Update����-----------------------------");
		
		return list();
	}
	
	/**
	 * ���ܣ�ɾ��/����ɾ��
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String delete(){
		
		String ids = ServletActionContext.getRequest().getParameter("oid");
		
		service.delAll(ids);
		
		return list();
	}
	
	/**
	 * ���ܣ���������Ƿ��ظ�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public void checkName() throws IOException{
		
		System.out.println("---------------����checkName-----------------");
		
		int id = type.getId()!=null?type.getId():-1;
		String name = type.getName();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if(name != null && !"".equals(name.trim())){
			
			if(name.matches("\\w{3,8}")){
				if(check.checkName(id,name))
					writer.write("�����Ѵ���!");
				else
					writer.write("");
			}else{
				writer.write("��������ӦΪ3-8λ!");
			}
			
		}else{
			writer.write("�������Ʋ���Ϊ��!");
		}			
		
		writer.flush();
		writer.close();
	}
	
	
	
	
	public PageBean<Type> getPagebean() {
		return pagebean;
	}

	public void setPagebean(PageBean<Type> pagebean) {
		this.pagebean = pagebean;
	}
	public Type getModel() {
		return type;
	}

	
}
