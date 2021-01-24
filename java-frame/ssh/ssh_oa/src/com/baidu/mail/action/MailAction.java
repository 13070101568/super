package com.baidu.mail.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.baidu.base.service.SelectDataService;
import com.baidu.mail.bean.Mail;
import com.baidu.mail.bean.MailReceiver;
import com.baidu.mail.service.MailReceiverService;
import com.baidu.mail.service.MailService;
import com.baidu.user.bean.User;
import com.baidu.util.Page;
import com.baidu.util.Response;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

public class MailAction extends ActionSupport implements ModelDriven<Mail> {
	
	private Mail mail = new Mail();
	
	private MailService mailService;
	private MailReceiverService mailReceiverService;
	private SelectDataService selectDataService;
	
	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public MailReceiverService getMailReceiverService() {
		return mailReceiverService;
	}

	public void setMailReceiverService(MailReceiverService mailReceiverService) {
		this.mailReceiverService = mailReceiverService;
	}

	public SelectDataService getSelectDataService() {
		return selectDataService;
	}

	public void setSelectDataService(SelectDataService selectDataService) {
		this.selectDataService = selectDataService;
	}

	@Override
	public Mail getModel() {
		// TODO Auto-generated method stub
		return mail;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-13����4:29:56
	 * ���ܣ�ת��mailtree.jspҳ��
	 * @return
	 */
	public String findTree(){
		
		return "mailtree";
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����10:12:25
	 * ���ܣ������ʼ��������ʼ���
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		mailService.save(mail);
		return SUCCESS;
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����11:02:43
	 * ���ܣ�ת��list.jspҳ�� --->�ʼ�ģ����ҳ��
	 */
	public String listPage(){
		return "list";
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����11:02:43
	 * ���ܣ�ת��receivebox.jspҳ��---> ��ǰ�û����Ѷ���δ���ģ��ʼ��б�ҳ��
	 */
	public String receivePage(){
		return "receivebox";
	}
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����10:20:43
	 * ���ܣ���ѯ��ǰ�û����Ѷ���δ���ģ��ʼ�
	 */
	public void receiveList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		Map map =selectDataService.getListForPage(request, 
			"select a.id,a.receivestatus,b.title, " +
					"b.content,b.sendtime,b.sender "+
					"from t_mail_receiver a left join t_mail b "+ 
					"on a.mailid = b.id where a.reveiverid = '"+user.getId()+"' "+
					"and b.sendestatus = '����' and a.receivestatus = 'δ��' or a.receivestatus ='�Ѷ�' "+
					"order by a.receivestatus asc ");
		
		JSONObject json = new JSONObject();
		json.put("total", ((Page)map.get("p")).getTotal());
		json.put("rows", map.get("resoultList"));

		Response.write(json.toString());
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����11:02:43
	 * ���ܣ�ת��sendbox.jspҳ��---> ��ǰ�û����ѷ��͵ģ��ʼ��б�ҳ��
	 */
	public String sendPage(){
		return "sendbox";
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����10:20:43
	 * ���ܣ���ѯ��ǰ�û����ѷ��͵ģ��ʼ�
	 */
	public void sendList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		Map map =selectDataService.getListForPage(request, 
			"select distinct b.id,b.title,b.content,b.sendtime,b.sender "+ 
				"from t_mail_receiver a ,t_mail b where b.sender = '"+user.getRealname()+"' "+
				"and a.receivestatus != 'ɾ��' "+
				"and b.sendestatus = '����' order by b.sendtime asc");

		JSONObject json = new JSONObject();
		json.put("total", ((Page)map.get("p")).getTotal());
		json.put("rows", map.get("resoultList"));

		Response.write(json.toString());
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����11:02:43
	 * ���ܣ�ת��draftbox.jspҳ��---> ��ǰ�û����ʼ��ݸ壩�б�ҳ��
	 */
	public String draftPage(){
		return "draftbox";
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����10:20:43
	 * ���ܣ���ѯ��ǰ�û��� �ݸ� �ʼ�
	 */
	public void draftList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		Map map =selectDataService.getListForPage(request, 
			"select a.* from t_mail a where a.sender = '"+user.getRealname()+"' "+
				"and a.sendestatus = '�ݸ�' "+
				"order by a.sendtime asc");

		JSONObject json = new JSONObject();
		json.put("total", ((Page)map.get("p")).getTotal());
		json.put("rows", map.get("resoultList"));

		Response.write(json.toString());
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����11:02:43
	 * ���ܣ�ת��rubbishbox.jspҳ��---> ��ǰ�û����ʼ������䣩�б�ҳ��
	 */
	public String rubbishPage(){
		return "rubbishbox";
	}

	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����10:20:43
	 * ���ܣ���ѯ��ǰ�û����������  ɾ��״̬�ģ��ʼ�
	 */
	public void rubbishList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		Map map =selectDataService.getListForPage(request,
			"select id,title,content,sendtime,sender from t_mail "+
			"where sendestatus = 'ɾ��' union all "+
			"select b.id,b.title,b.content,b.sendtime,b.sender  "+
			"from t_mail_receiver a left join t_mail b  "+
			"on a.mailid = b.id where a.reveiverid = '"+user.getId()+"' "+
			"and a.receivestatus = 'ɾ��' ");

		JSONObject json = new JSONObject();
		json.put("total", ((Page)map.get("p")).getTotal());
		json.put("rows", map.get("resoultList"));

		Response.write(json.toString());
	}
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����11:02:43
	 * ���ܣ�ת��writebox.jspҳ��---> �½��ʼ�  ���ҳ��
	 */
	public String writePage(){
		return "writebox";
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����4:07:35
	 * ���ܣ����ռ����е��ʼ��Ž�������
	 */
	public void changeToRubbish(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String aid = request.getParameter("id");
		selectDataService.update("update t_mail_receiver set receivestatus ='ɾ��' where id="+aid);
		
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����4:07:35
	 * ���ܣ��Ѳݸ���ͷ������е��ʼ��Ž�������
	 */
	public void changeToRubbish2(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String aid = request.getParameter("id");
		selectDataService.update("update t_mail set sendestatus ='ɾ��' where id="+aid);
		
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-24����4:16:15
	 * ���ܣ������ʼ�
	 */
	//1�������������Զ�ת����ʽ��  
	//2��Integer-----varchar2
	//3, IFRAME
	public void sendMail(){
		mail.setSendTime(new Date());
		mailService.save(mail);
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("userid");
		if(id!=null){
			String[] ids = id.split(",");
			for (String reveiverid : ids) {
				MailReceiver mailer = new MailReceiver(mail,new Integer(reveiverid),"δ��");
				mailReceiverService.save(mailer);
			}
		}
		
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-24����4:16:15
	 * ���ܣ������ݸ�
	 */
	public void toDraftbox() {
		mail.setSendTime(new Date());
		mail.setSendeStatus("�ݸ�");
		mailService.save(mail);
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-25����8:52:11
	 * ���ܣ���ѯ��ǰ�û�δ���ʼ��ĸ��� 
	 */
	public void findNoreadmail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		//selectDataService.
		List<MailReceiver> list = mailReceiverService.findEntityByHQL(" from MailReceiver m where " +
				" m.reveiverid=? and m.receiveStatus ='δ��'", new Integer(id));
		Response.write(String.valueOf(list.size()));
	}
	
	/**
	 * 
	 * ���ߣ�1405javab ����
	 * ʱ�䣺2015-12-23����4:07:35
	 * ���ܣ����������� ��������ɾ���ʼ�
	 */
	public void delMail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("id");
		String[] aids = ids.split(",");
		selectDataService.remove("delete from t_mail where id in ("+aids+")");
		
	}

}
