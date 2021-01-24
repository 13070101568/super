package com.baidu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.baidu.parse.pojo.App;
import com.baidu.parse.pojo.Comment;
import com.baidu.parse.pojo.Pic;

public class SmallParse {

	Parser parser = null;
	
	TagNameFilter tagNameFilter = null;
	NodeList nodeList = null;
	HasAttributeFilter hasAttributeFilter = null;
	AndFilter andFilter  = null;
	HasParentFilter parentFilter = null;


	public SmallParse(String url){	
		try {
			parser = new Parser(url);
			parser.setEncoding("UTF-8");
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�ͨ����������  �õ�Ӧ������
	 * ʱ�䣺2016��1��11��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	public String parseTitle(){
		parser.reset();
		String appname = null;
		try {
			tagNameFilter = new TagNameFilter("title");
			nodeList = parser.parse(tagNameFilter);
			String nodeValue = nodeList.elementAt(0).toPlainTextString();//��ȡ˫��ǩ������
			appname = nodeValue.substring(0,nodeValue.lastIndexOf("-"));
			
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return appname;	
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�����Ӧ��ͼ��
	 * ʱ�䣺2016��1��11��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	public String parseLogo(){
		parser.reset();
		String appiconName = null;
		try {
			tagNameFilter = new TagNameFilter("img");
			hasAttributeFilter = new HasAttributeFilter("id", "appicon");//img��id="appicon"Ψһ
			andFilter = new AndFilter(tagNameFilter, hasAttributeFilter);//�����������ϲ�
			nodeList= parser.parse(andFilter);
			TagNode tagNode = (TagNode)nodeList.elementAt(0);
			String appiconUrl = tagNode.getAttribute("src");//��ȡ����ǩ������ֵ
			//http://u5.mm-img.mmarket.com:80/rs/res1/21/2013/09/05/a930/482/26482930/logo2140x1408364726173_src.jpg
			appiconName = appiconUrl.substring(appiconUrl.lastIndexOf("/")+1);
			//��ͼƬ���ص����ط�������request.getSession().getServletContext().getRealPath("/")+"\\download"+appiconName
			HttpClientUtil.httpDownload(appiconUrl,"D:\\Program Files\\GongZuo2\\apache-tomcat-7.0.54\\webapps\\onlyone\\download\\"+appiconName);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return appiconName;
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�����Ӧ�ó�������·��
	 * ʱ�䣺2016��1��11��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	public String parseURL(){
		parser.reset();
		tagNameFilter = new TagNameFilter("div");
		hasAttributeFilter = new HasAttributeFilter("class", "mj_cont_left_t");
		andFilter = new AndFilter(tagNameFilter, hasAttributeFilter);
		parentFilter = new HasParentFilter(andFilter);
		
		tagNameFilter = new TagNameFilter("a");
		andFilter = new AndFilter(tagNameFilter, parentFilter);
		
		String attribute = null;
		try {
			nodeList = parser.parse(andFilter);
			TagNode tagNode = (TagNode)nodeList.elementAt(0);
			attribute = tagNode.getAttribute("href");
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return attribute;
		
	} 
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�����Ӧ�ý���
	 * ʱ�䣺2016��1��11��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	public String parseIntroduce() {
		
		parser.reset();
		tagNameFilter = new TagNameFilter("div");
		hasAttributeFilter = new HasAttributeFilter("class", "mj_yyjs font-f-yh");
		andFilter = new AndFilter(tagNameFilter, hasAttributeFilter);
		try {
			nodeList = parser.parse(andFilter);//��ȡ�����е�li(APPӦ�����鼯��)
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return nodeList.elementAt(0).toPlainTextString();
		
	}
	
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�����Ӧ������(����Ȥ���������۸񣬰汾����С�������ߣ�������𣬸���ʱ�䣬ϵͳ֧�� ��)	
	 * ʱ�䣺2016��1��11��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	public List<String> parseDetal(){
		parser.reset();
		tagNameFilter = new TagNameFilter("div");//�����б�(��Ԫ��ul�ĸ�Ԫ��div����Ψһclass="mj_info font-f-yh")
		hasAttributeFilter = new HasAttributeFilter("class", "mj_info font-f-yh");
		andFilter = new AndFilter(tagNameFilter, hasAttributeFilter);
		parentFilter = new HasParentFilter(andFilter);
		
		tagNameFilter = new TagNameFilter("ul");
		andFilter = new AndFilter(tagNameFilter, parentFilter);
		parentFilter = new HasParentFilter(andFilter);
		
		tagNameFilter = new TagNameFilter("li");
		andFilter = new AndFilter(tagNameFilter, parentFilter);
		try {
			nodeList = parser.parse(andFilter);//��ȡ�����е�li(APPӦ�����鼯��)
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		String value = null;
		List<String> list = new ArrayList<String>();
		
		for(int i = 0;i < nodeList.size();i++){
			if(i == 0){
				Node node = nodeList.elementAt(0);
				NodeList children = node.getChildren();
				Node elementAt = children.elementAt(0);
				value = elementAt.toPlainTextString();
				list.add(value);
				//.out.println(value.substring(0,value.length()-3));//<1��
				
				
			}else if(i == 4){
				Node node = nodeList.elementAt(4);
				value = node.toPlainTextString().trim();
				list.add(value);
				//System.out.println(value.substring(value.indexOf("��")+1));//���ݺϺ���Ϣ�������޹�˾
				
			}else{
				Node node = nodeList.elementAt(i);
				value = node.toPlainTextString();
				list.add(value);
				//System.out.println(value.substring(value.indexOf("��")+1).trim());
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ���������ȡ��Ӧ�õ������ֲ�ͼƬ
	 * ʱ�䣺2016��1��11��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	public List<Pic> ParseDynamicImg(String appid) {
		parser.reset();
		tagNameFilter = new TagNameFilter("div");
		hasAttributeFilter = new HasAttributeFilter("class", "mj_lunbo");
		andFilter = new AndFilter(tagNameFilter, hasAttributeFilter);
		parentFilter = new HasParentFilter(andFilter);
		
		tagNameFilter = new TagNameFilter("div");
		andFilter = new AndFilter(tagNameFilter, parentFilter);
		
		List<Pic> list = new ArrayList<Pic>();
		try {
			nodeList = parser.parse(andFilter);//��ȡ�����ֲ�ͼƬ��div
			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.elementAt(i).getFirstChild();
				if(node instanceof ImageTag){//�����IMG��ǩ
					ImageTag image  = (ImageTag)node;
					String imageURL = image.getImageURL();
					String fileName = imageURL.substring(imageURL.lastIndexOf("/")+1);
					//HttpClient ����ģ��Զ�̷������� �� Զ����������
					//����ͼƬ//��ͼƬ���ص����ط�������request.getSession().getServletContext().getRealPath("/")+"\\download\\"+appiconName
					HttpClientUtil.httpDownload(imageURL,"D:\\Program Files\\GongZuo2\\apache-tomcat-7.0.54\\webapps\\onlyone\\download\\"+fileName);
					Pic pic = new Pic();
					pic.setApp(appid);
					pic.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0,29));
					pic.setPicurl(imageURL);
					pic.setDescription(fileName);
					list.add(pic);
				}
			}
			//list = HttpParseUtil.processNodeList(nodeList, null);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ���������ñ�Ӧ�õ�����������Ϣ
	 * ʱ�䣺2016��1��11��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	public List<Comment> parseComment(String appid){
		parser.reset();
		tagNameFilter = new TagNameFilter("div");
		hasAttributeFilter = new HasAttributeFilter("class", "mj_pl_list_i_m");
		andFilter = new AndFilter(tagNameFilter, hasAttributeFilter);
		
		List<Comment> list = new ArrayList<Comment>();
		
		try {
			nodeList = parser.parse(andFilter);
			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.elementAt(i);
				NodeList nlist = node.getChildren();
				Comment comm = new Comment();
				comm.setAppid(appid);
				for(int j=0;j<nlist.size();j++){
					if(j==0){
						Node node2 = nlist.elementAt(0);
						TagNode first = (TagNode)node2.getFirstChild().getFirstChild();
						String attr1 = first.getAttribute("style");
						comm.setCommentNum(attr1.substring(attr1.lastIndexOf(":")+1));
						String attr2 = node2.getLastChild().toPlainTextString();
						comm.setCommentUser(attr2);
					}
					if(j==1){
						String comments = nlist.elementAt(1).toPlainTextString();
						comm.setComment(comments);
					}
					if(j==2){
						Node node3 = nlist.elementAt(2);
						String attr3 = node3.getLastChild().toPlainTextString();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						Date date = null;
						try {
							date = sdf.parse(attr3);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						comm.setCommentDate(date);
					}
					comm.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 29));
					
				}
				list.add(comm);
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ���ȡ������Ӧ�õ�App
	 * ʱ�䣺2016��1��12��
	 * ���ߣ�1405javab ����
	 * @param title
	 * @return
	 */
	public App parseApp(String title){
		App app = new App();
		app.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0,30));
		app.setAppname(title);
		List<String> detal = parseDetal();
		for (int i = 0; i < detal.size(); i++) {
			String s = detal.get(i);
			
			
			if(s.indexOf("��")==-1){
				app.setDownloadNums(s);
			}else if((s.split("��")[0]).equals("�ۡ���Ǯ")){
				String s1 = s.split("��")[1].trim().equals("���")?"0.0":s.split("��")[1].trim().substring(1);
			
				app.setPrice(Double.parseDouble(s1));
			}else if((s.split("��")[0]).equals("�桡����")){
				if(s.split("��").length>=2)
				app.setVersion(s.split("��")[1]);
			}else if((s.split("��")[0]).equals("�󡡡�С")){
				app.setFilesize(s.split("��")[1]);
			}else if((s.split("��")[0]).equals("�� �� ��")){
				app.setDeveloper(s.split("��")[1]);
			}else if((s.split("��")[0]).equals("�������")){
				app.setApptype(s.split("��")[1]);
			}else if((s.split("��")[0]).equals("����ʱ��")){
				try {
					app.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd").parse(s.split("��")[1]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if((s.split("��")[0]).equals("ϵͳ֧��")){
				app.setPlatform(s.split("��")[1]);
			}
			
		}
		app.setAppicon(parseLogo());
		app.setApkurl(parseURL());
		app.setDescription(parseIntroduce());
		return app;
	}
	
	
	/**
	 * ͨ����--����ȡ������·��
	 * D:/workspace/java-eclipse/onlyone/target/classes/
	 */
	public String getServerUrl(){
		return this.getClass().getClassLoader().getResource("").getPath().substring(1);
	}
	
}
