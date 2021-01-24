package com.itcast;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;
public class IStudentDAOImpl implements IStudentDAO{
    private static SqlMapClient sqlMapClient = null;
    static
    {
        try
        {
            Reader reader = com.ibatis.common.resources.Resources.getResourceAsReader("com/itcast/SqlMapConfig.xml");
            sqlMapClient = com.ibatis.sqlmap.client.SqlMapClientBuilder.buildSqlMapClient(reader);
            reader.close();  
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void addStudent(Student student) {
		try
        {
            sqlMapClient.insert("insertStudent",student);
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	
	}
	public void addStudentBySequence(Student student) {
		try
        {
			//1.�����ݿ������л�ȡ����ֵ
			//2.��student���в����¼
            sqlMapClient.insert("insertStudentBySequence",student);
            System.out.println("sid="+student.getSid());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
	}
	public void deleteStudentById(int id) {
		try
        {
            System.out.println(sqlMapClient.delete("deleteStudentById",id));
            //�����ӡ������ֵ����0��˵��ɾ���ɹ�
            System.out.println("�����ӡ������ֵ����0��˵��ɾ���ɹ�");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
	}
	public List<Student> queryAllStudent() {
		List<Student> studentList = null; 
        try
        {
        	studentList = sqlMapClient.queryForList("selectAllStudent");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return studentList;
	}
	public Student queryStudentById(int id) {
		Student student = null;
        try
        {
        	student = (Student)sqlMapClient.queryForObject("selectStudentById",id);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return student;
	}
	public List<Student> queryStudentByName(String name) {
		List<Student> studentList = null;
        try
        {
        	studentList = sqlMapClient.queryForList("selectStudentByName",name);
            
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return studentList;

	}
	public void updateStudent(Student student) {
		try
        {
            System.out.println(sqlMapClient.update("updateStudent",student));
            //�����ӡ������ֵ����0��˵�����³ɹ�
            System.out.println("�����ӡ������ֵ����0��˵�����³ɹ�");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
	}
    public static void main(String[] args)
    {
        IStudentDAOImpl dao = new IStudentDAOImpl();
        //1.�鿴����ѧ��
//        for(Student stus:dao.queryAllStudent())
//        {
//            System.out.println(stus);
//        }
    
        //2.��id��ѯѧ��
       // System.out.println(dao.queryStudentById(1));
     
        //3.����һ��ѧ����¼
//        Student student = new Student();
//        student.setSid(5);
//        student.setSname("ddd");
//        student.setMajor("lsdkj");
//        student.setBirth(Date.valueOf("1809-08-08"));
//        student.setScore((float) 123.20);
//        
//        dao.addStudent(student);
//        System.out.println(dao.queryStudentById(5));
        
        
        //4.���԰�idɾ��ѧ����¼
        // dao.deleteStudentById(5);
    
        //5.�����޸�ѧ����¼
//        Student student = new Student();
//        student.setSid(4);
//        student.setSname("ddd");
//        student.setMajor("lsdkj");
//        student.setBirth(Date.valueOf("1809-08-08"));
//        student.setScore((float) 123.20);
//        
//        dao.updateStudent(student);
//        System.out.println(dao.queryStudentById(4));
         
        //6.����ͨ�����ֽ���ģ����ѯ
//        for(Student student:dao.queryStudentByName("d"))
//        {
//            System.out.println(student);
//            
//        }

        //7.����ͨ���������ѧ����¼
        Student student = new Student();
        student.setSid(100);
        student.setSname("ttttt");
        student.setMajor("444444");
        student.setBirth(Date.valueOf("1843-08-08"));
        student.setScore((float) 155.20);
        dao.addStudentBySequence(student);
    }
}