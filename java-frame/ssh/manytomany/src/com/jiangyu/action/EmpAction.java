package com.jiangyu.action;


import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xiaobaixia.jiangyu.ssh3.utils.JiangYuBaseAction;

import com.jiangyu.pojo.CEmp;
import com.jiangyu.pojo.CRole;
import com.jiangyu.service.IEmpService;
import com.jiangyu.service.IRoleService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class EmpAction extends JiangYuBaseAction implements ModelDriven<CEmp> {

	@Autowired
	private IEmpService empService;
	@Autowired
	private IRoleService roleService;
	
	private CEmp emp = new CEmp();
	
	private List<CEmp> list;
	
	private Integer[] roleid;
	
	public String findAll(){
		// TODO Auto-generated method stub
		list = empService.findAll();
		
		return "found";
	}
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-11-16����8:28:25
	 * ���ܣ�ת�������ɫ
	 * @return
	 */
	public String toRole(){
		//��ѯ���е�ROLE����ɫ��
		List<CRole> list = roleService.findAll();
		//��ѯ��ǰ�û���ROLE
		List<CRole> list2 = roleService.findEmpRole(emp.getId());
		ServletActionContext.getRequest()
			.setAttribute("roleList", list);
		ServletActionContext.getRequest()
			.setAttribute("userRoleList", list2);
		return "updateRole";
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-11-16����8:32:44
	 * ���ܣ������û���ɫ
	 * roleid�������õĽ�ɫ��id����
	 * @return
	 */
	public String saveUserRole(){
//		for(Integer i : roleid){
//			System.out.println(i);
//		}
		empService.modifyEmpRole(emp.getId(),roleid);
		
		return "toList";
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-11-20����7:43:11
	 * ���ܣ�ɾ���û�
	 * @return
	 */
	public String remove(){
		
		empService.remove(emp.getId());
		
		return "";
	}
	
	public List<CEmp> getList() {
		return list;
	}

	public void setList(List<CEmp> list) {
		this.list = list;
	}

	public CEmp getModel() {
		// TODO Auto-generated method stub
		return emp;
	}

	public Integer[] getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer[] roleid) {
		this.roleid = roleid;
	}

	
}
