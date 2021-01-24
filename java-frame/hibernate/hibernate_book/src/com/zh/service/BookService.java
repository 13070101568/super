package com.zh.service;

import java.util.List;

import com.zh.dao.BookDao;
import com.zh.po.Book;
import com.zh.po.Type;
import com.zh.utils.PageBean;

public class BookService {

	private BookDao dao = new BookDao();
	
	/**
	 * ���ܣ��б�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public PageBean<Book> getList(Integer page,Integer pageSize){
		
		return dao.getList(page, pageSize);
	}
	
	/**
	 * ���ܣ������б�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public List<Type> getTypes(){
		
		return dao.getTypes();
	}
	
	/**
	 * ���ܣ���ѯ��������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public Book getObject(String id){
		
		return dao.getObject(id);
	}
	
	/**
	 * ���ܣ��������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public void insert(Book book){
		
		dao.insert(book);
	}
	
	/**
	 * ���ܣ���������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public void update(Book book){
		
		dao.update(book);
	}

	/**
	 * ���ܣ�ɾ������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public void delete(String id){
	
		dao.delete(id);
	}
	
	/**
	 * ���ܣ�����ɾ��
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public void delAll(String ids){
		
		String[] idd = ids.split(",");
		for (String id : idd) {
			
			dao.delete(id);
		}
	}

}
