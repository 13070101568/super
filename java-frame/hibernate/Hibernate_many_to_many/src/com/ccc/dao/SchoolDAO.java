package com.ccc.dao;

import java.util.Set;

import org.hibernate.Session;

import com.ccc.dto.Courses;
import com.ccc.dto.Grade;
import com.ccc.dto.Student;
import com.ccc.utils.HibernateUtils;

public class SchoolDAO {

	public Grade getGradeById(int gid){
		Session session = HibernateUtils.getSession();
		Grade g = (Grade) session.get(Grade.class, gid);
		session.close();
		return g;
	}
	
	public Student getStudentById(int sid){
		Session session = HibernateUtils.getSession();
		Student stu = (Student) session.get(Student.class, sid);
		session.close();
		return stu;
	}
	
	public Courses getCoursesById(int cid){
		Session session = HibernateUtils.getSession();
		Courses cou = (Courses) session.get(Courses.class, cid);
		session.close();
		return cou;
	}
	
	
	/**
	 * ���ܣ�ͨ��ѧ��id�ҵ�ѧ����ѡ�Ŀγ�
	 * @param sid
	 * @return
	 */
	public String[] getStudentById_course(int sid){
		//�õ�session
		Session session = HibernateUtils.getSession();
		//ͨ��ѧ��id�ҵ�ѧ������
		Student student = this.getStudentById(sid);
		
		//ͨ��ѧ���ҵ���Ӧ�Ŀγ̼���
		Set<Courses> cou = student.getCou();
		//�γ�����ĳ���
		int len = 0;
		for (Courses courses : cou) {
			len++;
		}
		//����γ����飬��װ�ؿγ�
		String[] course = new String[len];
		int i = 0;
		for (Courses courses : cou) {
			course[i] = courses.getCname();
			i++;
		}
		//����ѧ��ѡ�Ŀγ�����
		return course;
	}
	
	
	/**
	 * ���ܣ�ͨ��ѧ��id�ҵ�ѧ����ѡ�Ŀγ�
	 * 
	 * @param sid
	 * @return
	 */
	public String[] getCoursesByStudentId(Integer id){
		//ͨ��ѧ��id�õ�ѧ������
		Student student = this.getStudentById(1);
		//ͨ��ѧ������õ��γ̼���
		Set<Courses> cou = student.getCou();
		//����γ�����ĳ���
		int len = 0;
		for (Courses courses : cou) {
			len++;
		}
		//����һ���γ����飬��װ�ؿγ�
		String[] course = new String[len];
		int i=0;
		for (Courses courses : cou) {
			course[i] = courses.getCname();
			i++;
		}
		//���ؿγ̵�����
		return course;
	}
	
	/**
	 * ���ܣ�ͨ��ѧ��id�ҵ�ѧ����ѡ�Ŀγ�
	 * 
	 * @param sid
	 * @return
	 */
	public Set getCourses(Integer sid){
		//ͨ��ѧ��id�õ�ѧ������
		Student student = this.getStudentById(1);
		//ͨ��ѧ������õ��γ̼���
		Set<Courses> cou = student.getCou();
		return cou;
	}
	
	
	
	
	
}
