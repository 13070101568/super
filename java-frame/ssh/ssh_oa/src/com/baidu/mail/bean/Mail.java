package com.baidu.mail.bean;

import java.util.Date;
/**
 * 
 * @author���ߣ�����
 * ʱ�䣺2015-12-23����9:06:11
 * ���ܣ��ʼ���
 */
public class Mail {

	private Integer id;
	
	private String title;//����
	
	private String content;//����
	
	private Date sendTime;//����ʱ��
	
	private String sender;//������
	
	private String sendeStatus;//����״̬���ݸ� �ѷ��� ɾ����

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendeStatus() {
		return sendeStatus;
	}

	public void setSendeStatus(String sendeStatus) {
		this.sendeStatus = sendeStatus;
	}
	
	
	
}
