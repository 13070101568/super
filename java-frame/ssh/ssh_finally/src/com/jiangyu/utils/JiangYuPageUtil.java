package com.jiangyu.utils;

import java.util.List;


/**
 * С����ר�÷�ҳ��Struts2PageUtil<T>
 * by 1405javaB ���� 
 * ����è�� 2015-09-07 by JiangYu
 */
public final class JiangYuPageUtil<T> {
	private Integer pageIndex;// ��ǰҳ�ĵ�һ�����������ݿ������
	private String firstPage;// ��ҳ
	private String prePage;// ��һҳ
	private String nextPage;// ��һҳ
	private String lastPage;// βҳ
	private Integer pageCount;// ���ݿ��������ҳ����չʾ����ҳ��
	private Integer currentPageNo;// ��ǰҳ��ҳ��
	private Integer listCount;// չʾ�����ݿ����ݵ�������
	private String pageAll;//�����һ���ַ��� ��������һҳ����һҳ�����ĳ����� ��
	private List<T> list;// ��ǰҳչʾ������

	@SuppressWarnings("unused")
	private String ajaxPage;//ajax��ҳ�ַ���
	
	/**
	 * 
	 * @param request
	 *            service��request����
	 * @param listCount
	 *            չʾ�����ݿ��������
	 * @param onePageCount
	 *            ��ҳʱÿһҳչʾ������
	 * @param currentPageNo
	 *            ��ǰ��ҳ��ҳ��
	 * @param list
	 *            ��ǰ��ҳ��չʾ����,ͨ���ȼ���ͨ��sql��������ݿ��в��������һ��list����
	 * @param gongcheng
	 *            ��ǰweb project��Ŀ��
	 *            
	 * С����ר�÷�ҳ��Struts2PageUtil<T>
	 * by 1405javaB ���� 
	 * ����è�� 2015-09-07 by JiangYu
	 */
	public static <T> JiangYuPageUtil<T> page(String gongcheng, String namespace,
			String actionName, Integer listCount,
			Integer currentPageNo, Integer onePageCount, List<T> list) {
		JiangYuPageUtil<T> jiangYuPageUtil = new JiangYuPageUtil<T>();
		Integer pageCount = listCount < onePageCount ? 1 : (listCount
				/ onePageCount + (listCount % onePageCount == 0 ? 0 : 1));
		Integer pageIndex = (currentPageNo - 1) * onePageCount;
		String firstPage = "";
		String prePage = "";
		String nextPage = "";
		String lastPage = "";

		if (currentPageNo == 1) {
			firstPage = " ��ҳ";
			prePage = " ��һҳ";
		} else {
			if (namespace == null) {
				firstPage = " <a href='/" + gongcheng + "/" + actionName
						+ "?page=1'>��ҳ</a>";
				prePage = " <a href='/" + gongcheng + "/" + actionName
						+ "?page=" + (currentPageNo - 1)
						+ "'>��һҳ</a>";
			} else {
				firstPage = " <a href='/" + gongcheng + "/" + namespace + "/"
						+ actionName + "?page=1'>��ҳ</a>";
				prePage = " <a href='/" + gongcheng + "/" + namespace + "/"
						+ actionName + "?page="
						+ (currentPageNo - 1) + "'>��һҳ</a>";

			}
		}

		if (currentPageNo == pageCount) {
			lastPage = " ĩҳ";
			nextPage = " ��һҳ";
		} else {
			if (namespace == null) {
				lastPage = " <a href='/" + gongcheng + "/" + actionName
						+ "?page=" + pageCount + "'>ĩҳ</a>";
				nextPage = " <a href='/" + gongcheng + "/" + actionName
						+ "?page=" + (currentPageNo + 1)
						+ "'>��һҳ</a>";
			} else {
				lastPage = " <a href='/" + gongcheng + "/" + namespace + "/"
						+ actionName + "?page=" + pageCount
						+ "'>ĩҳ</a>";
				nextPage = " <a href='/" + gongcheng + "/" + namespace + "/"
						+ actionName + "?page="
						+ (currentPageNo + 1) + "'>��һҳ</a>";
			}
		}
		String as ="";
		String a ="";
		for(int i=1;i<=pageCount;i++){
			if(namespace == null){
				a = " <a href='/" + gongcheng + "/" 
						+ actionName + "?page="
						+  i  + "'>"+i+"</a> ";
			}else{
				a = " <a href='/" + gongcheng + "/" + namespace + "/"
						+ actionName + "?page="
						+  i  + "'>"+i+"</a> ";
			}
			
			as = as + a;
		}
		// ���磺1/3ҳ
		String pageAll = "��" + listCount + "������ " + currentPageNo + " / "
				+ pageCount + "ҳ " + firstPage + prePage + as +nextPage + lastPage;

		jiangYuPageUtil.setPageIndex(pageIndex);// ��ǰҳ�ĵ�һ�����������ݿ������
		jiangYuPageUtil.setFirstPage(firstPage);// ��ҳ��Ϣ
		jiangYuPageUtil.setPrePage(prePage);// ��һҳ����Ϣ
		jiangYuPageUtil.setNextPage(nextPage);// ��һҳ����Ϣ
		jiangYuPageUtil.setLastPage(lastPage);// βҳ����Ϣ
		jiangYuPageUtil.setPageCount(pageCount);// ���ݿ��������ҳ����չʾ����ҳ��
		jiangYuPageUtil.setCurrentPageNo(currentPageNo); // ��ǰҳ��ҳ��
		jiangYuPageUtil.setListCount(listCount); // չʾ�����ݿ����ݵ�������
		jiangYuPageUtil.setPageAll(pageAll);
		jiangYuPageUtil.setList(list);// ��ǰҳչʾ������
		return jiangYuPageUtil;
	}

