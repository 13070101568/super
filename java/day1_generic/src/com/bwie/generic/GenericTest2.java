package com.bwie.generic;

import java.util.List;

import org.junit.Test;

/*
 *  1. ��ʵ����������Ķ���ʱ��ָ�����͵����ͣ�ָ���Ժ󣬶�Ӧ����������ʹ�÷��͵�λ�á�����Ϊʵ�����з��͵����͡�
 *  
 *  2. ��������Զ����˷����࣬������ʵ����ʱû��ʹ�ã���ôĬ��������Object���͡�
 */
public class GenericTest2 {

	@Test
	public void test() {
		
		Order<Boolean> order = new Order<Boolean>();
		
		order.setT(false);
		
//		System.out.println(order.getT());
		
		order.add();
		
		List<Boolean> list = order.getList();
		
		System.out.println(list);
	}
	
}
