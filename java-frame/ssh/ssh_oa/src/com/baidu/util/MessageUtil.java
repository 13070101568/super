package com.baidu.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * ���ֻ�����Ϣ
 * �й�������http://www.smschinese.cn/default.shtml�������ֻ���Ϣ
 * �û���wangxiaohong
 * ����973958
 * ��Ҫ�����û���Ϣ�޸�----�������ĺ�׺�޸�
 */
public class MessageUtil {
	public static void sendMsg(String phone,String content){
		try {
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
			post.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=gbk");// ��ͷ�ļ�������ת��
			NameValuePair[] data = { new NameValuePair("Uid", "wangxiaohong"),//��վ�û���
					new NameValuePair("Key", "ea03cb96a699850b2b6c"),//�ӿڰ�ȫ��Կ
					new NameValuePair("smsMob", phone),//Ŀ���ֻ���
					new NameValuePair("smsText",content) };//��������
			post.setRequestBody(data);
			
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
			System.out.println(result); // ��ӡ������Ϣ״̬
			post.releaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * ԭ����
	 */
	public static void main(String[] args) throws Exception {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");// ��ͷ�ļ�������ת��
		NameValuePair[] data = { new NameValuePair("Uid", "wangxiaohong"),
				new NameValuePair("Key", "ea03cb96a699850b2b6c"),
				new NameValuePair("smsMob", "18010471740"),
				new NameValuePair("smsText", "��ү����õ�Ŷ��") };
		post.setRequestBody(data);
		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		System.out.println(result); // ��ӡ������Ϣ״̬
		post.releaseConnection();
	}
	
	/**
	 * 	�������
	 *  statusCode:200
	 *	Cache-Control: no-cache
	 *  Content-Length: 1
	 *	Content-Type: text/html
	 *	Expires: Thu, 24 Dec 2015 23:39:56 GMT
	 *  Server: Microsoft-IIS/7.5
	 *	Set-Cookie: CHNET=Temp%5Fusername=201512267395623577; expires=Thu, 20-Sep-2018 23:39:56 GMT; path=/
	 *  Set-Cookie: ASPSESSIONIDCACBABBS=PFJFOJMCOEHHLMHIOECOOGGI; path=/
	 *  X-Powered-By: ASP.NET
	 *	Date: Fri, 25 Dec 2015 23:39:56 GMT
	 *  1����ʾ���͵�������
	 */
	

}
