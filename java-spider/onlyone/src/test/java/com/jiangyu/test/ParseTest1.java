package com.jiangyu.test;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.parserapplications.filterbuilder.Filter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.baidu.utils.HttpClientUtil;


public class ParseTest1 {

	public static void main(String[] args) {
		
		try {
			Parser parser =new Parser("http://mm.10086.cn/android/info/300000863435.html?from=www&fw=507#hotcom");
			parser.setEncoding("UTF-8");
			
			TagNameFilter tagNameFilter =new TagNameFilter("title");
			
			NodeList nodelist =	parser.parse(tagNameFilter);
			System.out.println(nodelist.elementAt(0).toPlainTextString());
			
			parser.reset();//�����Ѿ�����һ���� ��Ҫ�ٽ����µ� ����Ҫ��������һ��
			
			tagNameFilter =new TagNameFilter("img");
			HasAttributeFilter attributeFilter = new HasAttributeFilter("id", "appicon");
			AndFilter andFilter = new AndFilter(tagNameFilter, attributeFilter);
			nodelist =	parser.parse(andFilter);
			TagNode tagNode =(TagNode) nodelist.elementAt(0);
			System.out.println(tagNode.getAttribute("src"));
			String iconurl =tagNode.getAttribute("src");
			String fileName = iconurl.substring(iconurl.lastIndexOf("/")+1);
			System.out.println(fileName);
			//HttpClient ����ģ��Զ�̷������� �� Զ����������
			//����ͼƬ
			HttpClientUtil.httpDownload(tagNode.getAttribute("src"),"D:\\"+fileName);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
