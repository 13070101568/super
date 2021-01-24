package com.baidu.bmx.utils;

import java.util.ArrayList;
import java.util.List;
public class PageBean<T>  {
	
	/**
	 * ��ҳ
	 */
	
	//��ǰҳ
	private int page=1 ;
	//ÿҳ��ʾ��
	private int pageSize ;
	
	private List<T> list = new ArrayList<T>()	;
	//��ҳ��
	private int totalPages ;
	//�ܼ�¼��
	private int totalNums ;
	//ÿҳ��ʵ������
	private int actualPageSize ;
	//��ҳ
	private String url;
	
	
   @SuppressWarnings("unused")
    private String pageing;
    private String pageIndex;
    private String before;
    private String next;
    private String last;
  
  
	public String getPageing() {
		String flag = null;
		 if(url!=null&&!"".equals(url)){  flag = url.indexOf("?") > -1 ? "&" : "?";}
		if(page==1&&getPrevPage()==1) {
       	 pageIndex= "<a>��ҳ</a>";
       	 before="<a>��һҳ</a>";
        }else{
        	pageIndex=	"<a href='"+ url+flag+"page=1'>��ҳ</a>";
        	before="<a href='"+ url+flag+"page="+getPrevPage()+"'>��һҳ</a> ";
        }
		if((page==getTotalPages()&&getNextPage()==getTotalPages())||getTotalPages()==0) {
	       	 last= "<a>ĩҳ</a>";
	       	 next="<a>��һҳ</a>";
	        }else{
	        	last= "<a href='"+ url+flag+"page="+getTotalPages()+"'>ĩҳ</a> ";
		       	 next="<a href='"+ url+flag+"page="+getNextPage()+"'>��һҳ</a> ";
	        }
	return  "�ܼ�¼��"+ getTotalNums()+" "+
			"��ǰҳ"+getPage()+"/"+getTotalPages()+""+
         
	
			pageIndex +
    before 
     +next+
    last;
    
    
}

   

	/*
	 * ������Ҫ���ѯҳ��С�ڵ�����ҳ ������ҳ 
	 * ������Ҫ���ѯҳ�����ڵ���βҳ����βҳ
	 */
	public int getPage() {
		if(page <= 1)
		{
			page = 1 ;
			
		}
		else if(page >= totalPages)
		{
			page = totalPages;
		}
		
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(int totalNums) {
		this.totalNums = totalNums;
	}

	public int getActualPageSize() {
		return actualPageSize;
	}

	public void setActualPageSize(int actualPageSize) {
		this.actualPageSize = actualPageSize;
	}
	
	/*
	 * �Ƿ�����һҳ
	 */
	public boolean isHasNext(){
		if(getPage() < getTotalPages()){
			return true;
		}
		return false;
	}
	
	/*
	 * �Ƿ�����һҳ
	 */
	public boolean isHasPrev(){
		if(getPage() > 1){
			return true;
		}
		return false;
	}
	
	/*
	 * ��ȡ��һҳ
	 */
	public int getPrevPage(){
		if(isHasPrev()){
			return getPage() - 1;
		}
		return getPage();
	}
	
	/*
	 * ��ȡ��һҳ
	 */
	public int getNextPage(){
		if(isHasNext()){
			return getPage() + 1;
		}
		return getPage();
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
