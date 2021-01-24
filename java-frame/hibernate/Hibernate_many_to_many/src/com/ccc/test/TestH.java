package com.ccc.test;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import com.ccc.dao.SchoolDAO;
import com.ccc.dto.Courses;
import com.ccc.dto.Grade;
import com.ccc.dto.Student;
import com.ccc.utils.HibernateUtils;

public class TestH {

	// �γ�
	@Test
	public void test_courses_query1() {
		SchoolDAO dao = new SchoolDAO();
		Courses cou = dao.getCoursesById(1);
		System.out.println(cou.getCname());
		Set<Student> stu = cou.getStu();
		for (Student student : stu) {
			System.out.println(student.getSname() + "\t"
					+ student.getGrade().getGname());
		}
	}
	
	// �༶
	@Test
	public void test_courses_query1a() {
		
		SchoolDAO dao = new SchoolDAO();
		Grade grade = dao.getGradeById(2);
		System.out.println(grade.getGname());
		System.out.println("*****�༶********");
		Set<Student> stu = grade.getStu();
		for (Student student : stu) {
			System.out.println(student.getSname() +"\t");
			Set<Courses> cou = student.getCou();
			for (Courses courses : cou) {
				System.out.println(courses.getCname());
			}
			System.out.println("------ѧ��--------------");
		}		
	}
	
	
	// �γ�
	@Test
	public void test_courses_query1ab() {
	
		SchoolDAO dao = new SchoolDAO();
		Courses courses = dao.getCoursesById(1);
		System.out.println(courses.getCname());
		Set<Student> stu = courses.getStu();
		for (Student student : stu) {
			System.out.println(student.getSname() +"\t"+student.getGrade().getGname());
		}
	}
	
	/**
	 * ���ܣ�ͨ��ѧ��id�ҵ�ѧ����ѡ�Ŀγ�
	 * 
	 * @param sid
	 * @return
	 */
	@Test
	public void test_courses_query1abc() {
	
		SchoolDAO dao = new SchoolDAO();
		Student student = dao.getStudentById(1);
		System.out.println(student.getSname()+"\t"+student.getGrade().getGname());
		
		Set<Courses> cou = student.getCou();
		for (Courses courses : cou) {
			System.out.println(courses.getCname());
		}
		
	}
	
	
	/**
	 * ���ܣ�ͨ��ѧ��id�ҵ�ѧ����ѡ�Ŀγ�
	 * 
	 * @param sid
	 * @return
	 */
	@Test
	public void test_courses_query1abcd() {
	
		SchoolDAO dao = new SchoolDAO();
		String[] course = dao.getCoursesByStudentId(1);
		Student student = dao.getStudentById(1);
		System.out.println("1��ѧ����"+student.getSname()+",ѡ������Ϊ��");
		for(int i=0;i<course.length;i++){
			System.out.println(course[i]);
		}
		
	}
	
	
	
	
	
	
	

	/**
	 * ���ܣ�ͨ��ѧ��id�ҵ�ѧ����ѡ�Ŀγ�
	 * 
	 * @param sid
	 * @return
	 */
	@Test
	public void getStudentById1() {
		SchoolDAO dao = new SchoolDAO();
		String[] cou = dao.getStudentById_course(1);
		Student student = dao.getStudentById(1);
		System.out.println(student.getSname());
		for (int i = 0; i < cou.length; i++) {
			System.out.println(cou[i]);
		}

	}

	/**
	 * ���ܣ�ѧ��û��ѡ�Ŀγ�
	 */
	@Test
	public void test_stu_query11() {
		SchoolDAO dao = new SchoolDAO();
		Session session = HibernateUtils.getSession();
		String sql = "select * from courses where cid not in(select c.cid from student a join stu_cou b on a.sid=b.sid join courses c on b.cid=c.cid where a.sid=1)";
		List<Courses> list = session.createSQLQuery(sql).addEntity(Courses.class).list();
		for (Courses cou : list) {
			System.out.println(cou.getCname());
		}

		session.close();

	}
	
	
	/**
	 * ���ܣ�ѧ��û��ѡ�Ŀγ�
	 */
	@Test
	public void test_stu_query112() {
		SchoolDAO dao = new SchoolDAO();
		Session session = HibernateUtils.getSession();
		String sql = "select * from courses where  cid not in(select cid from stu_cou where sid=1)";
		List<Courses> list = session.createSQLQuery(sql).addEntity(Courses.class).list();
		for (Courses cou : list) {
			System.out.println(cou.getCname());
		}

		session.close();

	}

}
