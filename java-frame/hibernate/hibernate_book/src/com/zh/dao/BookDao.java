package com.zh.dao;

import java.util.List;

import com.zh.po.Book;
import com.zh.po.Type;
import com.zh.utils.BaseDao;
import com.zh.utils.PageBean;

public class BookDao {

	private BaseDao dao = new BaseDao();
	
	
	
	/**
	 * ���ܣ��б�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public PageBean<Book> getList(Integer page,Integer pageSize){
		
		return dao.findByPage(page, pageSize, Book.class);
	}
	
	/**
	 * ���ܣ������б�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public List<Type> getTypes(){
		
		return dao.findAll(Type.class);
	}
	
	/**
	 * ���ܣ���ѯ��������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public Book getObject(String id){
		
		return dao.findById(Book.class, id);
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
	 * ���ܣ��޸�����
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
	
		Book book = dao.findById(Book.class, id);
		dao.delete(book);
	}	
	
}
