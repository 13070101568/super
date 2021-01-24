package com.jdbc.content;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PlayJdbc02 {
	public static void main(String[] args) {
		/*����oracle����������
		 * oracle.jdbc.driver.OracleDriver
		 * 
		 *������������سɹ�֮������������Զ�ͨ������
		 *java.sql.DriverManager.registerDriver()��̬����
		 *��������ע�ᣬע�ᵽJava�����֮�С�
		 * 
		 * URL: jdbc:oracle:thin:@192.168.1.xx:1521:sid 
		 * scott
		 * tiger
		 * 
		 * java.sql.SConnection conn = DriverManage. getConnection(url,user,pwd);
		 */
		String url = "jdbc:oracle:thin:@192.168.1.177:1521:orcl";
		String user ="scott";
		String pwd ="tiger";
		
		Scanner scan = new Scanner(System.in);
		System.out.print("�����빤�����ࣺ");
		String job = scan.next();
		
		try {
			//����oracle jdbc��������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//������ݿ����ӡ�
			Connection conn = DriverManager.getConnection(url, user, pwd);
			/*����SQL���
			 ���ֲ�ѯ ��SQLע�����bug�����Ի���Ԥ�����SQL���ִ�����������
			  String sql = "select * from emp where job='"+job+"'";
			    ������̬sql���ִ����
			  Statement stmt = conn.createStatement();
			*/
			//Ԥ����sql��� д��ʱ�� ����Ĳ���ֵд�� ��
			String sql = "select * from emp where job=? and sal=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			//��ִ�в�ѯ֮ǰΪ ����ֵ ����������1��ʼ �����ܵı�֤������ȷ��
			stmt.setString(1, job);
			stmt.setFloat(2, 800);
			//ִ�в�ѯ��������ѯ������ظ�ResultSet��
			//ע�⣬Ԥ����stmt��ִ��ʱ �����в��� 
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Object o1 = rs.getObject(1);
				Object o2 = rs.getObject(2);
				Object o3 = rs.getObject(3);
				Object o4 = rs.getObject(4);
				System.out.println(o1+"  "+o2+"  "+o3+"  "+o4 +" ");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			/*1 urlûд��
			 *2  �û���|���벻��ȷ
			 *3  ���ݿ�û��
			 * */
			e.printStackTrace();
		}
	}
}
