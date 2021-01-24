package com.bw.service;

import java.util.List;

import com.bw.dao.UserDAO;
import com.bw.entity.User;

public class UserService {

	private UserDAO dao = new UserDAO();
	/*
	 * ���ܣ����
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public void add(User user){
		dao.add(user);
	}
	
	/*
	 * ���ܣ��б�
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public List getListPage(Integer currentPage,Integer pageSize){
		return dao.getListPage(currentPage, pageSize);
	}
	
	/*
	 * ���ܣ��ܼ�¼��
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public int getListCount(){
		return dao.getListCount();
	}
	
	/*
	 * ���ܣ�ɾ��
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public void delete(String id){
		dao.delete(id);
	}
	
	/*
	 * ���ܣ���ѯid
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public User getUserById(Integer id){
		return dao.getUserById(id);
	}
}
