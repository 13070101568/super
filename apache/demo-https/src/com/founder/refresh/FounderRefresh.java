package com.founder.refresh;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class FounderRefresh {

	
	public static void refresh(List<String> urls, List<String> dirs){

		String url = "https://r.chinacache.com/content/refresh";
		
		try {
			String json = convertJSON(urls, dirs);
			sendHttpsRequestByPost(url,json);
			/*
			//1.���httpclient
			HttpClient httpClient = new DefaultHttpClient();
			//2.request����
			HttpPost httpPost = new HttpPost(url);
			
			//Ϊrequest����ֵ������ͷ����������
			//httpPost.setHearder("","");//��������ͷ
			StringEntity requestEntity = new StringEntity(json,"UTF-8");
			requestEntity.setContentEncoding("UTF-8");
			requestEntity.setContentType("application/json");
			httpPost.setEntity(requestEntity);

			//3.ִ��httpclient�õ�response����
			//CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpResponse response = httpClient.execute(httpPost);
			//����response������Ӧ����
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			System.out.println(content);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static String convertJSON(List<String> urls, List<String> dirs) throws Exception{
		//��װcallback
		List<String> emails = new ArrayList<String>();
		emails.add("651806859@qq.com");
		RefreshCallBack refreshCallBack = new RefreshCallBack(emails);
		//��װtask
		RefreshTask refreshTask = new RefreshTask(urls,dirs,refreshCallBack);
		//���ķ�װ
		RefreshModel refreshModel = new RefreshModel();
		refreshModel.setTask(refreshTask);
		
		ObjectMapper mapper = new ObjectMapper();
    
		//mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);

		String json = mapper.writeValueAsString(refreshModel);

	    System.out.println("Java2Json: "+json);
		
		return json;
	}
	
	public static final String sendHttpsRequestByPost(String url, String json) {
		String responseContent = null;
		HttpClient httpClient = new DefaultHttpClient();
		//����TrustManager
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		//���������HOST��֤
		X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
			
			public boolean verify(String hostname, SSLSession session) {
				return false;
			}
			public void verify(String arg0, SSLSocket arg1) throws IOException {}
			public void verify(String arg0, X509Certificate arg1) throws SSLException {}
			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
		};
		try {
			//TLS1.0��SSL3.0������û��̫��Ĳ�𣬿ɴ������ΪTLS��SSL�ļ̳��ߣ�������ʹ�õ�����ͬ��SSLContext
			SSLContext ctx = SSLContext.getInstance("TLS");
			//ʹ��TrustManager����ʼ���������ģ�TrustManagerֻ�Ǳ�SSL��Socket��ʹ��
			ctx.init(null, new TrustManager[] { xtm }, null);
			//����SSLSocketFactory
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			socketFactory.setHostnameVerifier(hostnameVerifier);
			//ͨ��SchemeRegistry��SSLSocketFactoryע�ᵽ���ǵ�HttpClient��
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
			
			HttpPost httpPost = new HttpPost(url);
			StringEntity requestEntity = new StringEntity(json,"UTF-8");
			requestEntity.setContentEncoding("UTF-8");
			requestEntity.setContentType("application/json");
			httpPost.setEntity(requestEntity);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			System.out.println(content);
			//����Ϊ���ݲ���Mapʱ��д��
//			HttpPost httpPost = new HttpPost(url);
//			List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // ����POST����ı�����
//			for (Map.Entry<String, String> entry : params.entrySet()) {
//				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//			}
//			httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
//			HttpResponse response = httpClient.execute(httpPost);
//			HttpEntity entity = response.getEntity(); // ��ȡ��Ӧʵ��
//			if (entity != null) {
//				responseContent = EntityUtils.toString(entity, "UTF-8");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			// �ر�����,�ͷ���Դ
			httpClient.getConnectionManager().shutdown();
		}
		return responseContent;
	}
	
	
}
