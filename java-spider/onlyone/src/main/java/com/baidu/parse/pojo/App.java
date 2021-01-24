package com.baidu.parse.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {

	private String id;
	
	private String appname;//Ӧ������
	
	private String version;//�汾
	
	private String appicon;//Ӧ��ͼ������·��
	
	private String apkurl;//Ӧ������·��
	
	private String description;//����
	
	private String filesize;//�ļ���С
	
	private Date updatetime;//����ʱ��
	
	private String developer;//������
	
	private String apptype;//Ӧ������
	
	private double price;//�۸�
	
	private String downloadNums;//������
	
	private String platform;//����ƽ̨
	
	private String status;//Ӧ��״̬
	
	private String codeurl;//��ά��ͼƬ·��
	
	private List<Pic> picList = new ArrayList<Pic>();
	
	private List<Comment> commentList = new ArrayList<Comment>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAppicon() {
		return appicon;
	}

	public void setAppicon(String appicon) {
		this.appicon = appicon;
	}

	public String getApkurl() {
		return apkurl;
	}

	public void setApkurl(String apkurl) {
		this.apkurl = apkurl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDownloadNums() {
		return downloadNums;
	}

	public void setDownloadNums(String downloadNums) {
		this.downloadNums = downloadNums;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodeurl() {
		return codeurl;
	}

	public void setCodeurl(String codeurl) {
		this.codeurl = codeurl;
	}

	public List<Pic> getPicList() {
		return picList;
	}

	public void setPicList(List<Pic> picList) {
		this.picList = picList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "App [id=" + id + ", appname=" + appname + ", version="
				+ version + ", appicon=" + appicon + ", apkurl=" + apkurl
				+ ", description=" + description + ", filesize=" + filesize
				+ ", updatetime=" + updatetime + ", developer=" + developer
				+ ", apptype=" + apptype + ", price=" + price
				+ ", downloadNums=" + downloadNums + ", platform=" + platform
				+ ", status=" + status + ", codeurl=" + codeurl + ", picList="
				+ picList + ", commentList=" + commentList + "]";
	}
	
	
	
	
}
