package com.doctor.dao;

import java.util.List;

import com.doctor.pojo.Xl;
import com.doctor.utils.BaseDao;

public class Xldao {

	private BaseDao dao = new BaseDao();
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ���ѯ����ҽ����ѧ��
	 */
	public List<Xl> query(String sql) {
		// TODO Auto-generated method stub
		return dao.queryAll(Xl.class, sql);
	}

}
