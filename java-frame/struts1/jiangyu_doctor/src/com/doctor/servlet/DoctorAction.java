package com.doctor.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.doctor.pojo.Doctor;
import com.doctor.pojo.Xl;
import com.doctor.service.DoctorService;
import com.doctor.service.Xlservice;
import com.doctor.utils.PageBean;

public class DoctorAction extends DispatchAction {

	private DoctorService ds = new DoctorService();
	private Xlservice xs = new Xlservice();
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ���ѯ����ҽ��
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		String page = request.getParameter("page");

		String pageSize = request.getParameter("pageSize");
		
		
		if(page==null){
			page="1";
		}
		if(pageSize==null){
			pageSize="2";
		}
		
		System.out.println(page);
		
		PageBean<Doctor> pagebean = ds.queryAll(new Integer(page),new Integer(pageSize));
		
		request.setAttribute("pagebean", pagebean);
		
		return mapping.findForward("success");
	}
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ���ѯһ��ҽ��
	 */
	public ActionForward queryOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		// TODO Auto-generated method stub
		
		Doctor d = (Doctor)form;
		
		//System.out.println(d.getId());
		
		List<Xl> list = xs.query();
		//System.out.println(list.get(1).toString());
		
		Doctor dd = ds.queryOne(d.getId());
		
		request.setAttribute("list", list);
		
		request.setAttribute("dd", dd);
		
		return mapping.findForward("upsuccess");
	}
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ�ת���޸�ҳ��
	 */
	public ActionForward toSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		// TODO Auto-generated method stub
		
		List<Xl> list = xs.query();
		
		request.setAttribute("list", list);
		
		return mapping.findForward("sasuccess");
	}
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ����ҽ��
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		// TODO Auto-generated method stub
		
		Doctor d = (Doctor)form;
		
		
		
		ds.add(d);
	
		
		return mapping.findForward("addsuccess");
	}
	/**
	 * ���ߣ�1405javab����
	 * ʱ�䣺2015-09-07
	 * ���ܣ��޸�ҽ��
	 */
	public ActionForward alter(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		Doctor d = (Doctor)form;
		
		
		
		ds.alter(d);
	
		
		return mapping.findForward("altersuccess");
	}
	
	
}
