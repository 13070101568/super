package org.wangf.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wangf.common.util.Page;
import org.wangf.service.PowerService;

@Controller
@RequestMapping(value="/power")
public class PowerAction {
	@Autowired
	private PowerService powerService;
	Page page = new Page();
	Map map = new HashMap();
	@RequestMapping(value="getPowerTree")
	@ResponseBody
	public List findPowerTree(){
		List list = powerService.findPowerTree();
		return list;
	}
	
	 @RequestMapping(value="/findRoleList")
	  public void findRoleList(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		 response.setCharacterEncoding("utf-8");
		  // ��ҳ��λ
		  String rows = request.getParameter("rows");
		  // ��ǰҳ
		  String cpage = request.getParameter("page");
		  page.setCpage(Integer.parseInt(cpage));
		  page.setPageSize(Integer.parseInt(rows));
		  page=powerService.findRoleList(page);
		  JSONObject jSONObject =  new JSONObject();
		  //  ��ǰҪչʾ������
		  jSONObject.put("rows",page.getList());
		  //  ����Ϣ��
		  jSONObject.put("total", page.getTotalRow());
		  //  ��ǰҳ
		  jSONObject.put("page", cpage);
		  
		  response.getWriter().print(jSONObject);
		 
	 }
	 
	  @RequestMapping(value="/findRoleById")
	  public void findRoleById(HttpServletRequest request ,HttpServletResponse response ) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.getWriter().print("����");
	  }
	  
	  @RequestMapping(value="/addRolePower")
	  public void addRolePower(HttpServletRequest request ,HttpServletResponse response ) throws IOException{
		String powerids = request.getParameter("powerids");
		String roleid = request.getParameter("roleid");
		map.put("roleid", roleid);
		map.put("powerids", powerids);
		Boolean ss= powerService.addRolePower(map);
		
		response.setCharacterEncoding("utf-8");
		//��Ҫȥ��̨ȡ��ǰ�Ľ�ɫid���ڵĽ�ɫ���ƣ����ҷ���
		response.getWriter().print("����");
	  }
	  
	  @RequestMapping(value="/findRolePower")
	  public void findRolePower(HttpServletRequest request ,HttpServletResponse response ,String roleid) throws IOException{
		 List list= powerService.findRolePower(roleid);
		 JSONArray powerid = JSONArray.fromObject(list);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(powerid);
	  }
	  
	  @RequestMapping(value="findPowerTreeByUserid")
	  @ResponseBody
	  public List findPowerTreeByUserId(String userid){
		  List list = powerService.findPowerTreeByUserId("1");
		  return list;
	  }
	  
}
