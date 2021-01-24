package com.jiangyu.utils;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * SSH��֤������
 * 
 * С����ר��ssh----JiangYuSpringDao<T>
 * by 1405javaB ���� 
 * ����è�� 2015-09-22 by JiangYu
 * 
 * ע�⣺������ɾ�Ĵ�ע�ͣ����������������
 */
public class JiangYuSpringDao<T> extends HibernateDaoSupport implements JiangYuIspringDao<T> {
	

	public void setSuperSessionFactory(SessionFactory sessionFactory){
		
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:15:18
	 * ���ܣ���ѯ��������
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(String hql){
		try{
			return getHibernateTemplate().find(hql);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:15:56
	 * ���ܣ����棨��������ӣ�һ������
	 * @param entityObj
	 */
	public void addOne(T entityObj){
		try{
			getHibernateTemplate().save(entityObj);
			//saveOrUpdate����id��update��ûid��save
			//getHibernateTemplate().saveOrUpdate(entityObj);
		
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:16:31
	 * ���ܣ��޸�һ������
	 * @param entityObj
	 */
	public void modify(T entityObj){
		try{
			getHibernateTemplate().update(entityObj);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:16:46
	 * ���ܣ�ɾ��һ������
	 * @param entityObj
	 */
	public void removeOne(T entityObj){
		try{
			getHibernateTemplate().delete(entityObj);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:23:17
	 * ���ܣ���ID ɾ��һ������
	 * @param id
	 * @param entityClass
	 */
	public void removeOneById(Integer id,Class<T> entityClass){
		try{
			Object object = getHibernateTemplate().get(entityClass, id);
			getHibernateTemplate().delete(object);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:17:08
	 * ���ܣ������󼯺� �������
	 * @param entities
	 */
	public void addAll(Collection<T> entities){
		try{
			getHibernateTemplate().saveOrUpdateAll(entities);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:17:08
	 * ���ܣ������󼯺� ����ɾ��
	 * @param entities
	 */
	public void removeAll(Collection<T> entities){
		try{
			getHibernateTemplate().deleteAll(entities);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:32:31
	 * ���ܣ���id�ַ��� ����ɾ��
	 * @param entityClass
	 * @param ids
	 */
	public void removeAllById(Class<T> entityClass,String ids){
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String name = entityClass.getSimpleName();
		Query query = session.createQuery("delete from "+name+" where id in("+ids+")");
		query.executeUpdate();//ִ�и��£�ɾ�����޸ģ�
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����5:15:46
	 * ���ܣ���id��Integer���� ����ɾ��
	 * @param ids
	 * @param entityClass
	 */
	public void removeBacth(final List<Integer> ids,final Class<T> entityClass) {
		getHibernateTemplate().execute(new HibernateCallback<T>() {

			public T doInHibernate(Session session)
					throws HibernateException, SQLException {
				String name = entityClass.getSimpleName();
				String hql = "delete from "+name+" where id in(:ids)";
				Query query = session.createQuery(hql);
				query.setParameterList("ids", ids);
				query.executeUpdate();
				return null;
			}
		});
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����5:15:46
	 * ���ܣ���id��String���� ����ɾ��
	 * @param ids
	 * @param entityClass
	 */
	public void removeBacth2(final List<String> ids,final Class<T> entityClass) {
		getHibernateTemplate().execute(new HibernateCallback<T>() {
			
			public T doInHibernate(Session session)
					throws HibernateException, SQLException {
				String name = entityClass.getSimpleName();
				String hql = "delete from "+name+" where id in(:ids)";
				Query query = session.createQuery(hql);
				List<Integer> list = new ArrayList<Integer>();
				for (String s : ids) {
					list.add(new Integer(s));
				}
				query.setParameterList("ids", list);
				query.executeUpdate();
				return null;
			}
		});
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:33:16
	 * ���ܣ�get��ʽ ��ѯһ��
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T get(Class<T> entityClass,Serializable id){
		try{
			return (T) getHibernateTemplate().get(entityClass, id);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:33:34
	 * ���ܣ�load��ʽ��ѯһ��
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T load(Class<T> entityClass,Serializable id){
		try{
			return (T) getHibernateTemplate().load(entityClass, id);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:59:26
	 * ���ܣ���������ѯһ��
	 * @param hql
	 * @param args
	 * @return
	 */
	public T findOne(String hql,String...args){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery(hql);
		for(int i=0;i<args.length;i++){
			q.setString(i, args[i]);
		}
		@SuppressWarnings("unchecked")
		List<T> list = q.list();
		return list.get(0);
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:59:26
	 * ���ܣ���������ѯһ��
	 * @param hql
	 * @param args
	 * @return
	 */
	public boolean findOneBoolean(String hql,String...args){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Query q = session.createQuery(hql);
		for(int i=0;i<args.length;i++){
			q.setParameter(i, args[i]);//setString
		}
		@SuppressWarnings("unchecked")
		List<T> list = q.list();
		if(list.size()==0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����7:04:18
	 * ���ܣ���������ѯ
	 * @param hql
	 * @param pramas
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public List<T[]> findByConditionSql(String sql, Object... pramas) {
		List<T> list = null ;
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		for(int i=0;i<pramas.length;i++){
			query.setParameter(i, pramas[i]);
		}
		List<T[]> list2 = query.list();
//		try{
//			list  =	(List<T>) getHibernateTemplate().execute(new HibernateCallback<T>(){
//				public T doInHibernate(Session session)
//						throws HibernateException, SQLException {
//					SQLQuery query = session.createSQLQuery(sql);
//					for(int i=0;i<pramas.length;i++){
//						query.setParameter(i, pramas[i]);
//					}
//					return (T) query.list();
//				}	
//			});
//		}catch(DataAccessException e){
//			e.printStackTrace();
//		}
		return list2;
	}
	
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����7:04:18
	 * ���ܣ���������ѯ
	 * @param hql
	 * @param pramas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByConditionHql(final String hql, final Object... pramas) {
		List<T> list = null ;
		try{
			list  =	(List<T>) getHibernateTemplate().execute(new HibernateCallback<T>(){
				public T doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					for(int i=0;i<pramas.length;i++){
						query.setParameter(i, pramas[i]);
					}
					return (T) query.list();
				}	
			});
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:47:09
	 * ���ܣ���ҳ֧�� ��ȡ�ܼ�¼��
	 * @param entityClass
	 * @return
	 */
	public int getCount(Class<T> entityClass){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String name = entityClass.getSimpleName();
		Query query = session.createQuery("select count(*) from "+name);
		Long count = (Long) query.uniqueResult();
		return count.intValue();
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-10-13����4:47:09
	 * ���ܣ���ҳ֧�� ��HQL��ȡ�ܼ�¼��
	 * @param entityClass
	 * @return
	 */
	public int getCount(String hql){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Query query = session.createQuery(hql);
		
		Long count = (Long) query.uniqueResult();
		return count.intValue();
	}
	
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
	public List<T> queryBySql(final String sql,final Class<T> entityClass){
		try{
			getHibernateTemplate().execute(new HibernateCallback<T>() {
				@SuppressWarnings("unchecked")
				public T doInHibernate(Session session) throws HibernateException,SQLException {
					//�����ڲ��������ϱߵ� ������final��
					SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(entityClass);
					return (T) sqlQuery.list();
				}
			});
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return null;
	}
	
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
	public List<T> queryPage(String hql,int currentPageNo,int onePageCount){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(onePageCount*(currentPageNo-1)).setMaxResults(onePageCount);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}
	
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
	public List<T> queryPage(Class<T> entityClass,int currentPageNo,int onePageCount){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String name = entityClass.getSimpleName();
		Query query = session.createQuery("from "+name);
		if(currentPageNo>0){
			query.setFirstResult((currentPageNo-1)*onePageCount).setMaxResults(onePageCount);
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}
	
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
	public List<T> getPageList(Class<T> entityClass,int currentPageNo,int onePageCount,String hql){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String name = entityClass.getSimpleName();
		Query query = session.createQuery(" from "+name+" where 1=1 "+hql);
		if(currentPageNo>0){
			query.setFirstResult((currentPageNo-1)*onePageCount).setMaxResults(onePageCount);
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}
	
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
	@SuppressWarnings("unchecked")
	public List<T> getPageList(final Class<T> entityClass,final String column,
			final int currentPageNo,final int onePageCount,final String condition) {
		
		return (List<T>) getHibernateTemplate().execute(
			new HibernateCallback<T>(){
				public T doInHibernate(Session session)throws HibernateException, SQLException {
					List<String> list = new ArrayList<String>();
					String name = entityClass.getSimpleName();
					String hql = "from "+name+" where 1=1 ";
					if(condition != null && condition.length() > 0) {
						hql += " and "+column+" like ? ";
						list.add("%" + condition + "%");		
					}
				Query query = session.createQuery(hql);
				query.setFirstResult((currentPageNo-1)*onePageCount)
						.setMaxResults(onePageCount);
				for (int i = 0; i < list.size(); i++) {
					query.setParameter(i, list.get(i));
				}
				return (T) query.list();
			}	
		});
	}
	
}
