package com.bw.utils;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ToolPage {
	
	public static void page(HttpServletRequest request, int currentPage, int pageSize, String url, int listCount, List list) {
		//4��Ԫ�ص��ַ�������, �������htmlҳ���ϵ���ҳ����һҳ����һҳ��ĩҳ
		String[] pageArray = new String[4];
		//��ҳ��
		int pageCount =  listCount / pageSize + (listCount % pageSize != 0 ? 1 : 0);
		//url���α��
		String flag = url.indexOf("?") > -1 ? "&" : "?";
		
		if (currentPage == 0) {
			pageArray[0] = "��ҳ";
		} else {
			pageArray[0] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "currentPage=0\">��ҳ</a>";
		}
		
		if (currentPage == 0) {
			pageArray[1] = "��һҳ";
		} else {
			pageArray[1] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "currentPage=" + (currentPage - 1) + "\">��һҳ</a>";
		}
		
		if (currentPage < pageCount - 1) {
			pageArray[2] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "currentPage=" + (currentPage + 1) + "\">��һҳ</a>";
		} else {
			pageArray[2] = "��һҳ";
		}
		
		if (currentPage < pageCount - 1) {
			pageArray[3] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "currentPage=" + (pageCount - 1) + "\">ĩҳ</a>";
		} else {
			pageArray[3] = "ĩҳ";
		}
		//��ҳ
		request.setAttribute("firstPage", pageArray[0]);
		//��һҳ
		request.setAttribute("precursorPage", pageArray[1]);
		//��һҳ
		request.setAttribute("nextPage", pageArray[2]);
		//ĩҳ
		request.setAttribute("lastPage", pageArray[3]);
		//��ǰҳ
		request.setAttribute("currentPage", String.valueOf(currentPage + 1));
		//��ҳ��
		request.setAttribute("pageCount", String.valueOf(pageCount));
		//�ܼ�¼��
		request.setAttribute("listCount", listCount);
		//ÿһҳ��ʾ��¼
		request.setAttribute("pageSize", pageSize);
		//request.setAttribute("list", list);
	}
}