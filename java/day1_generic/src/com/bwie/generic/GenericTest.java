package com.bwie.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
/*
 *   1. Ϊʲôʹ�÷��� 
 *     *�����Ԫ�ش���İ�ȫ������
 *     *�����ȡԪ��ʱ����Ҫ����ת��������
 *     *������ʱ������ǰ������ʱ�������˿���Ч��
 *     
 *   2. �Գ��õķ���ʹ�÷�ʽ��ѧϰ
 */
public class GenericTest {

	@Test
	public void test() {
		
		List list = new ArrayList();
		
		list.add(65);
		list.add(60);
		list.add(55);
		
		//û��ʹ�÷��ͣ��κ�Object���������͵Ķ��󶼿�����ӽ���
		list.add("tom");
		
		for(int i = 0 ; i < list.size(); i++)
		{
			//ǿתΪInteger����ʱ�����ܻᷢ��ClasCastException���쳣
			int score = (Integer) list.get(i);
			System.out.println(score);
		}
		
	}

	@Test
	public void test2()
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(65);
		list.add(60);
		list.add(55);
		
//		list.add("tom");
		
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		
	}
	
	@Test
	public void test3()
	{
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("����÷", 44);
		map.put("����", 4);
		map.put("�Ժ���", 28);
		
		
		Set<Map.Entry<String, Integer>> set = map.entrySet();
		
		for(Map.Entry<String, Integer> o : set)
		{
			System.out.println(o.getKey() + " : "+ o.getValue());
		}
		
	}
	
}
