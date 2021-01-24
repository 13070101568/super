package com.bbb.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.bbb.dto.Card;
import com.bbb.dto.Person;

public class TestOneToOne�� {

	/**
	 * ���
	 */
	@Test
	public void testSave(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//����
		Card card = new Card();
		card.setCname("6666");
		
		Person person = new Person();
		person.setName("������6");
		person.setAge(140);
		
		//ͨ��set������������������ϵ
		card.setPerson(person);
		
		session.save(card);
		
		transaction.commit();
		session.close();
	}
	/**
	 * Hibernate: insert into t_person (name, age) values (?, ?)
	 * Hibernate: insert into t_card (cname, cid) values (?, ?)
	 */
	
	
	
	/**
	 * �޸�
	 */
	@Test
	public void testUpdate(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		//����
		Card card = new Card();
		card.setCname("ggggg");
		card.setCid(3);
		
		Person person = new Person();
		person.setName("������gg");
		person.setAge(140);
		person.setId(3);
		
		//ͨ��set������������������ϵ
		card.setPerson(person);
		
		session.update(card);
		
		transaction.commit();
		session.close();
	}
	/*
	 * ����cascade="all"����ɾ��
	 */
	@Test
	public void testDelete(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		Person p = (Person) session.load(Person.class, 4);
		//System.out.println(p.getName()+"\t"+p.getAge()+"\t"+p.getCard().getCname());
		session.delete(p);
		
		transaction.commit();
		session.close();
	}
	
	/**
	 * get��ѯ
	 */
	@Test
	public void test_get(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		Person p = (Person) session.get(Person.class, 1);
		System.out.println(p.getName()+"\t"+p.getAge()+"\t"+p.getCard().getCname());
		
		transaction.commit();
		session.close();
	}
	/**
	 * get��ѯ
	 */
	@Test
	public void test_get1(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		Card c = (Card) session.get(Card.class, 3);
		System.out.println(c.getCname()+"\t"+c.getPerson().getName()+"\t"+c.getPerson().getAge());
		
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
		
		Person p = (Person) session.load(Person.class, 1);
		System.out.println(p.getName()+"\t"+p.getAge()+"\t"+p.getCard().getCname());
		
		transaction.commit();
		session.close();
	}
	/**
	 * ��ѯ����
	 */
	@Test
	public void test_all(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "from Person";
		List<Person> list = session.createQuery(hql).list();
		for (Person p : list) {
			System.out.println(p.getName()+"\t"+p.getAge()+"\t"+p.getCard().getCname());
		}
		
		
		transaction.commit();
		session.close();
	}
	/**
	 * ��ѯһ����¼
	 */
	@Test
	public void test_unique(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "from Person where id=1";
		Person p = (Person) session.createQuery(hql).uniqueResult();
		System.out.println(p.getName()+"\t"+p.getAge()+"\t"+p.getCard().getCname());
		
		
		transaction.commit();
		session.close();
	}
	
	 

	
	
	
}
