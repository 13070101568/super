package com.bee.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class HibernateDao<T> {
	/**
	 * Hibernate������
	 * 
	 * С����ר��DaoHibernateDao<T>
	 * by 1405javaB ���� 
	 * ����è�� 2015-09-07 by JiangYu
	 */
	private Session session = null;

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:39:36
	 * ���ܣ����
	 * @param entityObj��ʵ�������
	 */
	public void addOne(T entityObj){
		// ��ȡsession
		session = HibernateUtils.getSession();
		// ������
		Transaction tran = session.beginTransaction();
		try{
			session.save(entityObj);
			tran.commit();
		}catch(Exception e){
			tran.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:39:36
	 * ���ܣ�ɾ��
	 * @param entityObj��ʵ�������
	 */
	public void remove(T entityObj){
		// ��ȡsession
		session = HibernateUtils.getSession();
		// ������
		session.beginTransaction();
		try{
			session.delete(entityObj);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:41:06
	 * ���ܣ�����Integer���͵�IDɾ��
	 * @param id 
	 * @param clazz
	 */
	public void removeById(Integer id,Class<T> clazz){
		session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
	    Object obj = session.get(clazz, id);
		try {
		   session.delete(obj);
		   tr.commit();
		} catch (Exception e) {
			tr.rollback();
		}finally{
			session.close();
		}
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:41:51
	 * ���ܣ�����������String���͵�id ɾ��������ɾ��
	 * @param clazz
	 * @param id
	 */
	public void removeAll(Class<T> clazz,String id){
		// ��ȡsession
		session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		String name = clazz.getSimpleName();
		Query query = session.createQuery("delete from "+name+" where id in("+id+")");
		try {
			query.executeUpdate();//ִ�и��£�ɾ�����޸ģ�
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
		}finally{
			session.close();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:42:38
	 * ���ܣ��޸�
	 * @param entityObj��ʵ�������
	 */
	public void modify(T entityObj){
		// ��ȡsession
		session = HibernateUtils.getSession();
		// ������
		session.beginTransaction();
		try{
			session.update(entityObj);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:53:31
	 * ���ܣ�����get������һ��
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T findOneByGet(Class<? extends Object> entityClass,Serializable id){
		session = HibernateUtils.getSession();
		@SuppressWarnings("unchecked")
		T obj = (T) session.get(entityClass, id);
		session.close();
		return obj;
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:54:18
	 * ���ܣ�����load������һ��
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T findOneByLoad(Class<? extends Object> entityClass,Serializable id){
		session = HibernateUtils.getSession();
		@SuppressWarnings("unchecked")
		T obj = (T) session.load(entityClass, id);
		session.close();
		return obj;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:54:57
	 * ���ܣ�����sql����ѯ�������ݣ�������ָ���Ķ��󼯺�(select *)
	 * @param entityClass
	 * @param sql
	 * @return
	 */
	public List<T> findAllBySql(Class<T> entityClass,String sql){
		// ��ȡsession
		session = HibernateUtils.getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(entityClass);
		@SuppressWarnings("unchecked")
		List<T> list = sqlQuery.list();
		session.close();
		return list;
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:55:11
	 * ���ܣ�����HQL��������ѯһ��
	 * @param hql
	 * @param args
	 * @return
	 */
	public T findOneByHql(String hql,String...args){
		// ��ȡsession
		session = HibernateUtils.getSession();
		Query q = session.createQuery(hql);
		for(int i=0;i<args.length;i++){
			q.setString(i, args[i]);
		}
		@SuppressWarnings("unchecked")
		List<T> list = q.list();
		session.close();
		return list.get(0);
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:55:11
	 * ���ܣ�����HQL��������ѯһ��
	 * @param hql
	 * @param args
	 * @return
	 */
	public boolean findOneByHql2(String hql,String...args){
		// ��ȡsession
		session = HibernateUtils.getSession();
		Query q = session.createQuery(hql);
		for(int i=0;i<args.length;i++){
			q.setParameter(i, args[i]);//setString
		}
		@SuppressWarnings("unchecked")
		List<T> list = q.list();
		session.close();
		if(list.size()==0){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:55:24
	 * ���ܣ�����SQL��������ѯһ��
	 * @param sql
	 * @param args
	 * @return
	 */
	public Object findOneBySql(String sql,String...args){
		session = HibernateUtils.getSession(); 
		Query query = session.createSQLQuery(sql);
				
		 for(int i=0;i<args.length;i++){
			 query.setParameter(i, args[i]);
		 }
	
		Object result =  query.uniqueResult();
		session.close();

		return result;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:55:24
	 * ���ܣ�����SQL��������ѯһ��
	 * @param sql
	 * @param args
	 * @return
	 */
	public T findOneBySql(Class<T> entityClass,String sql,String...args){
		session = HibernateUtils.getSession(); 
		Query query = session.createSQLQuery(sql).addEntity(entityClass);
		
		for(int i=0;i<args.length;i++){
			query.setParameter(i, args[i]);
		}
		
		@SuppressWarnings("unchecked")
		T result =  (T)query.uniqueResult();
		session.close();
		
		return result;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:56:17
	 * ���ܣ�����hql����ѯ�������ݣ�������ָ���Ķ��󼯺�
	 * @param hql
	 * @return
	 */
	public List<T> findAll(String hql){
		// ��ȡsession
		session = HibernateUtils.getSession();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		session.close();
		return list;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:56:35
	 * ���ܣ�����sql����ҳ��ѯ��������ָ���Ķ��󼯺�(select *)
	 * @param entityClass
	 * @param sql
	 * @param currentPageNo
	 * @param onePageCount
	 * @return
	 */
	public List<T> queryPageBySQL(Class<T> entityClass,String sql,int currentPageNo,int onePageCount){
		// ��ȡsession
		session = HibernateUtils.getSession();
		//addEntity():��ѯ�Ľ�������ĸ�����װ��ʹ��SQL��ʱ����Ҫ���������������ָ����
		SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(sql).addEntity(
			entityClass).setFirstResult(onePageCount*(currentPageNo-1)).setMaxResults(onePageCount);
		@SuppressWarnings("unchecked")
		List<T> list = sqlQuery.list();
		session.close();
		return list;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:56:50
	 * ���ܣ�����hql����ҳ��ѯ��������ָ���Ķ��󼯺�
	 * @param hql
	 * @param currentPageNo
	 * @param onePageCount
	 * @return
	 */
	public List<T> queryPage(String hql,int currentPageNo,int onePageCount){
		session = HibernateUtils.getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(onePageCount*(currentPageNo-1)).setMaxResults(onePageCount);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		session.close();
		return list;
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:57:02
	 * ���ܣ�����������ҳ��ѯ��������ָ���Ķ��󼯺�
	 * @param currentPageNo
	 * @param onePageCount
	 * @param clazz
	 * @return
	 */
	public List<T> getPageList(int currentPageNo,int onePageCount,Class<T> entityClass){
		session = HibernateUtils.getSession();
		String name = entityClass.getSimpleName();
		 Query query = session.createQuery("from "+name);
		if(currentPageNo>0){
			query.setFirstResult((currentPageNo-1)*onePageCount).setMaxResults(onePageCount);
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		session.close();
		return list;	 
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:57:38
	 * ���ܣ��������� ��ҳ��ģ����ѯ��������ָ���Ķ��󼯺�
	 * @param currentPageNo
	 * @param onePageCount
	 * @param hql
	 * @param clazz
	 * @return
	 */
	public List<T> getPageList(Class<T> entityClass,int currentPageNo,int onePageCount,String hql){
		session = HibernateUtils.getSession();
		String name = entityClass.getSimpleName();
		//System.out.println(name);
		Query query = session.createQuery("from "+name+" where 1=1 "+hql);
		if(currentPageNo>0){
			query.setFirstResult((currentPageNo-1)*onePageCount).setMaxResults(onePageCount);
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		session.close();
		return list;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:57:55
	 * ���ܣ���ҳ֧�֣�����sql����ѯ�ܼ�¼��
	 * @param sql
	 * @return
	 */
	public int getTotalCountBySql(String sql){
		String coutSql = "select count(*) cn from("+sql+")";
		session = HibernateUtils.getSession();
		Object obj = session.createSQLQuery(coutSql).uniqueResult();
		session.close();
		return Integer.parseInt(obj.toString());
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:58:14
	 * ���ܣ���ҳ֧�֣�����hql����ѯ�ܼ�¼��
	 * @param hql
	 * @return
	 */
	public int getTotalCountByHql(String hql){
		String coutSql = "select count(*) cn from("+hql+")";
		session = HibernateUtils.getSession();
		Object obj = session.createQuery(coutSql).uniqueResult();
		session.close();
		return Integer.parseInt(obj.toString());
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:58:31
	 * ���ܣ���ҳ֧�֣�����������ȡ�ܼ�¼��
	 * @param clazz
	 * @return
	 */
	public int getCount(Class<T> entityClass){
		// ��ȡsession
		session = HibernateUtils.getSession();
		String name = entityClass.getSimpleName();
		Query query = session.createQuery("select count(*) from "+name);
		Long count = (Long) query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-9-07����4:58:57
	 * ���ܣ���ҳ֧�֣��ܼ�¼����ģ����Ψһ��֤
	 * @param hql
	 * @param clazz
	 * @return
	 */
	public int getCount(String hql,Class<T> entityClass){
		// ��ȡsession
		session = HibernateUtils.getSession();
		String name = entityClass.getSimpleName();
		Query query = session.createQuery("select count(*) from "+name+" where 1=1 "+hql);
		Long count = (Long) query.uniqueResult();
		session.close();
		return count.intValue();
	}
}
