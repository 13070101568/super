package com.jiangyu.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface JiangYuIspringDao<T> {

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:15:18
	 * ���ܣ���ѯ��������
	 * @param hql
	 * @return
	 */
	
	public abstract List<T> findAll(String hql);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:15:56
	 * ���ܣ����棨��������ӣ�һ������
	 * @param entityObj
	 */
	public abstract void addOne(T entityObj);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:16:31
	 * ���ܣ��޸�һ������
	 * @param entityObj
	 */
	public abstract void modify(T entityObj);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:16:46
	 * ���ܣ�ɾ��һ������
	 * @param entityObj
	 */
	public abstract void removeOne(T entityObj);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:23:17
	 * ���ܣ���ID ɾ��һ������
	 * @param id
	 * @param entityClass
	 */
	public abstract void removeOneById(Integer id, Class<T> entityClass);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:17:08
	 * ���ܣ������󼯺� �������
	 * @param entities
	 */
	//public abstract void addAll(Collection<T> entities);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:17:08
	 * ���ܣ������󼯺� ����ɾ��
	 * @param entities
	 */
	public abstract void removeAll(Collection<T> entities);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:32:31
	 * ���ܣ���id�ַ��� ����ɾ��
	 * @param entityClass
	 * @param ids
	 */
	public abstract void removeAllById(Class<T> entityClass, String ids);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����5:15:46
	 * ���ܣ���id��Integer���� ����ɾ��
	 * @param ids
	 * @param entityClass
	 */
	public abstract void removeBacth(final List<Integer> ids,
			final Class<T> entityClass);
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����5:15:46
	 * ���ܣ���id��Integer���� ����ɾ��
	 * @param ids
	 * @param entityClass
	 */
	public abstract void removeBacth2(final List<String> ids,
			final Class<T> entityClass);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:33:16
	 * ���ܣ�get��ʽ ��ѯһ��
	 * @param entityClass
	 * @param id
	 * @return
	 */
	
	public abstract T get(Class<T> entityClass, Serializable id);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:33:34
	 * ���ܣ�load��ʽ��ѯһ��
	 * @param entityClass
	 * @param id
	 * @return
	 */
	
	public abstract T load(Class<T> entityClass, Serializable id);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:59:26
	 * ���ܣ���������ѯһ��
	 * @param hql
	 * @param args
	 * @return
	 */
	public abstract T findOne(String hql, String... args);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:59:26
	 * ���ܣ���������ѯһ��
	 * @param hql
	 * @param args
	 * @return
	 */
	public abstract boolean findOneBoolean(String hql, String... args);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����7:04:18
	 * ���ܣ���������ѯ
	 * @param hql
	 * @param pramas
	 * @return
	 */
	
	public abstract List<T> findByConditionHql(final String hql,
			final Object... pramas);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����7:04:18
	 * ���ܣ���������ѯ
	 * @param hql
	 * @param pramas
	 * @return
	 */
	
	public abstract List<T[]> findByConditionSql(String sql,
			Object... pramas);
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:47:09
	 * ���ܣ���ҳ֧�� ��ȡ�ܼ�¼��
	 * @param entityClass
	 * @return
	 */
	public abstract int getCount(Class<T> entityClass);
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:47:09
	 * ���ܣ���ҳ֧�� ��ȡ�ܼ�¼��
	 * @param entityClass
	 * @return
	 */
	public abstract int getCount(String hql);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:49:43
	 * ���ܣ�����SQL����ҳ��ѯ
	 * @param hql
	 * @param currentPageNo
	 * @param onePageCount
	 * @return
	 */
	public abstract List<T> queryBySql(final String sql,
			final Class<T> entityClass);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:49:43
	 * ���ܣ�����HQL����ҳ��ѯ
	 * @param hql
	 * @param currentPageNo
	 * @param onePageCount
	 * @return
	 */
	public abstract List<T> queryPage(String hql, int currentPageNo,
			int onePageCount);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:49:43
	 * ���ܣ�����������ҳ��ѯ
	 * @param hql
	 * @param currentPageNo
	 * @param onePageCount
	 * @return
	 */
	public abstract List<T> queryPage(Class<T> entityClass, int currentPageNo,
			int onePageCount);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����5:09:13
	 * ���ܣ�ģ����ѯ ����ҳ
	 * @param entityClass
	 * @param currentPageNo
	 * @param onePageCount
	 * @param hql
	 * @return
	 */
	public abstract List<T> getPageList(Class<T> entityClass,
			int currentPageNo, int onePageCount, String hql);

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����5:09:13
	 * ���ܣ�ģ����ѯ ����ҳ
	 * @param entityClass
	 * @param currentPageNo
	 * @param onePageCount
	 * @param hql
	 * @return
	 */

	public abstract List<T> getPageList(final Class<T> entityClass,
			final String column, final int currentPageNo,
			final int onePageCount, final String condition);

}