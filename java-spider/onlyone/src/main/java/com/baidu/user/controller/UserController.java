package com.baidu.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.user.pojo.User;
import com.baidu.user.service.ActiveMqSender;
import com.baidu.user.service.IUserService;
import com.baidu.utils.ChangeWord;
import com.baidu.utils.HttpClientUtil;
import com.baidu.utils.Md5Util;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ActiveMqSender activeMqSender;
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�MQ������Ϣ
	 * ʱ�䣺2016��1��25��
	 * ���ߣ�1405javab ����
	 */
	@RequestMapping("mqsend")
	public void mqsend(){
		System.out.println("111");
		activeMqSender.send("111", "bbb");
	}
	
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�ת��ע��ҳ��
	 * ʱ�䣺2016��1��4��
	 * ���ߣ�1405javab ����
	 */
	@RequestMapping("toRegister")
	public String toRegister(){
		return "user/register";
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�ת����½ҳ��
	 * ʱ�䣺2016��1��4��
	 * ���ߣ�1405javab ����
	 */
	@RequestMapping("toLogin")
	public String toLogin(){
		return "user/login";
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ��û�ע��
	 * ʱ�䣺2016��1��4��
	 * ���ߣ�1405javab ����
	 */
	@RequestMapping("register")
	public void register(User user) {
		
		userService.register(user);
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ��û���½
	 * ʱ�䣺2016��1��4��
	 * ���ߣ�1405javab ����
	 */
	@RequestMapping("login")
	public void login(User user,HttpServletResponse response) {
		
		try {
			userService.login(user);
			response.getWriter().print("ok");
		} catch (Exception e) {
			String message = e.getMessage();
			try {
				response.getWriter().print(message);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	public String getToken(String url){
		return null;
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ���������½
	 * ʱ�䣺2016��1��7��
	 * ���ߣ�1405javab ����
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("otherLogin")
	public String otherLogin(String code,Model model) {//��ȡ�ٶ���ữ���񴫵ݵ�code
		
		String url="https://openapi.baidu.com/social/oauth/2.0/token";
        //social redirect_uri registered in developer.baidu.com
		String redirect_uri = 
              "http://127.0.0.1:8080/onlyone/user/otherLogin.do";
		Map paramsMap = null;
		String client_id = "iZ2OrS1XxmK0udqo4Mwcbiuz";
		String client_secret = "pErxPcTIkl2UvL1XurwinOkS5zNrQRuA";
		paramsMap =new HashMap<String, String>();
		paramsMap.put("grant_type", "authorization_code");
		paramsMap.put("client_id", client_id);//[�ڡ�������Ϣ���е�API Key��ֵ];
		paramsMap.put("client_secret", client_secret);//[�ڡ�������Ϣ���е�Secret Key ��ֵ]
		paramsMap.put("redirect_uri", redirect_uri);
		paramsMap.put("code", code);
		//��ȡ������token�ȣ������Ϣ: "expires_in":7776000,"access_token":"51.902629ff03554983f841f27798f621d6.7776000.1459904659.3674570194-7606124","session_secret":"495b196ac81dfe01e4a9eb8062358051","session_key":"8aKDBFBCXPSSQaItNTOionA3GGAAJS6kXGatsgIfKrWT9CREbjP\/sXTLlhK9tIBIygCCmxW+fVjj7NFZ7HMwC8\/mkIae2EZZlg==","name":"\u5929\u84dd\u84dd\u7237\u7237","media_uid":"16AE426C7300F185C7D88FBFC0F34C20","social_uid":3674570194,"media_type":"qqdenglu"
		String returnJson =HttpClientUtil.post(url, paramsMap);
		System.out.println(returnJson);
		//�������Ϣ�� ��ȡ����
		String access_token  =
				JSONObject.fromObject(returnJson).get("access_token").toString();
		
		
		//�������ƻ�ȡ������վ���˺���Ϣ
		url="https://openapi.baidu.com/social/api/2.0/user/info";
		paramsMap.clear();
		paramsMap.put("access_token", access_token);
		//��ȡ�û���Ϣ---- ͼƬ �ǳƵ�  //��ȡ��������վ�û���
		returnJson = HttpClientUtil.post(url, paramsMap);
		System.out.println(returnJson);
		JSONObject json = JSONObject.fromObject(returnJson);
		String username = json.get("username").toString();
		String social_uid = json.get("social_uid").toString();//��ȡ��ữƽ̨��ͳһuid
		
		//�ж��û��Ƿ��½��
		Boolean flag = userService.checkIsfirst(social_uid);
		//�����½��  ����ת��ҳ
		//û�е�½��  ����ת�������û���Ϣҳ��
		if(!flag){
			return "index";
		}else{
			//�Ž�����������
			model.addAttribute("nickName", ChangeWord.unicodeToChinese(username));
			model.addAttribute("social_uid", social_uid);
			model.addAttribute("access_token", access_token);
			return "user/otherMsg";
		}
		
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ��������û�������Ϣ �� ��
	 * ʱ�䣺2016��1��7��
	 * ���ߣ�1405javab ����
	 * @param user
	 */
	@RequestMapping("finishOtherMsg")
	public void finishOtherMsg(User user,String access_token) {
		userService.finishOtherMsg(user);
		
		//�鿴��״̬
		String url="https://openapi.baidu.com/social/api/2.0/user/bind_status";
		Map paramsMap =new HashMap<String, String>();
		paramsMap.put("access_token", access_token);
		String returnJson =HttpClientUtil.post(url, paramsMap);
		JSONObject json = JSONObject.fromObject(returnJson);
		Object obj = json.get("media_type");
		if(obj==null){
			//���������
			url = "https://openapi.baidu.com/social/api/2.0/user/bind";
			paramsMap.put("uid", user.getId());
			paramsMap.put("uid_sign",Md5Util.getMD5(user.getId()+"pErxPcTIkl2UvL1XurwinOkS5zNrQRuA"));
			returnJson =HttpClientUtil.post(url, paramsMap);
			json = JSONObject.fromObject(returnJson);
			String result = json.get("result").toString();
			System.out.println(result);
			
		}
		
	}
	
	
	
}
