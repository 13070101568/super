package com.bw.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class Timer2Interceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		//��ʼʱ��
		long start = System.currentTimeMillis();
		//System.out.println("start="+start);
		String invoke = invocation.invoke();
		//����ʱ��
		long end = System.currentTimeMillis();
		//System.out.println("end="+end);
		System.out.println("������ִ��Ч��Ϊ"+(end-start)+"ms");
		
		
		return invoke;
	}

}
