package com.baidu.parse.controller;

import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.parse.pojo.App;
import com.baidu.parse.service.IParseService;
import com.baidu.utils.HtmlThread;

@Controller
@RequestMapping("parse")
public class ParseController {

	@Autowired
	private IParseService parseService;

	/**
	 * 
	 * @author ����è��
	 * ���ܣ�����10086��վ������APPӦ�õ������Ϣ ���������ݿ�
	 * ʱ�䣺2016��1��12��
	 * ���ߣ�1405javab ����
	 */
	@RequestMapping("parseAll")
	public void parseAll(){	
		try {
			//ץȥ��ҳ��
			Parser parser = new Parser("http://mm.10086.cn/android/software/qbrj?p=1");
			parser.setEncoding("UTF-8");
			HasAttributeFilter hasAttributeFilter = new HasAttributeFilter("class","last");
			TagNameFilter tagNameFilter = new TagNameFilter("a");
			NodeList nlist = parser.parse(new AndFilter(tagNameFilter,hasAttributeFilter));
			TagNode tagNode = (TagNode)nlist.elementAt(0);
			int totalPage = Integer.parseInt(tagNode.getAttribute("href").split("=")[1]);
			//����ÿ���߳�ץȡ��ҳ��
			int threadPageNum = 15;
			//����������߳�����
			int threadNum = totalPage/threadPageNum+(totalPage%threadPageNum==0?0:1);
			//ѭ��ץȡÿ���̵߳�Ӧ��
			for (int i = 0; i < threadNum; i++) {
				HtmlThread htmlThread = new HtmlThread("�߳�"+i,i*15+1,parseService);
				htmlThread.start();
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @author ����è��
	 * ���ܣ�ת����ɾ��Ĳ���ҳ��
	 * ʱ�䣺2016��1��15��
	 * ���ߣ�1405javab ����
	 * @return
	 */
	@RequestMapping("appAction")
	public String appAction(Model model){
		
		List<App> list = parseService.seletApps();
		
		model.addAttribute("applist", list);
		
		return "app/applist";
	}
	

	
	
	
	
}
