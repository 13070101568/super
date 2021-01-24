package com.baidu.bmx.utils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 * ���ߣ�����³
 * ���ܣ�JPA+SpringMVC+Spring�Ĺ��߰��־ò�
 * ʱ�䣺2015��10��23��
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> implements Dao<T> {

	
	private Class<T> clazz ;
	
	@PersistenceContext
	private EntityManager em ;
	
	public BaseDao()
	{
		ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
		clazz = (Class<T>)pt.getActualTypeArguments()[0];
	}
	
	/**
	 * ���ߣ�����³
	 * ���ܣ�CRUD
	 * ʱ�䣺2015��10��23��
	 */
	//���
	@Override
	public void save(T t) {
		em.persist(t);
	}

	//����
	@Override
	public void update(T t) {
		em.merge(t);
	}

	//ɾ��
	@Override
	public void delete(T t) {
		em.remove(t);
	}

	//��ID����
	@Override
	public T findById(int id) {
		
		return em.find(clazz, id);
	}

	//��������
	@Override
	public List<T> findAll() {
		String jpql = "from "+ clazz.getSimpleName() ;
		return (List<T>)em.createQuery(jpql).getResultList();
	}

	//��ҳ����
	@Override
	public PageBean<T> findByPage(int page, int pageSize, String url) {
		
		PageBean<T> pageBean=new PageBean<T>();
		String name=clazz.getSimpleName();
		
		//�ڼ�ҳ
		pageBean.setPage(page);
		//ÿҳ����������
		pageBean.setPageSize(pageSize);
		pageBean.setUrl(url);
		
		//From Employee e left outer join fetch e.dept
		String hql="from " + name;
		Query query = em.createQuery(hql).setFirstResult((page-1)*pageSize).setMaxResults(pageSize);
		List<T> list = query.getResultList();
		
		Long totalNum = (Long) em.createQuery("select count(*) from "+name).getSingleResult();
		int totalNums = totalNum.intValue();
		int totalPages=totalNums%pageSize==0 ? totalNums/pageSize : totalNums/pageSize+1;
		
		//��ҳ��
		pageBean.setTotalPages(totalPages);
		//�ܼ�¼��
		pageBean.setTotalNums(totalNums);
		//��ŵ�ǰҳ���ݵļ���
		pageBean.setList(list);
		pageBean.setActualPageSize(list.size());
		
		return pageBean;
	}

}
