package com.baidu.user.service;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqSender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	//@Autowired
	//private ActiveMqMessage mqMessage;
	
	@Autowired
	private ActiveMQTopic topic;
	
	public void send(String name,String content){
		topic.setPhysicalName(name);
		ActiveMqMessage mqMessage = new ActiveMqMessage();
		mqMessage.setContent(content);
		//send������Ҫһ��MessageCreator���͵Ĳ�������
		//ActiveMqMessage��ʵ����MessageCreator�ӿڣ���Ϊ���ഫ����
		jmsTemplate.send(topic,mqMessage);
	}
	
}
