package com.zh.dao;


import com.zh.po.Type;
import com.zh.utils.BaseDao;
import com.zh.utils.PageBean;

public class TypeDao {

	private BaseDao dao = new BaseDao();
	
	
	/**
	 * ���ܣ��б�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public PageBean<Type> getList(Integer page,Integer pageSize){
		
		return dao.findByPage(page, pageSize, Type.class);
	}
	
	/**
	 * ���ܣ���ѯ��������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public Type getObject(String id){
		
		return dao.findById(Type.class, id);
	}
	
	/**
	 * ���ܣ��������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public void insert(Type type){
		
		dao.insert(type);
	}
	
	/**
	 * ���ܣ���������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public void update(Type type){
		
		dao.update(type);
	}

	/**
	 * ���ܣ�ɾ������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public void delete(String id){
	
		Type type = dao.findById(Type.class, id);
		dao.delete(type);
	}
	
}
