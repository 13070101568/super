package com.bee.inteceptor;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class InteceptorOne extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("lanjieqi ++++++++++++++++++++++++=");
		Object user = ActionContext.getContext().getSession().get("user");
		
		
		if(user==null){
			System.out.println(user);
			ActionSupport action = (ActionSupport)invocation.getAction();
			action.addActionError(action.getText("�գ�����"));
	    	return "input";
		}
		return invocation.invoke();
	}
	
//	public String intercept2(ActionInvocation invocation) throws Exception {
//		
//		//contextΪ�����Ķ���
//		ActionContext context = invocation.getInvocationContext();
//		//ȡsession  
//		Map<String, Object> session = context.getSession();
//		//sessionȡ�û�
//		User u = (User)session.get("user");
//		if(u==null){
//			//��Ϊ�����û�м̳�ActionSupport ������Ҫʹ��LOGIN�������ص�½ҳ�� �����ýӿ�.LOGIN
//			return Action.LOGIN;
//		}else{
//			return invocation.invoke();
//		}
//		
//	}
	
}
