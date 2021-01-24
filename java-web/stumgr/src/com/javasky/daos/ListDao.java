package com.javasky.daos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javasky.datas.DataSource;
import com.javasky.datas.IDataSource;
//��DAO���ڲ�ѯ ���÷������ ��һ��DAO���Խ��ѧ�����ɼ������������͵Ĳ�ѯ
public class ListDao<T> {

	public List<T> getList(Class c,String sql){
		List<T> list = new ArrayList<T>();
		IDataSource ds = DataSource.getInstance();
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			//ʹ�÷�����ƻ�� ���Ա
			Field[] fields = c.getDeclaredFields();
			while(rs.next()){
				//����Class c ��������
				T o = (T)c.newInstance();//�������Ͳ����� ����Object ֱ���÷���
				/*��ͳ��д����
				 * Student s = new Student();
				 * s.setSno(rs.getInt("sno"));
				 * s.setSname(rs.getString("sname"));
				 * */
				for(Field field : fields){
					String fieldName = field.getName();//��ȡ���Ա�����ԣ���
					/*ʹ�÷�����ƻ��set������*/
					String methodName ="set"+fieldName.substring(0, 1).toUpperCase()
							+fieldName.substring(1);//��ȡset������
					//��ȡset���������Ա��ʲô���ͣ�����ֵ����ʲô����
					Method method =c.getMethod(methodName, field.getType());
					//��÷�������
					String type = field.getType().getSimpleName();
					if("int".equals(type)||"Integer".equals(type)){
						//���������o��ִ�����set�������ѻ�ȡ���Ľ����set��ȥ
						method.invoke(o, rs.getInt(fieldName));
					}else if("String".equals(type)){
						method.invoke(o, rs.getString(fieldName));
					}else if("double".equals(type)||"Double".equals(type)){
						method.invoke(o, rs.getString(fieldName));
					}else if("char".equals(type)||"Character".equals(type)){
						method.invoke(o, rs.getString(fieldName));
					}else if("boolean".equals(type)||"Boolean".equals(type)){
						method.invoke(o, rs.getString(fieldName));
					}
				}
				/*�Ѷ���װ�뵽������*/
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
