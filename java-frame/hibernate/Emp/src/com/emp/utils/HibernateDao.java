package com.emp.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class HibernateDao<T> {
	/**
	 * С����ר��HibernateDao
	 */
	private Session session = null;
	
	/**
	 * ���
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
	 * ɾ��
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
	 * ����Integer���͵�IDɾ��
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
	 * ����������String���͵�id ɾ��������ɾ��
	 */
	public void deleAll(Class<T> clazz,String id){
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
	 * �޸�
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
	 * ����get������һ��
	 */
	public T findOneByGet(Class<? extends Object> entityClass,Serializable id){
		session = HibernateUtils.getSession();
		@SuppressWarnings("unchecked")
		T obj = (T) session.get(entityClass, id);
		session.close();
		return obj;
	}

	/**
	 * ����load������һ��
	 */
	public T findOneByLoad(Class<? extends Object> entityClass,Serializable id){
		session = HibernateUtils.getSession();
		@SuppressWarnings("unchecked")
		T obj = (T) session.load(entityClass, id);
		session.close();
		return obj;
	}
	
	/**
	 * ����sql����ѯ�������ݣ�������ָ���Ķ��󼯺�(select *)
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
	 * ����hql����ѯ�������ݣ�������ָ���Ķ��󼯺�
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
	 * ����sql����ҳ��ѯ��������ָ���Ķ��󼯺�(select *)
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
	 * ����hql����ҳ��ѯ��������ָ���Ķ��󼯺�
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
	 * ����������ҳ��ѯ��������ָ���Ķ��󼯺�
	 */
	public List<T> getList(int currentPageNo,int onePageCount,Class<T> clazz){
		session = HibernateUtils.getSession();
		String name = clazz.getSimpleName();
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
	 * �������� ��ҳ��ģ����ѯ��������ָ���Ķ��󼯺�
	 */
	public List<T> getList(int currentPageNo,int onePageCount,String hql,Class<T> clazz){
		session = HibernateUtils.getSession();
		String name = clazz.getSimpleName();
		//System.out.println(name);
		Query query = session.createQuery("from "+name+" where 1=1"+hql);
		if(currentPageNo>0){
			query.setFirstResult((currentPageNo-1)*onePageCount).setMaxResults(onePageCount);
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		session.close();
		return list;
	}
	
	/**
	 * ��ҳ֧�֣�����sql����ѯ�ܼ�¼��
	 */
	public int getTotalCountBySql(String sql){
		String coutSql = "select count(*) cn from("+sql+")";
		session = HibernateUtils.getSession();
		Object obj = session.createSQLQuery(coutSql).uniqueResult();
		session.close();
		return Integer.parseInt(obj.toString());
	}
	
	/**
	 * ��ҳ֧�֣�����hql����ѯ�ܼ�¼��
	 */
	public int getTotalCountByHql(String hql){
		String coutSql = "select count(*) cn from("+hql+")";
		session = HibernateUtils.getSession();
		Object obj = session.createQuery(coutSql).uniqueResult();
		session.close();
		return Integer.parseInt(obj.toString());
	}
	
	/**
	 * ��ҳ֧�֣�����������ȡ�ܼ�¼��
	 */
	public Long getCount(Class<T> clazz){
		// ��ȡsession
		session = HibernateUtils.getSession();
		String name = clazz.getSimpleName();
		Query query = session.createQuery("select count(*) from "+name);
		Long count = (Long) query.uniqueResult();
		session.close();
		return count;
	}
	
	/**
	 * ��ҳ֧�֣��ܼ�¼����ģ����Ψһ��֤
	 */
	public Long getCount(String hql,Class<T> clazz){
		// ��ȡsession
		session = HibernateUtils.getSession();
		String name = clazz.getSimpleName();
		Query query = session.createQuery("select count(*) from "+name+" where 1=1 "+hql);
		Long count = (Long) query.uniqueResult();
		session.close();
		return count;
	}
}
