package com.baidu.test2;


public class MailTest {

	public static void main(String[] args) throws Exception {
		MailSenderInfo mailInfo = new MailSenderInfo();    
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		//�û���    ����
		mailInfo.setUserName("17703592658@163.com");
		mailInfo.setPassword("lzq193016");		
		
		mailInfo.setFromAddress("17703592658@163.com");		//���͵�ַ
		mailInfo.setToAddress("602666769@qq.com");//���յ�ַ
		mailInfo.setSubject("����һ¶ͷ");		//����
		mailInfo.setContent("���ݵ�һ��");		//����
		
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);
		//SimpleMailSender.sendHtmlMail(mailInfo);
//		sms.sendHtmlMail(mailInfo);
		
	}
}
