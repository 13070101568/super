package com.xiaobai.utils;




import javax.servlet.http.HttpServletRequest;

public class ToolPage {
	
	public static void page(HttpServletRequest request, int page, int pageSize, String url, int totalNums) {
		//4��Ԫ�ص��ַ�������, �������htmlҳ���ϵ���ҳ����һҳ����һҳ��ĩҳ
		String[] pageArray = new String[4];
		//��ҳ��
		int totalPages =  totalNums / pageSize + (totalNums % pageSize != 0 ? 1 : 0);
		//url���α��
		String flag = url.indexOf("?") > -1 ? "&" : "?";
		
		if (page == 1) {
			pageArray[0] = "��ҳ";
		} else {
			pageArray[0] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "page=1\">��ҳ</a>";
		}
		
		if (page == 1) {
			pageArray[1] = "��һҳ";
		} else {
			pageArray[1] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "page=" + (page - 1) + "\">��һҳ</a>";
		}
		
		if (page < totalPages) {
			pageArray[2] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "page=" + (page + 1) + "\">��һҳ</a>";
		} else {
			pageArray[2] = "��һҳ";
		}
		
		if (page < totalPages) {
			pageArray[3] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "page=" + totalPages + "\">ĩҳ</a>";
		} else {
			pageArray[3] = "ĩҳ";
		}
		String ��ҳ = pageArray[0];
		String ��һҳ = pageArray[1];
		String ��һҳ = pageArray[2];
		String ĩҳ = pageArray[3];
		//��ҳ
		request.setAttribute("firstPage", ��ҳ);
		//��һҳ
		request.setAttribute("prevPage", ��һҳ);
		//��һҳ
		request.setAttribute("nextPage", ��һҳ);
		//ĩҳ
		request.setAttribute("lastPage", ĩҳ);
		//��ǰҳ
		request.setAttribute("page", page);
		//��ҳ��
		request.setAttribute("totalPages", String.valueOf(totalPages));
		//�ܼ�¼��
		request.setAttribute("totalNums", totalNums);
		//ÿһҳ��ʾ��¼
		request.setAttribute("pageSize", pageSize);
		//request.setAttribute("list", list);
		request.setAttribute("pageAll",��ҳ +" " + ��һҳ +" " + ��һҳ +" " + ĩҳ +"    ��"+totalNums+"������         "+"   ��"+page+"/"+totalPages+"ҳ");
	}
}