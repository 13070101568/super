package com.baidu.parse.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
/**
 * 
 * @author ����è��
 * ���ܣ�HttpParse������
 * ʱ�䣺2016��1��8��
 * ���ߣ�1405javab ����
 */
public class HttpParseUtil {

	/**
	 * 
	 * @author ����è��
	 * ���ܣ���ȡ�ڵ����͵�Ԫ��ֵ  ��������   ����<title id="xxx">xxxxx</title>
	 * ʱ�䣺2016��1��8��
	 * ���ߣ�1405javab ����
	 */
	public static NodeList catchOneTagElement(Parser parser,String tag,Map<String,String> map){
		
		parser.reset();
		
		TagNameFilter tagNameFilter =new TagNameFilter(tag);
		
		AndFilter andFilter = null;
		
		NodeList nodelist = null;
		
		if(map!=null){
			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String next = it.next();
				HasAttributeFilter attributeFilter = new HasAttributeFilter(next, map.get(next));
				andFilter = new AndFilter(tagNameFilter, attributeFilter);
			}
			try {
				nodelist = parser.parse(andFilter);
			} catch (ParserException e) {
				e.printStackTrace();
			}
		}else{
			try {
				nodelist = parser.parse(tagNameFilter);
			} catch (ParserException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return nodelist;
	}
	
	
	public static List<String> processNodeList(NodeList nodelist, String keyword) {
		
		SimpleNodeIterator iterator = nodelist.elements();
		List<String> list = new ArrayList<String>();
		while (iterator.hasMoreNodes()) {
			Node node = iterator.nextNode();
			NodeList childList = node.getChildren();
			
			if (null == childList){//���ӽڵ�Ϊ�գ�˵����ֵ�ڵ�
				
				if(node instanceof ImageTag){//�����IMG��ǩ
					ImageTag image  = (ImageTag)node;
					list.add(image.getImageURL());
				}else{
					String result = node.toPlainTextString();//�õ�˫��ǩֵ�ڵ��ֵ
					
					//if (result.indexOf(keyword) != -1){//�������ؼ��֣���򵥴�ӡ�����ı�
					//System.out.println(result);
					if(!result.trim().equals("")){
						list.add(result);
					}
					
					//}
				}
				
					
			} else {//���ӽڵ㲻Ϊ�գ����������ú��ӽڵ�
				processNodeList(childList, keyword);
			}
		}
		
		return list;
	}
	
	
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�����Ԫ��ֵ��ȡ
	 * ʱ�䣺2016��1��9��
	 * ���ߣ�1405javab ����
	 * @param nodelist
	 * @return
	 */
	public String processNodeList(NodeList nodelist) {
		
		String result;
		try {
			SimpleNodeIterator iterator = nodelist.elements();
			result = null;
			while (iterator.hasMoreNodes()) {
				Node node = iterator.nextNode();

				NodeList childList = node.getChildren();
				System.out.println(childList);
				if (null == childList){
					result = node.toPlainTextString();
				} else {
					processNodeList(childList);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
