package com.baidu.util;

import java.util.List;

public class QueryResult<T> {
	/** ÿҳ��ʾ�ķ�ҳ���� */
	private List<T> resultlist;
	/** �ܼ�¼��*/
	private long totalrecord;
	
	public List<T> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
}