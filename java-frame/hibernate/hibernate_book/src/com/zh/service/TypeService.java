package com.zh.service;

import com.zh.dao.TypeDao;
import com.zh.po.Type;
import com.zh.utils.PageBean;

public class TypeService {

	private TypeDao dao = new TypeDao();
	
	/**
	 * ���ܣ��б�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public PageBean<Type> getList(Integer page,Integer pageSize){
		
		return dao.getList(page, pageSize);
	}
	
	/**
	 * ���ܣ���ѯ��������
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-22
	 * @user lenovo	
	 */
	public Type getObject(String id){
		
		return dao.getObject(id);
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
	
		dao.delete(id);
	}
	
	/**
	 * ���ܣ�����ɾ������
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
