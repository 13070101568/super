package com.baidu.base.service;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baidu.util.Page;

public interface BaseService {
	/**
	 * ����ʵ��
	 * 
	 * @param obj
	 * @param clazz
	 * @param key
	 * @return
	 */
	public Object save(Object obj, Class clazz, String key);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param obj
	 */
	public void delete(Object obj);

	/**
	 * ͨ����������ʵ��
	 * 
	 * @param clazz
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public List findObjectByPar(Class clazz, String propName, Object propValue);

	/**
	 * �������,�����������
	 * 
	 * @param clazz
	 * @param propName
	 * @param propValue
	 * @param orderName
	 * @param ascoOrdesc
	 * @return
	 */
	public List findObjectByParOrder(Class clazz, String propName, Object propValue, String orderName, String ascoOrdesc);

	/**
	 * ͨ���������������ѯ��Ӧ����
	 * 
	 * @param clazz
	 * @param propNames
	 * @param propValues
	 * @return
	 */
	public List findObjectByPars(Class clazz, String[] propNames, Object[] propValues);

	/**
	 * ͨ�������������ģ����ѯ��Ӧ����
	 * 
	 * @param clazz
	 * @param propNames
	 * @param propValues
	 * @return
	 */
	public List findObjectByLikeParsOrder(Class clazz, String[] propNames, Object[] propValues, String[] orderNames, String[] ascoOrdescs);

	public List findObjectByParsOrder(Class clazz, String[] propNames, Object[] propValues, String orderName, String ascoOrdesc);

	/**
	 * ��ѯ
	 * 
	 * @param clazz
	 * @param proMap
	 * @param orderMap
	 * @return
	 */
	public List findObjectListByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap);

//	public void saveSysTemUserLog(HttpServletRequest request, String operateDetail);

	public Long getUniqueId();


	/**
	 * ��ҳ��ѯ,ͨ���������������ѯ��Ӧ����
	 * 
	 * @param clazz
	 * @param proMap
	 *            (������,ֵ)
	 * @param orderMap
	 *            (��������,desc or asc)
	 * @param request
	 * @author tongjp
	 * @return
	 */
	public Page findObjectPageByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap, HttpServletRequest request);
}

