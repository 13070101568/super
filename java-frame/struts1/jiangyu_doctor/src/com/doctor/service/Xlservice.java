package com.doctor.service;

import java.util.List;

import com.doctor.dao.Xldao;
import com.doctor.pojo.Xl;

public class Xlservice {

	private Xldao dao = new Xldao();
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ���ѯ����ҽ����ѧ��
	 */
	public List<Xl> query() {
		// TODO Auto-generated method stub
		String sql = " select * from t_xl ";
		
		return dao.query(sql);
	}

}
