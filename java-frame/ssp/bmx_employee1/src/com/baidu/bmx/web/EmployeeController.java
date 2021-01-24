package com.baidu.bmx.web;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.bmx.entity.Dept;
import com.baidu.bmx.entity.Employee;
import com.baidu.bmx.service.DeptService;
import com.baidu.bmx.service.EmployeeService;
import com.baidu.bmx.utils.PageBean;

@Controller
@RequestMapping("bmx")
public class EmployeeController {
	
	@Resource
	private EmployeeService es;
	@Resource
	private DeptService ds;
	
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * ���ߣ� ������
	 * ʱ�� ��10/26
	 * ���ܣ�չʾ
	 */
	
	@RequestMapping("list")
	private String list(HttpServletRequest request,Integer page) throws Exception{
		System.out.println(" ----- list controller-----------");
		if(page==null){
			page=1;
		}
		
		PageBean<Employee> pageBean = es.findByPage(page, 2, request.getRequestURI());
		request.setAttribute("pageBean", pageBean);
		return "list";
	}

	/**
	 * ���ߣ� ������
	 * ʱ�� ��10/26
	 * ���ܣ���ת���
	 */
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) throws Exception{
		List<Dept> deptList = ds.findAll();
		model.addAttribute("deptList", deptList);
	    return "bmx_add";
	}
	
	
	/**
	 * ���ߣ� ������
	 * ʱ�� ��10/26
	 * ���ܣ����
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public void add(Employee emp) throws Exception{
		es.save(emp);
	}
	
}
