package com.javasky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.javasky.pojo.Student;
import com.javasky.tools.DataSource;

public class StudentDao {
	//�����ķ���
		public boolean save(Student student){
			boolean flag = false;
			//1,������ݿ�����
			DataSource ds = DataSource.getInstance();
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO STUDENT(id,name,score) VALUES(?,?,?)";
			//�������
			conn = ds.getConnection();
			try {
				//2,���� ������ύ��ʽΪ �ֶ��ύ
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				//3,�� �� ��ֵ
				pstmt.setInt(1, student.getId());
				pstmt.setString(2, student.getName());
				pstmt.setInt(3, student.getScore());
				//4,ִ��sql���  ���ִ��ʧ�� ֱ����catch�����
				pstmt.executeUpdate();
				//5,�ύ����
				conn.commit();
				//6,sql���ִ�гɹ� flag = true
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
				//7,���ִ��ʧ�� �ع�����
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}finally{
				//8,......�ͷ���Դ
				ds.close(conn, pstmt);
			}
			return flag;
		}
		//�޸ĵķ���
		public boolean update(Student student){
			boolean flag = false;
			//1,������ݿ�����
			DataSource ds = DataSource.getInstance();
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE student SET name=?, score=? where id=?";
			//�������
			conn = ds.getConnection();
			try {
				//2,���� ������ύ��ʽΪ �ֶ��ύ
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				//3,�� �� ��ֵ
				pstmt.setInt(3, student.getId());
				pstmt.setString(1, student.getName());
				pstmt.setInt(2, student.getScore());
				//4,ִ��sql���  ���ִ��ʧ�� ֱ����catch�����
				pstmt.executeUpdate();
				//5,�ύ����
				conn.commit();
				//6,sql���ִ�гɹ� flag = true
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
				//7,���ִ��ʧ�� �ع�����
				try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			//8,......�ͷ���Դ
			ds.close(conn, pstmt);
		}
		return flag;
	}
	//ɾ�� ָ����ID
	public boolean delete(int id){
		boolean flag = false;
		//1,������ݿ�����
		DataSource ds = DataSource.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM student where id=?";
		//�������
		conn = ds.getConnection();
		try {
			//2,���� ������ύ��ʽΪ �ֶ��ύ
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			//3,�� �� ��ֵ
			pstmt.setInt(1, id);
			//4,ִ��sql���  ���ִ��ʧ�� ֱ����catch�����
			pstmt.executeUpdate();
			//5,�ύ����
			conn.commit();
			//6,sql���ִ�гɹ� flag = true
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			//7,���ִ��ʧ�� �ع�����
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			//8,......�ͷ���Դ
			ds.close(conn, pstmt);
		}
		return flag;
	}
	//��ѯ
	public void select(){
		//1,������ݿ�����
		DataSource ds = DataSource.getInstance();
		Connection conn = null;
		Statement stmt = null;
		String sql = "SELECT * FROM student";
		ResultSet rs = null;
		//�������
		conn = ds.getConnection();
		try {
			//2,���� ������ύ��ʽΪ �ֶ��ύ
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			//3,ִ��sql��� 
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Object o1 = rs.getObject("id");
				Object o2 = rs.getObject("name");
				Object o3 = rs.getObject("score");
				System.out.println(o1+"  "+o2+"  "+o3);
			}
			//5,�ύ����
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//6,���ִ��ʧ�� �ع�����
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			//7,......�ͷ���Դ
			ds.close(conn, stmt, rs);
		}
	}
	//��ID��ѯ
	public boolean selectId(int id){
		boolean flag = false;
		//1,������ݿ�����
		DataSource ds = DataSource.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM student where id=?";
		//�������
		conn = ds.getConnection();
		try {
			//2,���� ������ύ��ʽΪ �ֶ��ύ
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			//3,�� �� ��ֵ
			pstmt.setInt(1, id);
			//4,ִ��sql���  ���ִ��ʧ�� ֱ����catch�����
			pstmt.executeUpdate();
			//5,�ύ����
			conn.commit();
			//6,sql���ִ�гɹ� flag = true
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			//7,���ִ��ʧ�� �ع�����
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			//8,......�ͷ���Դ
			ds.close(conn, pstmt);
		}
		return flag;
	}
	//����
	public void sort(){
		//1,������ݿ�����
		DataSource ds = DataSource.getInstance();
		Connection conn = null;
		Statement stmt = null;
		String sql = "SELECT * FROM student order by score";
		ResultSet rs = null;
		//�������
		conn = ds.getConnection();
		try {
			//2,���� ������ύ��ʽΪ �ֶ��ύ
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
				//3,ִ��sql��� 
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					Object o1 = rs.getObject("id");
					Object o2 = rs.getObject("name");
					Object o3 = rs.getObject("score");
					System.out.println(o1+"  "+o2+"  "+o3);
				}
				//5,�ύ����
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				//6,���ִ��ʧ�� �ع�����
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}finally{
				//7,......�ͷ���Դ
				ds.close(conn, stmt, rs);
			}
	  }
}
