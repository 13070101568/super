package com.jdbc.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;

public class PoolDataSource {
	private String url;
	private String user;
	private String pwd;
	private int maxIdle;
	private int minIdle;
	/*����pool�е����� ���� ɾ���ͼ��룬Ϊ��Ч�� ʹ������*/
	private static LinkedList<Connection> pool = new  LinkedList<Connection>();
	private PoolDataSource(){
		Properties prop = new Properties();
		//��ȡ�����ļ�  ���������·��ȥѰ��
		try {
	prop.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			pwd = prop.getProperty("pwd");
		maxIdle = Integer.parseInt(prop.getProperty("maxIdle","2"));//�����д�� Ĭ��Ϊ2��
			minIdle = Integer.parseInt(prop.getProperty("minIdle","1"));
			String driver = prop.getProperty("driver");
			//��������
			Class.forName(driver);
			//��ʼ�����ӳأ�����minIdle��Connection������ȥ
			for(int i =0;i<minIdle;i++){
				//���� ����������ʱ�� ���Ӿ��Ѿ�װ���� �����Ƚ��� ���Ժ��õ�ʱ�� �ܿ� ����Ҫ����ʱ��
				pool.add(getConnection());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static PoolDataSource self = new PoolDataSource();
	
	public static PoolDataSource getInstance(){
		return self;
	}
	/**
	 * ������ݿ�����ʱ �����ж� ���Ƿ��ж�����
	 * ����� ����ȡ����ɾ����ȡ�ߵ��Ǹ�
	 * �������û�У������µ�
	 * 
	 * �������������ͬ���� ��Ϊ�п��ܱ���������ͬʱȡ��
	 * ����Ҫ�������Ŷӣ�������synchronized
	 * */
	public synchronized Connection getConnection(){
		Connection conn = null;
		if(pool.size()>minIdle){
			conn = pool.removeLast();
		}else{
			try {
				conn = DriverManager.getConnection(url, user, pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
		
	}
	/*
	 * �Żس���ʱ �ж� ���Ƿ�ﵽ������������û�дﵽ
	 * װ����� ���� �ͷ�
	 */
	public void close(Connection conn){
		close(conn,null,null);//�������ķ���
	}
	public void close(Connection conn, Statement stmt){
		close(conn,stmt,null);
	}

	public void close(Connection conn, Statement stmt, ResultSet rs){
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(null!=stmt){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//��Ϊ�����Ǹ�������� ��������Ҳһ��
		synchronized(this){
			if(null!=conn){
				if(pool.size()==maxIdle){//��������� �ͷ�
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else{
					pool.add(conn);//װ�뵽����
				}
			}
		}
	}
}
