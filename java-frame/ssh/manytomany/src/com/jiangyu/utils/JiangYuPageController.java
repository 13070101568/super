package com.jiangyu.utils;

import java.io.IOException;
//import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * ��ҳ������
 * 
 * С����ר�÷�ҳ������JiangYuAjaxUtils<T>
 * by 1405javaB ���� 
 * ����è�� 2015-10-11 by JiangYu
 * 
 * ע�⣺������ɾ�Ĵ�ע�ͣ�����������������㽫�ܵ������������䣡��
 */
public class JiangYuPageController extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;

	public int doStartTag() throws JspException {
		StringBuffer html = new StringBuffer("");
		int pageNo = 1;
		int pageSize = 10;
		int pageCount = 0;
		int totalCount = 0;
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		//����·��
		this.url = request.getContextPath()+"/"+this.url;
//		Enumeration<String> enum_ = request.getAttributeNames();
//		BaseListBean baseListBean = null;
//		while(enum_.hasMoreElements()){
//			String attrName = enum_.nextElement();
//			Object obj = request.getAttribute(attrName);
//			if(obj instanceof BaseListBean){
//				baseListBean = (BaseListBean) obj;
//				pageNo = baseListBean.getAbstractData().getPageNo();
//				pageSize = baseListBean.getAbstractData().getPageSize();
//				pageCount = baseListBean.getAbstractData().getPageCount();
//				totalCount = baseListBean.getAbstractData().getTotalCount();
//			}
//		}
		
		StringBuffer selHtml = new StringBuffer();
		selHtml.append("<select name=\"pageSize\" onchange=\"goPage(1)\">");
		if(pageSize==10){
			selHtml.append("<option value=\"10\" selected>10</option><option value=\"20\">20</option><option value=\"50\">50</option>");
		}
		if(pageSize==20){
			selHtml.append("<option value=\"10\">10</option><option value=\"20\" selected>20</option><option value=\"50\">50</option>");
		}
		if(pageSize==50){
			selHtml.append("<option value=\"10\">10</option><option value=\"20\">20</option><option value=\"50\" selected>50</option>");
		}
		selHtml.append("</select>");
		
		//�����ҳ����������
		html.append("<input type=\"hidden\" name=\"pageNo\" value=\""+pageNo+"\">");
		//html.append("<input type=\"hidden\" name=\"pageSize\" value=\""+pageSize+"\">");
		html.append("<input type=\"hidden\" name=\"pageCount\" value=\""+pageCount+"\">");
		
        html.append("\r\n");
        html.append("   \t\t</table>\r\n");
        html.append("   \t\t\r\n");
        html.append("   \t\t<table align=\"center\">\r\n");
        html.append("   \t\t\t<tr>\r\n");
        html.append("   \t\t\t\t<td>\r\n");
        html.append("   \t\t\t\t\t<a href=\"javascript:goPage(1)\">��ҳ</a>\r\n");
        html.append("\t   \t\t\t\t<a href=\"javascript:goPage("+((pageNo-1)>0?(pageNo-1):1)+")\">��һҳ</a>\r\n");
        html.append("\t   \t\t\t\t<a href=\"javascript:goPage("+((pageNo+1)<pageCount?pageNo+1:pageCount)+")\">��һҳ</a>\r\n");
        html.append("\t   \t\t\t\t<a href=\"javascript:goPage("+pageCount+")\">βҳ</a>\r\n");
        html.append("\t   \t\t\t\t��"+totalCount+"��"+pageCount+"ҳ\r\n");
        html.append("\tÿҳ��ʾ");
        html.append(selHtml.toString());
        html.append("��");
        html.append("\t   \t\t\t\t<input type=\"text\" value=\"\" name=\"gopageCount\" size=\"1\" >\r\n");
        html.append("\t   \t\t\t\t<input type=\"button\" value=\"go\" name=\"pgBn\" onclick=\"goPage(gopageCount.value)\">\r\n");
        html.append("   \t\t\t\t</td>\r\n");
        html.append("   \t\t\t</tr>\r\n");
        html.append("   \t\t</table>\r\n");
        html.append("   \t\t\r\n");
        html.append("   \t");
        
        html.append("<script type=\"text/javascript\">\r\n");
        html.append("function goPage(pageno){\r\n");
       // html.append("alert(pageno){\r\n");
        html.append("	var length = pageno.length;"+
					"   var flag = false; "+
					"   for(var i=0;i<length;i++){ "+
					"   	var c = pageno.charAt(i); "+
					"   	if(c<'0' || c>'9'){ "+
					" 	  	flag = true; "+
					" 	  } "+
					"   } "+
					"   if(parseInt(pageno)==0){ "+
					"   	pageno = parseInt(pageno)+1; "+
					"   } "+
					"	if(flag){ "+
					"   	alert('ҳ����������֣�'); "+
					" 	  return; "+
					"   }");
        html.append("\tvar form = document.forms[0];\r\n");
        html.append("\tvar pageCount = form.pageCount.value;\r\n");
        html.append("\tif(parseInt(pageno)>parseInt(pageCount)){\r\n\talert('ҳ�벻�ܴ�����ҳ��"+pageCount+"!');\r\n \t return;\r\n}");
        html.append("\tform.pageNo.value = pageno;\r\n");
        html.append("\tform.action = '"+this.url+"';\r\n");
        html.append("\tform.submit();\r\n");
        html.append("}\r\n");
        html.append("</script>\r\n");
        /**
         * ��ҳ������
         * 
         * С����ר�÷�ҳ������JiangYuAjaxUtils<T>
         * by 1405javaB ���� 
         * ����è�� 2015-10-11 by JiangYu
         * 
         * ע�⣺������ɾ�Ĵ�ע�ͣ�����������������㽫�ܵ������������䣡��
         */
		try {
			pageContext.getOut().print(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * ��ҳ������
	 * 
	 * С����ר�÷�ҳ������JiangYuAjaxUtils<T>
	 * by 1405javaB ���� 
	 * ����è�� 2015-10-11 by JiangYu
	 * 
	 * ע�⣺������ɾ�Ĵ�ע�ͣ�����������������㽫�ܵ������������䣡��
	 */
}
