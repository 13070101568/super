package com.baidu.mail.bean;
/**
 * 
 * @author���ߣ�����
 * ʱ�䣺2015-12-23����9:07:55
 * ���ܣ��ʼ�--�ռ����м��
 */
public class MailReceiver {

	private Integer id;
	private Mail mail;//�ʼ��������һ���ռ��˶�Ӧ����ʼ���
	private Integer reveiverid;//�ռ��˱���� �������û������
	private String receiveStatus;//�ʼ�״̬���Ѷ� δ�� ɾ����

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}
	public Integer getReveiverid() {
		return reveiverid;
	}
	public void setReveiverid(Integer reveiverid) {
		this.reveiverid = reveiverid;
	}
	public String getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public MailReceiver(Mail mail, Integer reveiverid, String receiveStatus) {
		super();
		this.mail = mail;
		this.reveiverid = reveiverid;
		this.receiveStatus = receiveStatus;
	}
	public MailReceiver() {
		super();
	}
	
	
	
}
