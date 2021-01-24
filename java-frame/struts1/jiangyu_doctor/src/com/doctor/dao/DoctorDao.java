package com.doctor.dao;

import java.util.List;

import com.doctor.pojo.Doctor;
import com.doctor.utils.BaseDao;
import com.doctor.utils.PageBean;

public class DoctorDao {

	private BaseDao dao = new BaseDao();
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ���ѯ����ҽ��
	 */
	public PageBean<Doctor> queryAll(String sql,int page,int pageSize) {
		// TODO Auto-generated method stub
		return dao.queryPage(Doctor.class, sql, page, pageSize);
	}

	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ���ѯһ��ҽ��
	 */
	public Doctor queryOne(String sql, int id) {
		// TODO Auto-generated method stub
		return dao.queryOne(Doctor.class, sql, id);
	}
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ�ת���޸�ҳ��
	 */
	public void alter(String sql, Doctor d) {
		// TODO Auto-generated method stub
		dao.executeUpdate(sql, d.getName(),d.getAge(),d.getContent(),d.getXid(),d.getId());
	}
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ����ҽ��
	 */
	public void add(String sql, Doctor d) {
		// TODO Auto-generated method stub
		dao.executeUpdate(sql, d.getName(),d.getAge(),d.getContent(),d.getXid());
	}

}
