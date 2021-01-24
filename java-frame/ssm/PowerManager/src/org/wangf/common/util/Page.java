package org.wangf.common.util;


import java.util.List;



/**
 * ��ҳ��ǩ������
 */
public class Page  {
	private int start;  //��ʼλ��
	private int end;    //����ҳ
	private int totalPage;  //��ҳ��
	private int cpage=1;     //��ǰҳ
	private int totalRow;    //����Ϣ��
	private int pageSize=2;    //��ҳ��λ
    private String link;
    private  List list;
	
    public Page(int cpage, int totalRow, int pageSize) {
		this.totalRow = totalRow;
		this.pageSize = pageSize;
		this.cpage = (cpage <= 0) ? 1 : cpage;
		this.totalPage = totalRow / pageSize;
		if (totalRow % pageSize > 0)
			this.totalPage = this.totalPage + 1;
		if(this.cpage > this.totalPage) this.cpage = this.totalPage;
		
		/*
		 * oracle��mysql���ݿ��л�����,1Ϊoracle��0Ϊmysql.
		 * modify by ChaiZhaohui
		 * date 2012-05-22
		 */
		
			this.start = ((this.cpage - 1) * pageSize);
			this.end = pageSize;
		}
//		this.start = (this.cpage - 1) * pageSize;
//		this.end = this.start + pageSize;
	

	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getCpage() {
		return cpage;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	public void setLink(String link) {
		this.link = link;
	}


	public List getList() {
		return list;
	}


	public void setList(List list) {
		this.list = list;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public void setEnd(int end) {
		this.end = end;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public void setCpage(int cpage) {
		this.cpage = cpage;
	}


	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
