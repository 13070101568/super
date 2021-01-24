package com.bbb.test;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.bbb.dto.Card;
import com.bbb.dto.Person;

public class TestOneToOne {

	/**
	 * Hibernate: insert into t_person (name, age) values (?, ?)
	 * Hibernate: insert into t_card (cname, cid) values (?, ?)
	 */
	/**
	 * ���
	 */
	@Test
	public void testSave(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//�������֤
		Card card = new Card();
		card.setCname("333");
		
		Person person = new Person();
		person.setName("������3");
		person.setAge(140);
		
		//ͨ��set������������������ϵ
		card.setPerson(person);
		
		session.save(card);
		
		
		
		transaction.commit();
		session.close();
	}
	/**
	 * ����1,�������
	 */
	@Test
	public void testSave1(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//�������֤
		Card card = new Card();
		card.setCname("222222");
		
		Person person = new Person();
		person.setName("������222");
		person.setAge(140);
		
		//ͨ��set������������������ϵ
		card.setPerson(person);
		
		session.save(person);
		
		
		
		transaction.commit();
		session.close();
	}
	
	
	/**
	 * �޸ļ���
	 * . ����Hibernate�м����ؼ����Լ�����������Щֵ?(5��)
		Cascade:
		all:���������е�����¶�ִ�м�������
		none:����������¶���ִ�м�������(Ĭ��ֵ)
		save-update:�ڱ���͸���ʱִ�м�������
		delete:��ɾ��ʱִ�м�������
	 */
	@Test
	public void testUpdate(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//�޸�
		Card card = new Card();
		card.setCname("666");
		card.setCid(3);
		
		
		Person person = new Person();
		person.setName("������66");
		person.setAge(14);
		person.setId(3);
		
		//ͨ��set������������������ϵ
		card.setPerson(person);
		
		session.update(card);
		
		transaction.commit();
		session.close();
	}
	
	/**
	 * ɾ��person��cascade="all"  person��cascade="delete"
	 */
	@Test
	public void testDelete(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//ɾ��
		Person person = (Person) session.load(Person.class, 3);
		session.delete(person);
		
		transaction.commit();
		session.close();
	}
	/**
	 * ɾ��card����cascade="all"
	 */
	@Test
	public void testDelete1(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//ɾ��
		Card card = (Card) session.load(Card.class, 1);
		session.delete(card);
		
		transaction.commit();
		session.close();
	}
	
	
	
	/**
	 * get��ѯperson
	 * �ص�
	 */
	@Test
	public void test_get(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		
		//get
		Person person = (Person) session.get(Person.class, 2);
		System.out.println(person.getName()+"\t"+person.getCard().getCname()+"\t"+person.getCard().getCid());
		
		session.close();
	}
	
	/**
	 * get��ѯcard
	 */
	@Test
	public void test_get1(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//get
		Card card = (Card) session.get(Card.class,2);
		System.out.println(card.getPerson().getName()+"\t"+card.getCname());
		transaction.commit();
		session.close();
	}
	
	/**
	 * load����
	 */
	@Test
	public void test_load(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//load
		Person person = (Person) session.load(Person.class, 2);
		System.out.println(person.getName()+"\t"+person.getCard().getCname());
		
		transaction.commit();
		session.close();
	}
	
	/**
	 * load��ѯcard
	 */
	@Test
	public void test_get2(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//get
		Card card = (Card) session.load(Card.class,2);
		System.out.println(card.getPerson().getName()+"\t"+card.getCname());
		transaction.commit();
		session.close();
	}
	
	/**
	 * ��ѯ����all
	 */
	@Test
	public void test_all(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Person";
		List<Person> list = session.createQuery(hql).list();
		for (Person person : list) {
			System.out.println(person.getName()+"\t"+person.getCard().getCname());
		}
		
		transaction.commit();
		session.close();
	}
	
	/**
	 * ��ѯһ����¼uniqueResult
	 */
	@Test
	public void test_uniqueResult(){
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "from Person where id=4";
		Person person = (Person) session.createQuery(hql).uniqueResult();
		System.out.println(person.getName()+"\t"+person.getCard().getCname());
		
		
		transaction.commit();
		session.close();
	}
	
	
	
	
	
}
