package com.baidu.util;


public class Page {
	private int page;//��ǰҳ
	private int pageSize;//ÿҳ����
	private int total;//����
	private int pageNum;//ҳ��
	private Object data;//����
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Page() {
		
	}
	/**
	 * 
	 * @param start
	 * @param totalSize
	 * @param pageSize
	 * @param data
	 */
	public Page(int page, int total, int pageSize, Object data) {
		this.pageSize = pageSize;
		this.page = page;
		this.total = total;
		this.data = data;
	}
	public int getPage() {
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageNum() {
		pageNum=(total+pageSize-1)/pageSize;
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public static int getStartOfPage(int page, int pageSize) {
		return (page - 1) * pageSize;
	}
	
}
