package com.bw.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.bw.entity.User;
import com.bw.model.UserModel;
import com.bw.service.UserService;
import com.bw.utils.ToolPage;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	private UserModel userModel;
	
	private List list;
	
	private UserService service = new UserService();
	
	
	/**
	 * ���ܣ���ӵĹ��ʻ�
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public String change() throws Exception {
	     
		return "toAdd";
	  }  
	
	
	
	/**
	 * ���ܣ������ҳ��
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public String toAdd() throws Exception {
		System.out.println("-------UserAction----toAdd------");
		
		
		
		return "toAdd";
	}
	
	/**
	 * ���ܣ����
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public String add() throws Exception {
		System.out.println("-------UserAction----add------");
		
		//�ռ�����
		//����
		String[] h = userModel.getHobby();
		String hobby = "";
		for (String string : h) {
			hobby += string+",";
		}
		hobby = hobby.substring(0,hobby.length()-1);
		
		//װ������
		User user = new User();
		BeanUtils.copyProperties(user, userModel);
		user.setHobby(hobby);
		//System.out.println("hobby="+user.getHobby());
		
		
		//����ҵ��
		service.add(user);
		//��ת
		return SUCCESS;
	}
	
	/**
	 * ���ܣ��б�
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public String list() throws Exception {
		System.out.println("-------UserAction----list------");
		int pageSize = 5;
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String page = request.getParameter("currentPage")!=null?request.getParameter("currentPage"):"0";
		Integer currentPage = Integer.parseInt(page);
		list = service.getListPage(currentPage, pageSize);
		
		String url = "user!list.action";
		
		int listCount = service.getListCount();
		ToolPage.page(request, currentPage, pageSize, url, listCount, list);
		
		return "list";
	}
	
	/**
	 * ���ܣ�ɾ��
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public String deletea() throws Exception {
		System.out.println("-------UserAction----deletea------");
		//��ȡid
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		System.out.println("id_del="+id);
		
		//ҵ��
		service.delete(id);
		
		return SUCCESS;
	}
	
	/**
	 * ���ܣ�ɾ��
	 * ���ߣ�sdw
	 * ʱ�䣺2015-3-23
	 */
	public String toUpdate() throws Exception {
		System.out.println("-------UserAction----toUpdate------");
		//��ȡid
		Integer id = userModel.getId();
		System.out.println("id_update="+id);
		
		//ҵ��
		User user = service.getUserById(id);
		
		//װ������userModel
		BeanUtils.copyProperties(userModel, user);
		
		
		return "toUpdate";
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	
	
	
	
	
	
}
