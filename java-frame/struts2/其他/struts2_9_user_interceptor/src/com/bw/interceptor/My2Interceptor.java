package com.bw.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class My2Interceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("------My2Interceptor-------����action֮ǰ------------");	
		
		String invoke = invocation.invoke();
		
		System.out.println("------My2Interceptor-------����action֮��------------");
		
		return invoke;
	}

}
