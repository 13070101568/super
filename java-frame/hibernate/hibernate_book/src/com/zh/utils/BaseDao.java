package com.zh.utils;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.zh.utils.HibernateUtils;
import com.zh.utils.PageBean;


public class BaseDao {
	
	/**
	 * ���ܣ���ѯȫ������( ��List��ʽ���� + �޷�ҳ )
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-15
	 * @user lenovo	
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> clazz){
			
		Session session = HibernateUtils.getFactory().openSession();
		
		List<T> list = null;
		try {list = session.createCriteria(clazz).list();
		} catch (Exception e) {System.err.print("when execute getList catch an error!");
			System.out.println(e.getMessage());
		}finally{
			if(session != null && session.isOpen())session.close();
		}
		
		return list;					
	}
	
	/**
	 * ���ܣ���ѯ����(��pageBean��ʽ����+��ҳ)
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-15
	 * @user lenovo	
	 */
	@SuppressWarnings("unchecked")
	public <T> PageBean<T> findByPage(Integer page,Integer pageSize,Class<T> clazz){	
		
		List<T> list = null;Integer totalNums = 0;
		Session session = HibernateUtils.getFactory().openSession();
		
		try {
			totalNums = findAll(clazz) != null ? findAll(clazz).size():0;
			list = session.createCriteria(clazz).setFirstResult((page-1)*pageSize).setMaxResults(pageSize).list();
		} catch (Exception e) {
			System.out.println("when EXECUTE findByPage : "+e.getMessage());}
		if(list == null || list.isEmpty())System.out.println("���ݿ�Ϊ�ջ�Page������ҳ����Χ!");
		
		return pageBean(page, pageSize, totalNums, list);					
	} 
		
	/**
	 * ���ܣ���ѯ����(��pageBean��ʽ����+��ҳ+ģ����ѯ)
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-15
	 * @user lenovo	
	 */
	@SuppressWarnings("unchecked")
	public <T> PageBean<T> findByPageAndCondition(Integer page,Integer pageSize,Class<T> clazz,Map<String,String> map){	
		
		List<T> list = null;Integer totalNums = 0;
		Session session = HibernateUtils.getFactory().openSession();

		try {Criteria query = session.createCriteria(clazz);
			if(map != null && map.size()>0){
				for (String key : map.keySet()) {
					query.add(Restrictions.like(key, ("%" + map.get(key).trim() + "%")));
				}
			}
			totalNums = query.list() != null ? query.list().size() : 0;
			list = query.setFirstResult((page-1)*pageSize).setMaxResults(pageSize).list();	
		} catch (Exception e) {
			System.out.println("whern EXECUTE findByPageAndCondition : "+e.getMessage());}
		if(list == null || list.isEmpty())System.out.println("û����֮ƥ������ݻ�Page������ҳ����Χ!");
		
		return pageBean(page, pageSize, totalNums, list);					
	} 
	
	/**
	 * ���ܣ�����Id��ѯ����
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-15
	 * @user lenovo	
	 */
	@SuppressWarnings("unchecked")
	public <T> T findById(Class<T> clazz,String id){			
		
		Session session = HibernateUtils.getFactory().openSession();
		
		int idd = -1;T obj = null;

		try {idd = Integer.parseInt(id);
		} catch (Exception e) {System.out.println(" 'id' convert error!");
		}

		try {obj = (T) session.get(clazz, idd);
		} catch (Exception e) {System.err.print("when execute getById catch an error!");
		}finally{
			if(session != null && session.isOpen())session.close();
		}
		if(idd == -1)System.out.println("idֵΪ��!");
		
		return obj;
	}		
		
	/**
	 * ���ܣ�����
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-15
	 * @user lenovo	
	 */
	public <T> void insert(T t){	
		
		Session session = HibernateUtils.getFactory().openSession();
		
		Transaction transaction = session.beginTransaction();
		try {session.save(t);transaction.commit();
		} catch (Exception e) {
			if(transaction != null)transaction.rollback();
			System.out.println("Insert error!");
		}finally{
			if(session != null && session.isOpen())session.close();
		}
		
	}
		
	/**
	 * ���ܣ��޸�
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-15
	 * @user lenovo	
	 */
	public <T> void update(T t){
		
		Session session = HibernateUtils.getFactory().openSession();
		
		Transaction transaction = session.beginTransaction();
		try {
			session.update(t);transaction.commit();
		} catch (Exception e) {
			if(transaction != null)transaction.rollback();
			System.out.println("Insert error!");
		}finally{
			if(session != null && session.isOpen())session.close();
		}
		
	}
	
	/**
	 * ���ܣ�ɾ��
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-15
	 * @user lenovo	
	 */
	public <T> void delete(T t){
		
		Session session = HibernateUtils.getFactory().openSession();
		
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(t);transaction.commit();
		} catch (Exception e) {
			if(transaction != null)transaction.rollback();
			System.out.println("Delete error!");
		}finally{
			if(session != null && session.isOpen())session.close();
		}
		
	}	
	
	/**
	 * ���ܣ���ȡpageBean
	 * ���ߣ���ǿ
	 * ���ڣ�2015-9-18
	 * @user lenovo
	 */
	public <T> PageBean<T> pageBean(Integer page,Integer pageSize,Integer totalNums,List<T> list){
		
		PageBean<T> pagebean = new PageBean<T>();
		Integer totalPages = 0;

		try {
			totalPages = (totalNums/pageSize)+(totalNums%pageSize==0?0:1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		pagebean.setPage(page);pagebean.setPageSize(pageSize);
		pagebean.setList(list);pagebean.setTotalPages(totalPages);
		
		return pagebean;
	}
	
	/**
	
	//ģ����ѯʾ��
	@Test
	public void test1(){

		Map<String,String> map = new HashMap<String, String>();
		
		//��ѯname��������˹�ٷ�����������
		map.put("name", "��˹�ٷ���");
		
//		//��ѯname��������˹�ٷ���������address����"�й�԰"������
//		map.put("name", "��˹�ٷ���");   map.put("address", "�й�԰");
		
		PageBean<Yyy> pageBean = findByPageAndCondition(1,6,Yyy.class, map);
		
		List<Yyy> list = pageBean.getList();
		
		for (Yyy yyy : list) {
			System.out.println(yyy);
		}
		
	}
		
		
	*/
}
