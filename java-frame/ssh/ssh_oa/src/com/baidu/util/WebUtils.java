package com.baidu.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	public static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
	public static ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<HttpServletResponse>();
	public static HttpServletRequest getRequest(){
		return requestHolder.get();
	}
	
	public static HttpServletResponse getResponse(){
		return responseHolder.get();
	}
	
	public static void setRequestResponse(HttpServletRequest request,HttpServletResponse response){
		WebUtils.requestHolder.set(request);
		WebUtils.responseHolder.set(response);
	}
	
	/**
	 * �������Id��,���Id�ŵ����:��λ�����λ�·���λ������λʱ��λ����λ��+(3λ�����,����3λǰ�油��),��:20100803163405346
	 * yyyyMMddHHmmss+3λ�����
	 * @return ���Id��
	 */
	public static String getRandomId(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			StringBuffer sb = new StringBuffer(sdf.format(new Date()));
			sb.append(fillZero(3,String.valueOf(new Random().nextInt(1000))));
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException("�������Id��ʧ��");
		}
	}
	
	/**
	 * ����
	 * @param length �����ĳ���
	 * @param source ��Ҫ����ĳ�����
	 * @return
	 */
	private static String fillZero(int length, String source) {
		StringBuilder result = new StringBuilder(source);
		for(int i=result.length(); i<length ; i++){
			result.insert(0, '0');
		}
		return result.toString();
	}
	
	public static void addCookie(String name, String value) {  
    	try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("URL�����쳣");
		}
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(getRequest().getContextPath());
        getResponse().addCookie(cookie);
    }
	 
    public static void addCookie(HttpServletResponse response, String name, String value) {  
    	try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("URL�����쳣");
		}
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(getRequest().getContextPath());
        //cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
    }
   
    
    /**
     * ��ȡ��½�û���session
     * @param request
     * @param name cookie������
     * @return
     */
//    public static UserInfo getLoginUser() {
//    	UserInfo loginUser = (UserInfo) getRequest().getSession().getAttribute("userinfo");
//    	return loginUser;
//    }
    
    
    /**
     * ��ȡcookie��ֵ
     * @param request
     * @param name cookie������
     * @return
     */
    public static String getCookieByName(HttpServletRequest request, String name) {
    	Map<String, Cookie> cookieMap = WebUtils.readCookieMap();
    	try {
			URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("URL�����쳣");
		}
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            String value = cookie.getValue();
            try {
            	value=URLDecoder.decode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("URL�����쳣");
			}
            return value;
        }else{
            return null;
        }
    }
    public static String getCookieByName(String name) {
    	Map<String, Cookie> cookieMap = WebUtils.readCookieMap();
    	try {
			URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("URL�����쳣");
		}
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            String value = cookie.getValue();
            try {
            	value=URLDecoder.decode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new RuntimeException("URL�����쳣");
			}
            return value;
        }else{
            return null;
        }
    }
    
    protected static Map<String, Cookie> readCookieMap() {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = getRequest().getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
        return cookieMap;
    }
    
    public static boolean copyRealFile(String srcName, String destName) {
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcName));
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destName));
			int i = 0;
			byte[] buffer = new byte[2048];
			while ((i = in.read(buffer)) != -1) {
				out.write(buffer, 0, i);
			}
			out.close();
			in.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
   
    public static String getServerUrl(){
		String url ="http://"+getRequest().getLocalAddr()+":"+getRequest().getServerPort()+"/"+getRequest().getContextPath();
		return url;
	}
    
    public static void removeUserCookie(){
    	WebUtils.addCookie("userID", "");
		WebUtils.addCookie("userIsn", "");
		WebUtils.addCookie("userName", "");
		
		WebUtils.addCookie("userGroupIsn", "");

		WebUtils.addCookie("unitIsn", "");
		WebUtils.addCookie("unitName", "");

		WebUtils.addCookie("nodeId", "");
		WebUtils.addCookie("nodeName", "");
		
    }
}