	//AJAX��ҳ
	public String getAjaxPage() {
		
		if(currentPageNo==1) {
			firstPage="��ҳ";
			prePage="��һҳ";
        }else{
        	firstPage="<a href='javaScript:void(0)' onclick='goPage("+1+")'>��ҳ</a> ";
        	prePage="<a href='javaScript:void(0)' onclick='goPage("+(getCurrentPageNo()-1)+")'>��һҳ</a> ";
        }
		
		String bs ="";
		String b ="";
		for(int i=1;i<=getPageCount();i++){
			if(i == getCurrentPageNo()){
				b= i +"";
			}else{
				b = "<a href='javaScript:void(0)' onclick='goPage("+i+")'>"+i+"</a> ";
			}
			
			bs = bs +"  "+ b;
		}
		
		if(currentPageNo==getPageCount() || getPageCount()==1) {
			lastPage= "ĩҳ";
	       	nextPage="��һҳ";
	    }else{
	    	lastPage= "<a href='javaScript:void(0)' onclick='goPage("+getPageCount()+")'>ĩҳ</a> ";
	        nextPage="<a href='javaScript:void(0)' onclick='goPage("+(getCurrentPageNo()+1)+")'>��һҳ</a> ";
	    }
		
		return  "�ܼ�¼��"+ getListCount()+" "+
				"��ǰҳ"+getCurrentPageNo()+"/"+getPageCount()+"  "+
				firstPage +" "+ prePage +" "+ bs +" "+ nextPage + " "+ lastPage;
		
	}
	
	
	public void setAjaxPage(String ajaxPage) {
		this.ajaxPage = ajaxPage;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public String getPrePage() {
		return prePage;
	}

	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getLastPage() {
		return lastPage;
	}

	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public Integer getListCount() {
		return listCount;
	}

	public void setListCount(Integer listCount) {
		this.listCount = listCount;
	}

	public String getPageAll() {
		return pageAll;
	}

	public void setPageAll(String pageAll) {
		this.pageAll = pageAll;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
