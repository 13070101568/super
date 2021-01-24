package com.baidu.dept.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.baidu.base.service.SelectDataService;
import com.baidu.dept.bean.Dept;
import com.baidu.dept.service.DeptService;
import com.baidu.user.bean.User;
import com.baidu.util.Page;
import com.baidu.util.PageView;
import com.baidu.util.QueryResult;
import com.baidu.util.Request;
import com.baidu.util.Response;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DeptAction extends ActionSupport {
	
	private Dept dept;
	private DeptService deptService;
	private SelectDataService selectDataService;
	
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
	public SelectDataService getSelectDataService() {
		return selectDataService;
	}

	public void setSelectDataService(SelectDataService selectDataService) {
		this.selectDataService = selectDataService;
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:08:04
	 * ���ܣ�ת�������б�ҳ��
	 * @return
	 */
	public String listPage(){
		return "list";
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:08:25
	 * ���ܣ�ת���������ҳ��
	 * @return
	 */
	public String editPage(){
		return "edit";
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:08:53
	 * ���ܣ�ת��depttree.jspҳ��
	 * @return
	 */
	public String findTree(){
		
		return "depttree";
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:09:36
	 * ���ܣ���Ӳ���
	 */
	public void add(){	
		//System.out.println(dept.getId());
		deptService.save(dept);

	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:09:57
	 * ���ܣ�ת���޸Ĳ���ҳ��
	 * @return
	 */
	public String toModify(){
		String mid = Request.getParameter("id");
		Dept dept = deptService.getEntityById(new Integer(mid));
		ActionContext.getContext().getValueStack().push(dept);
		return "modify";
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:10:20
	 * ���ܣ�AJAX��ȡ���в��� չʾ��ztree��
	 */
	public void findDepts(){
		
		List list = selectDataService.queryForList("select id \"id\",name \"name\",'0' \"pId\" from t_dept");
		Response.write(JSONArray.fromObject(list).toString());
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:10:55
	 * ���ܣ�Ajax��ȡһ����������
	 */
	public void findonepid(){
		String mid = Request.getParameter("id");
		Dept dept = deptService.getEntityById(new Integer(mid));
		Response.write(dept.getDeptName());
		
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:11:30
	 * ���ܣ�AJAX�޸Ĳ���
	 */
	public void modify(){
		try {
			deptService.update(dept);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:11:45
	 * ���ܣ�AJAXɾ������
	 */
	public void remove(){
		String mid = Request.getParameter("id");
		deptService.deleteById("id", mid);
	}
	
   /** 
	* ��ҳ��ѯ���ز��������б�
	* @Title: listData 
	* @Description: TODO(������һ�仰�����������������) 
	* @param     �趨�ļ� 
	* @return void    �������� 
	* @throws 
	*/ 	
	public void listData(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//��ǰҳ
		int page= Integer.parseInt(Request.getParameter("page")==null?"1":Request.getParameter("page"));
		//ÿҳ��ʾ����
		int rows= Integer.parseInt(Request.getParameter("rows")==null?"10":Request.getParameter("rows"));		
		//������
//		int totalCount = (int) deptService.getCount();		
		Map map =selectDataService.getListForPage(request, "select a.id,a.name,b.name pname,c.realname firstuser,d.realname seconduser,e.realname secretary from t_dept a,t_dept b,t_user c,t_user d,t_user e where a.pid =b.id and a.firstuser =c.id and a.seconduser =d.id and a.secretary =e.id");
		
		JSONObject json = new JSONObject();
		json.put("total", ((Page)map.get("p")).getTotal());
		json.put("rows", map.get("resoultList"));
		
//		QueryResult result =	deptService.getScrollData((page-1)*rows, rows);
//		
//		JSONObject json = new JSONObject();
//		json.put("total", result.getTotalrecord());
//		json.put("rows", result.getResultlist());
		
		Response.write(json.toString());

		
	}
	
	public void listAll(){
		List<Dept> depts =	deptService.findAll();
		Response.write(JSONArray.fromObject(depts).toString());
	}


}
