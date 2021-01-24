package com.jiangyu.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangyu.pojo.City;
import com.jiangyu.pojo.Street;
import com.jiangyu.service.ICoreService;

@Controller
@RequestMapping("core")
public class CoreAction {

	@Autowired
	private ICoreService coreService;
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-11-21����9:39:26
	 * ���ܣ���ѯ���г�����Ϣ
	 * @return
	 */
	@RequestMapping("findCitys")
	@ResponseBody
	public List<City> findCitys(){
		
		List<City> list = coreService.findCitys();
		
		return list;
	}
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-11-21����11:55:18
	 * ���ܣ�������ģ����ѯ��ǰ���еĽֵ�
	 * @param mess
	 * @param cityid
	 * @return
	 */
	@RequestMapping("findStreetsByCondition")
	@ResponseBody
	public List<Street> findStreetsByCondition(String mess,String cityid){
		
		List<Street> list = coreService.findStreetsByCondition(mess,cityid);
		
		return list;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-11-21����11:56:03
	 * ���ܣ�������Ϣ ��ӵ�t_address��
	 * @param city
	 * @param street
	 * @param meg
	 * @return
	 */
	@RequestMapping("addAddress")
	@ResponseBody
	public String addAddress(String city,String street,String meg){
		
		
		coreService.addAddress(city,street,meg);
		
		return "1";
	}
	
}
