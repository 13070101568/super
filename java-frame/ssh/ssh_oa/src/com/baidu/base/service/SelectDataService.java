package com.baidu.base.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baidu.util.Page;

public interface SelectDataService {
	public List queryForList(String sql);

	public int queryForInt(String sql);
	
	public List getDataObjs(String sql,Class clazz);
	
	public boolean remove(String sql);

	public String[] getColumnName(String sql);


	public int update(String sql);
	public Map getListForPage(HttpServletRequest request, String sql);

	public Page getForPage(HttpServletRequest request, String sql);


	public String getString(String sql);


	public void updateClob(String string, String content, String string2, String id, String string3);

	public String getClob(String string, String string2, String id, String string3);

	public boolean delete(String sql);

	/**
	 * ����û�з���ֵ�Ĵ洢����
	 * 
	 * @author wgzx_106
	 * @param callName
	 *            �洢��������
	 * @author wgzx_106
	 * @param args
	 *            List���ϣ����������ŵ��ַ���
	 * @version 5:16:39 PM
	 */
	public void callProceDureNoResult(String callName, List<String> args);

	/**
	 * ���÷�������������ݵĴ洢����
	 * 
	 * @author wgzx_106
	 * @param callName
	 *            �洢��������
	 * @author wgzx_106
	 * @param args
	 *            List���ϣ����������ű����������͵��ַ��� ��������_����ֵ����input_1 �����������out_2 �������
	 * @author wgzx_106
	 * @return ���������������ֵ�ļ���
	 * @version 5:16:26 PM
	 */
	public List<String> callProceDureByOut(String callName, List<String> args);
}