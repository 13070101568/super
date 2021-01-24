package com.baidu.base.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;

import com.baidu.util.Page;
import com.baidu.util.QueryResult;

public interface BaseDao<T> {
	/**
	 * ��ȡ��¼����
	 * 
	 * @param entityClass
	 *            ʵ����
	 * @return
	 */
	public long getCount();

	/**
	 * ���һ�����������
	 */
	public void clear();

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 *            ʵ��id
	 */
	public Serializable save(T entity);

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 *            ʵ��id
	 */
	public void update(T entity);

	/**
	 * ��������ʵ��
	 * 
	 * @param entity
	 *            ʵ��id
	 * */
	public void saveOrUpdateEntity(T t);

	
	/**
	 * ɾ��ʵ��
	 * 
	 * @param Object ʵ�����
	 *            
	 */
	public void delete(Object obj);
	/**
	 * ɾ��ʵ��
	 * 
	 * @param idName ������������
	 * @param idValue ����ֵ
	 *            
	 */
	public void deleteById(String idName,String idValue);
	/**
	 * ����ɾ��
	 * 
	 * @param idName ������������
	 * @param idValue ����ֵ����
	 *            
	 */
	public void deleteAll(String idName,List<Integer> idList);

	/**
	 * ��ȡʵ��
	 * 
	 * @param <T>
	 * @param entityClass
	 *            ʵ����
	 * @param entityId
	 *            ʵ��id
	 * @return
	 */
	public T getEntityById(Serializable entityId);

	/**
	 * ����ҳ����ȡȫ������
	 * 
	 * @return
	 */
	public List<T> findAll();

	public QueryResult<T> getScrollData(int firstResult, int maxResult, LinkedHashMap<String, String> orderby);
	
	public QueryResult<T> getScrollData(int firstResult, int maxResult, String wherehql, Object[] queryParams);
	
	public QueryResult<T> getScrollData(int firstResult, int maxResult);
	
	public QueryResult<T> getScrollData();
	/**
	 * @param firstResult ��ʼ����
	 * @param maxResult ÿҳ��ʾ����
	 * @param wherehql  where����������Ҫ����where��ֻ��Ҫ������������,���磺name = ? and password =?
	 * @param queryParams where����ֵ�ļ���
	 * @param orderby ������������
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstResult, int maxResult, String wherehql, Object[] queryParams, LinkedHashMap<String, String> orderby);
	
	/**
	 * ͨ��hql��ȡ��������
	 * 
	 * @param hql
	 * @param serializables
	 * @return
	 */
	public List<T> findEntityByHQL(String hql, Serializable... serializables);

	/**
	 * ͨ��hql��ֵ����(���صĽ�����ҽ���һ����¼)
	 * 
	 * @param hql
	 * @param serializables
	 * @return
	 */
	public T findUniqueResult(String hql, Serializable... serializables);

	/**
	 * ���������
	 * 
	 * @param hql
	 * @param serializables
	 */
	public void batchHandle(String hql, Serializable... serializables);

	/**
	 * ��ѯ
	 * 
	 * @param clazz
	 * @param proMap
	 * @param orderMap
	 * @return
	 */
	public List findObjectListByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap);

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
	 * ��ȡΨһ������ֵ
	 */
	public Long getUniqueId();

	public Long selectSequence();


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
	 * ͨ���������������ѯ��Ӧ����
	 * 
	 * @param clazz
	 * @param propNames
	 * @param propValues
	 * @return
	 */
	public List findObjectByPars(Class clazz, String[] propNames, Object[] propValues);

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
	 * ��ȡCriteria
	 * 
	 * @param clazz
	 * @return
	 */
	public Criteria getCriteria(Class clazz);

	public Page findObjectPageByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap, int pageNo, int pageSize);
}
