package com.baidu.base.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springside.core.utils.BeanUtils;

import com.baidu.base.dao.BaseDao;
import com.baidu.util.DateUtil;
import com.baidu.util.GenericsUtils;
import com.baidu.util.Page;
import com.baidu.util.QueryResult;
import com.baidu.util.WebUtils;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());

	/* (non-Javadoc)
	 * ���ݲ������Ͳ���ֵ��ȡ����
	 * @see com.baidu.base.dao.BaseDao#findObjectByPar(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	public List<T> findObjectByPar(Class clazz, String propName, Object propValue) {
		// TODO Auto-generated method stub
		Criteria c = this.getSession().createCriteria(clazz);
		if (propName != null) {
			c.add(Restrictions.eq(propName, propValue));
		}

		return c.list();
	}

	public List findObjectByPars(Class clazz, String[] propNames, Object[] propValues) {
		// TODO Auto-generated method stub
		Criteria c = this.getSession().createCriteria(clazz);
		for (int i = 0; i < propNames.length; i++) {
			c.add(Restrictions.eq(propNames[i], propValues[i]));
		}
		return c.list();
	}
	
	public List findObjectByParOrder(Class clazz, String propName, Object propValue, String orderName, String ascoOrdesc) {
		// TODO Auto-generated method stub
		Criteria c = this.getSession().createCriteria(clazz);
		c.add(Restrictions.eq(propName, propValue));
		if (orderName != null && ascoOrdesc.equals("desc")) {
			c.addOrder(Order.desc(orderName));
		} else if (orderName != null && ascoOrdesc.equals("asc")) {
			c.addOrder(Order.asc(orderName));
		}
		return c.list();
	}


	public Long selectMaxIdFromTable(Class clazz, String propertyName) {
		Criteria c = this.getSession().createCriteria(clazz);
		c.setProjection(Projections.projectionList().add(Projections.max(propertyName)));
		Object ob = c.uniqueResult();
		Long orderMax = Long.parseLong(WebUtils.getRandomId());
		if (ob != null) {
			orderMax = Long.parseLong(ob.toString());
		}
		return orderMax;
	}

	public Long selectSequence() {
		Object object = this.getSession().createSQLQuery("select seq_number.nextval from dual").uniqueResult();

		return Long.parseLong(object.toString());
	}

	public Object save(Object obj, Class clazz, String key) {
		// TODO Auto-generated method stub
		BeanWrapper bw = new BeanWrapperImpl(obj);
		Object provalue = bw.getPropertyValue(key);
		if (provalue == null || provalue.equals("")) {
			// Long id=this.selectMaxIdFromTable(clazz, key)+1;
			// selectSequence();
			Long id = Long.parseLong(DateUtil.getDateToStringFull2(new Date()) + selectSequence());
			// String id=DateUtil.getDateToStringFull2(new
			// Date())+System.currentTimeMillis()+DateUtil.getRandom(1000,9999);
			bw.setPropertyValue(key, id.toString());
			this.getHibernateTemplate().save(obj);
			// this.getHibernateTemplate().flush();
		} else {
			getHibernateTemplate().merge(obj);
			// getHibernateTemplate().flush();
		}
		return obj;
	}


	public List findObjectByLikeParsOrder(Class clazz, String[] propNames, Object[] propValues, String[] orderNames, String[] ascoOrdescs) {
		// TODO Auto-generated method stub
		Criteria c = this.getSession().createCriteria(clazz);
		for (int i = 0; i < propNames.length; i++) {
			if (propValues[i].toString().length() > 0) {
				c.add(Restrictions.ilike(propNames[i], propValues[i].toString(), MatchMode.ANYWHERE));
			}

		}
		for (int i = 0; i < orderNames.length; i++) {
			String orderName = orderNames[i];
			String ascoOrdesc = ascoOrdescs[i];
			if (orderName != null && ascoOrdesc.equals("desc")) {
				c.addOrder(Order.desc(orderName));
			} else if (orderName != null && ascoOrdesc.equals("asc")) {
				c.addOrder(Order.asc(orderName));
			}
		}
		return c.list();
	}

	public List findObjectByParsOrder(Class clazz, String[] propNames, Object[] propValues, String orderName, String ascoOrdesc) {
		// TODO Auto-generated method stub
		Criteria c = this.getSession().createCriteria(clazz);
		for (int i = 0; i < propNames.length; i++) {
			c.add(Restrictions.eq(propNames[i], propValues[i]));
		}
		if (orderName != null && ascoOrdesc.equals("desc")) {
			c.addOrder(Order.desc(orderName));
		} else if (orderName != null && ascoOrdesc.equals("asc")) {
			c.addOrder(Order.asc(orderName));
		}
		return c.list();
	}

	public List findObjectListByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap) {
		// TODO Auto-generated method stub
		Criteria c = this.getSession().createCriteria(clazz);
		if (proMap != null) {
			Set set = proMap.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Object obkey = it.next();
				Object obvalue = proMap.get(obkey);
				c.add(Restrictions.eq(obkey.toString(), obvalue));
			}
		}
		if (orderMap != null) {
			Set set1 = orderMap.keySet();
			Iterator it1 = set1.iterator();
			while (it1.hasNext()) {
				Object obkey = it1.next();
				Object obvalue = orderMap.get(obkey);
				if (obvalue != null && obvalue.equals("desc")) {
					c.addOrder(Order.desc(obkey.toString()));
				} else if (obvalue != null && obvalue.equals("asc")) {
					c.addOrder(Order.asc(obkey.toString()));
				}
			}
		}
		return c.list();
	}

	public Long getUniqueId() {
		Long id = Long.parseLong(DateUtil.getDateToStringFull2(new Date()) + selectSequence());
		return id;
	}

	public Criteria getCriteria(Class clazz) {
		Criteria c = this.getSession().createCriteria(clazz);
		return c;
	}

	

	public void clear() {
		getSession().clear();
	}

	public void delete(Object obj) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(obj);
	}
	
	public void deleteById(String idName,String idValue) {
		String hql="delete from "+getEntityName(entityClass)+" where "+idName+" = ? ";
		Query query = getSession().createQuery(hql);
		query.setString(0,idValue);
		query.executeUpdate();
	}
	
	
	
	/**
	 * @param idName javaBean�����������ƣ��������ݿ��ֶ����ƣ�
	 * @param idList ����ֵ����
	 */
	public void deleteAll(String idName,List<Integer> idList) {
		String hql="delete from "+getEntityName(entityClass)+" where "+idName+" in (:idList)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("idList",idList);
		query.executeUpdate();
	}

	public T getEntityById(Serializable entityId) {
		if (entityId == null) {
			return null;
		}
		return (T) getSession().get(this.entityClass, entityId);
	}

	public long getCount() {
		return ((Number) getSession().createQuery(//
				"SELECT count(o) FROM " + getEntityName(entityClass) + " o")//
				.uniqueResult()).longValue();
	}
	
	@Override
	public Serializable save(T entity) {
		return getSession().save(entity);
	}
	
	@Override
	public void update(T entity) {
		getSession().update(entity);
	}
	
	public void saveOrUpdateEntity(T t) {
		getSession().saveOrUpdate(t);
	}

	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + getEntityName(entityClass))//
				.list();
	}

	public QueryResult<T> getScrollData(int firstResult, int maxResult, LinkedHashMap<String, String> orderby) {
		return getScrollData(firstResult, maxResult, null, null, orderby);
	}

	public QueryResult<T> getScrollData(int firstResult, int maxResult, String wherehql, Object[] queryParams) {
		return getScrollData(firstResult, maxResult, wherehql, queryParams, null);
	}

	public QueryResult<T> getScrollData(int firstResult, int maxResult) {
		return getScrollData(firstResult, maxResult, null, null, null);
	}

	public QueryResult<T> getScrollData() {
		return getScrollData(-1, -1);
	}

	/**
	 * @param firstResult ��ʼ����
	 * @param maxResult ÿҳ��ʾ����
	 * @param wherehql  where����������Ҫ����where��ֻ��Ҫ������������,���磺name = ? and password =?
	 * @param queryParams where����ֵ�ļ���
	 * @param orderby ������������
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstResult, int maxResult, String wherehql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		QueryResult qr = new QueryResult<T>();
		String entityname = getEntityName(this.entityClass);
		String whereql = wherehql == null || "".equals(wherehql.trim()) ? "" : "where " + wherehql;
		Query query = getSession().createQuery("select o from " + entityname + " o " + whereql + buildOrderby(orderby));
		setQueryParams(query, queryParams);
		if (firstResult != -1 && maxResult != -1)
			query.setFirstResult(firstResult).setMaxResults(maxResult);
		qr.setResultlist(query.list());
		query = getSession().createQuery("select count(*) from " + entityname + " o " + whereql);
		setQueryParams(query, queryParams);
		qr.setTotalrecord((Long) query.uniqueResult());
		return qr;
	}

	

	/**
	 * ƴ�ղ�ѯ����
	 * @param query
	 * @param queryParams
	 */
	protected static void setQueryParams(Query query, Object[] queryParams) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i, queryParams[i]);
			}
		}
	}

	/**
	 * ƴ����������
	 * @param orderby
	 * @return
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			orderbyql.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}

	protected static <E> String getEntityName(Class<E> clazz) {
		String entityname = clazz.getSimpleName();
		return entityname;
	}
	
	public List<T> findEntityByHQL(String hql, Serializable... serializables) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, serializables);
		return query.list();
	}

	public T findUniqueResult(String hql, Serializable... serializables) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, serializables);
		return (T) query.uniqueResult();
	}

	public void batchHandle(String hql, Serializable... serializables) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, serializables);
		query.executeUpdate();
	}


	/* 
	 * ��ʱ����
	 * (non-Javadoc)
	 * @see com.baidu.base.dao.BaseDao#findObjectPageByParamAndOrder(java.lang.Class, java.util.LinkedHashMap, java.util.LinkedHashMap, int, int)
	 */
	public Page findObjectPageByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Criteria c = this.getSession().createCriteria(clazz);
		if (proMap != null) {
			Set set = proMap.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Object obkey = it.next();
				Object obvalue = proMap.get(obkey);
				c.add(Restrictions.eq(obkey.toString(), obvalue));
			}
		}
		if (orderMap != null) {
			Set set1 = orderMap.keySet();
			Iterator it1 = set1.iterator();
			while (it1.hasNext()) {
				Object obkey = it1.next();
				Object obvalue = orderMap.get(obkey);
				if (obvalue != null && obvalue.equals("desc")) {
					c.addOrder(Order.desc(obkey.toString()));
				} else if (obvalue != null && obvalue.equals("asc")) {
					c.addOrder(Order.asc(obkey.toString()));
				}
			}
		}
		return pagedQuery(c, pageNo, pageSize);
		// return c.list();
	}

	/**
	 * ��ʱ������ ��ҳ��ѯ������ʹ������ò�ѯ�����������<code>Criteria</code>.
	 * 
	 * @param pageNo
	 *            ҳ��,��1��ʼ.
	 * @return ���ܼ�¼���͵�ǰҳ���ݵ�Page����.
	 */
	public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) {
		Assert.notNull(criteria);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		CriteriaImpl impl = (CriteriaImpl) criteria;
		ResultTransformer resultTransformer = impl.getResultTransformer();
		// �Ȱ�Projection��OrderBy����ȡ����,���������ִ��Count����
		Projection projection = impl.getProjection();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List) BeanUtils.forceGetProperty(impl, "orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
			BeanUtils.forceSetProperty(impl, "resultTransformer", resultTransformer); // ���������bug
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// ִ�в�ѯ
		Object uniqueObject = criteria.setProjection(Projections.rowCount()).uniqueResult();
		if (uniqueObject == null) {
			throw new RuntimeException(impl.getClass().getName() + "�ڳ־û����������޷���ȡ������ʵ���Ƿ�ӳ����ȷ.");
		}
		int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();

		// ��֮ǰ��Projection��OrderBy�����������ȥ
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
			BeanUtils.forceSetProperty(impl, "resultTransformer", resultTransformer);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// ���ط�ҳ����
		if (totalCount < 1)
			return new Page();

		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List list = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
		return new Page(pageNo, totalCount, pageSize, list);
	}
	
	
}
