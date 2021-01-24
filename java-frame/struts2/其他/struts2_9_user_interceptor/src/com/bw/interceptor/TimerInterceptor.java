package com.bw.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class TimerInterceptor implements Interceptor {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		
		//��ʼʱ��
		long start = System.currentTimeMillis();
		String invoke = invocation.invoke();
		//����ʱ��
		long end = System.currentTimeMillis();
		System.out.println("������ִ��Ч��Ϊ"+(end-start)+"ms");
		
		
		return invoke;
	}

}
