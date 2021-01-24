package com.baidu.util;

 import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.baidu.mail.bean.Mail;


public class MailUtil {
	
    public static void setEmail(Mail mail) throws Exception {
        
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.sohu.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //ʹ��JavaMail�����ʼ���5������
        //1������session
        Session session = Session.getInstance(prop);
        //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
        session.setDebug(true);
        //2��ͨ��session�õ�transport����
        Transport ts = session.getTransport();
        //3��ʹ��������û��������������ʼ��������������ʼ�ʱ����������Ҫ�ύ������û����������smtp���������û��������붼ͨ����֤֮����ܹ����������ʼ����ռ��ˡ�
        ts.connect("smtp.sohu.com", "ppag2650c1b669ad@sohu.com", "wxh0901wxh");
        //4�������ʼ�
        Message message = createSimpleMail(session,mail);
        //5�������ʼ�
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
    
    /**
    * @Method: createSimpleMail
    * @Description: ����һ��ֻ�����ı����ʼ�
    * @throws Exception
    */ 
    public static MimeMessage createSimpleMail(Session session,Mail mail) throws Exception {
        //�����ʼ�����
        MimeMessage message = new MimeMessage(session);
        //ָ���ʼ��ķ�����
        message.setFrom(new InternetAddress("ppag2650c1b669ad@sohu.com"));
        //ָ���ʼ����ռ��ˣ����ڷ����˺��ռ�����һ���ģ��Ǿ����Լ����Լ���
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("447061319@qq.com"));
        //�ʼ��ı���
        message.setSubject("ֻ�����ı��ļ��ʼ�");
        //�ʼ����ı�����
        message.setContent("��ð���I am sohu.", "text/html;charset=UTF-8");
        //���ش����õ��ʼ�����
        return message;
    }
}
		

