package com.bwie.generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GenericTest3 {

	@Test
	public void test() {
		
		//��̬, ��������ָ���������
		Object obj = null ;
		String str = "�Ժ���" ;
		obj = str;
		
		Object[] obj2 = null ;
		String[] str2 = new String[]{"�Ժ���","�Ժ���"};
		obj2 = str2 ;
		
		//����A����B�����࣬��ôList<A> ������ List<B>���ӽӿ�
		List<Object> objList = null ;
		List<String> strList = new ArrayList<String>();
//		objList = strList ;������
	}

	/*
	 *  ? ͨ���
	 */
	@Test
	public void test2(){
		
		List<?> list = null ;
		
		List<Object> list1 = new ArrayList<Object>();
		List<String> list2 = new ArrayList<String>();
		
		list = list1 ;
		list = list2 ;
		
	}
	
	/*
	 *  �� extends A : ���Դ��A��������
	 *  ? super A : ���Դ��A���丸��
	 */
	@Test
	public void test3()
	{
		List<? extends Number> list = null ;
		List<Object> objList = null ;
		List<Integer> inList = null ;
		
		list = inList ;
//		list = objList ;
		
		List<? super Number> list2 = null ;
		
//		list2 = inList ;
		list2 = objList;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
