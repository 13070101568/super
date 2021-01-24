package com.ccc.dao;

import org.hibernate.Session;

import com.ccc.dto.Grade;
import com.ccc.dto.Student;
import com.ccc.utils.HibernateUtils;

public class SchoolDAO {

	//���ݰ༶id���Ұ༶����
	public Grade getGradeById(int gid){
		Session session = HibernateUtils.getSession();
		Grade g = (Grade) session.get(Grade.class, gid);
		session.close();
		return g;
	}
	
	//����ѧ��id��ѯѧ������
	public Student getStudentById(int sid){
		Session session = HibernateUtils.getSession();
		Student stu = (Student) session.get(Student.class, sid);
		session.close();
		return stu;
	}
}
