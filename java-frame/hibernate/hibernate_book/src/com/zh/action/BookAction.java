package com.zh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zh.dao.CheckDao;
import com.zh.po.Book;
import com.zh.po.Type;
import com.zh.service.BookService;
import com.zh.utils.PageBean;

public class BookAction extends ActionSupport implements ModelDriven<Book> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6425174539574402213L;
	
	private Book book = new Book();
	private List<Type> list;
	private PageBean<Book> pagebean;
	private CheckDao check = new CheckDao();
	private BookService service = new BookService();
	
	/**
	 * ���ܣ�չʾ�б�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String list(){
		
		System.out.println("-----------------------------����List-----------------------------");
		
		String pages = ServletActionContext.getRequest().getParameter("page");
		
		int page =1;int pageSize = 3;
		
		System.out.println(pages);
		
		if(pages != null && !"".equals(pages.trim()))
			page = Integer.parseInt(pages);
		
		pagebean = service.getList(page, pageSize);
		
		System.out.println("-----------------------------List����-----------------------------");
		
		return "list";		
	}
	
	/**
	 * ���ܣ���ת�����ҳ��
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String toAdd(){
		
		list = service.getTypes();
		
		return "add";
	}
	
	/**
	 * ���ܣ��������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String add(){
		
		System.out.println("OOOO"+book);
		
		service.insert(book);
		
		return list();
	}
	
	/**
	 * ���ܣ���ת������ҳ��
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String toUpdate(){
		
		String id = ServletActionContext.getRequest().getParameter("oid");
		
		list = service.getTypes();
		
		Book book2 = service.getObject(id);
		try {
			BeanUtils.copyProperties(book, book2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return "update";
	}
	
	/**
	 * ���ܣ���������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public String update(){
		
		service.update(book);
		
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
		
		int id = book.getId()!=null?book.getId():-1;
		String name = book.getName();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if(name != null && !"".equals(name.trim())){
			
			if(name.matches("\\w{3,8}")){
				if(check.checkName(id,name))
					writer.write("ͼ���Ѵ���!");
				else
					writer.write("");
			}else{
				writer.write("ͼ������ӦΪ3-8λ!");
			}
			
		}else{
			writer.write("ͼ�����Ʋ���Ϊ��!");
		}			
		
		writer.flush();
		writer.close();
	}
	
	/**
	 * ���ܣ����۸��Ƿ�Ϊ����
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @throws IOException 
	 * @user lenovo	
	 */
	public void checkPrice() throws IOException{
		
		System.out.println("---------------����checkPrice---------------");
		
		String price = ServletActionContext.getRequest().getParameter("prices");
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter writer = response.getWriter();
		
		System.out.println(price);
		
		if(price != null && !"".equals(price.trim())){
			if(!price.matches("\\d+(.\\d+)?")){
				writer.write("�۸��ʽ����ȷ!");
			}
				
		}else{
			writer.write("�۸���Ϊ��!");
		}
			
		
		writer.flush();writer.close();
	}
	
	
	public List<Type> getList() {
		return list;
	}

	public void setList(List<Type> list) {
		this.list = list;
	}

	public PageBean<Book> getPagebean() {
		return pagebean;
	}

	public void setPagebean(PageBean<Book> pagebean) {
		this.pagebean = pagebean;
	}

	public Book getModel() {
		return book;
	}
	
	
}
