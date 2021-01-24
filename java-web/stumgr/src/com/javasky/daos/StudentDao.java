package com.javasky.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.javasky.datas.DataSource;
import com.javasky.datas.IDataSource;
import com.stumgr.pojo.Student;

public class StudentDao {

	public boolean save(Student student){
		boolean flag = false;
		//1���������Դʵ��
		IDataSource ds = DataSource.getInstance();
		//2,������ݿ�����
		Connection conn = ds.getConnection();
		//3,����SQL��� ���sno�������д��������� ����Ҫ��������ȡ
		String sql ="insert into students(sname,gender,mobile,hobby,classes,sno) values(?,?,?,?,?,seq_stu.nextval)";
		//4,����Ԥ����SQL���ִ����
		PreparedStatement pstmt =null;
		try {
			//5,����������ύ��ʽΪ�ֶ�
			conn.setAutoCommit(false);
			//6������pstmtʵ��
			pstmt=conn.prepareStatement(sql);
			//7,��ֵ
			pstmt.setString(1, student.getSname());
			pstmt.setString(2, student.getGender());
			pstmt.setString(3, student.getMobile());
			pstmt.setString(4, student.getHobby());
			pstmt.setString(5, student.getClasses());
			//8,ִ��SQL���
			pstmt.executeUpdate();
			//9,�ύ����
			conn.commit();
			//10,���ύ�ɹ� ����flagΪtrue
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			//11,���ύʧ�� �ع�����
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			//12,�ͷ���Դ
			ds.close(conn, pstmt);
		}
		return flag;
	}
	
	public boolean update(Student student){
		boolean flag = false;
		//1���������Դʵ��
		IDataSource ds = DataSource.getInstance();
		//2,������ݿ�����
		Connection conn = ds.getConnection();
		//3,����SQL��� ���sno�������д��������� ����Ҫ��������ȡ
		String sql ="UPDATE STUDENTS SET sname=?,gender=?,mobile=?,hobby=?,classes=? where sno=?";
		//4,����Ԥ����SQL���ִ����
		PreparedStatement pstmt =null;
		try {
			//5,����������ύ��ʽΪ�ֶ�
			conn.setAutoCommit(false);
			//6������pstmtʵ��
			pstmt=conn.prepareStatement(sql);
			//7,��ֵ
			pstmt.setString(1, student.getSname());
			pstmt.setString(2, student.getGender());
			pstmt.setString(3, student.getMobile());
			pstmt.setString(4, student.getHobby());
			pstmt.setString(5, student.getClasses());
			pstmt.setInt(6, student.getSno());
			//8,ִ��SQL���
			pstmt.executeUpdate();
			//9,�ύ����
			conn.commit();
			//10,���ύ�ɹ� ����flagΪtrue
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			//11,���ύʧ�� �ع�����
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			//12,�ͷ���Դ
			ds.close(conn, pstmt);
		}
		return flag;
	}
	
	public boolean delete(int sno){
		boolean flag = false;
		String sql ="delete from students where sno=?";
		IDataSource ds = DataSource.getInstance();
		PreparedStatement stmt = null;
		Connection conn = ds.getConnection();
		try {
			conn.setAutoCommit(false);
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, sno);
			stmt.executeUpdate();
			conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			ds.close(conn, stmt);
		}
		return flag;
	}
	
	//����ѧ�Ų�ѯ��ѧ��
	public Student getBySno(int sno){
		ListDao<Student> stuDao = new ListDao<Student>();
		Student stu = stuDao.getList(Student.class, "SELECT * FROM STUDENTS WHERE SNO="+sno).get(0);
		return stu;
	}
	
}
