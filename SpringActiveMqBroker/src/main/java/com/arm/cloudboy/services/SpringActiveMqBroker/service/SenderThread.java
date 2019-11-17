package com.arm.cloudboy.services.SpringActiveMqBroker.service;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SenderThread {

	  @Autowired
	  private ConnectionFactory connectionFactory;
	  private JmsTemplate jmsTemplate;

	  @PostConstruct
	  public void init() {
	      this.jmsTemplate = new JmsTemplate(connectionFactory);
	  }
	  @Scheduled(fixedRate = 1000)
	  public void sendMessage() {
		  
		      System.out.println("sending: " +1 );
		      for(int i = 0 ; i< 10 ;i++){
			      Email email = new Email() ;
	        	  email.setBody(new Date() + "#######" );
	        	  email.setTo("arm.m04@gmail.com");
	        	  //jmsTemplate.convertAndSend("/queue/mailbox", email);
			      jmsTemplate.send("/queue/mailbox1", new MessageCreator() {
			          @Override
			          public Message createMessage(Session session) throws JMSException {
			        	  Email email = new Email() ;
			        	  email.setBody(new Date() + "#######Box1" );
			        	  email.setTo("arm.m04@gmail.com");
			              return session.createTextMessage(email.toString());
			          }
			      });
		      }
		      System.out.println("sending: " +1 + ":Complete");
	  }
	  @Scheduled(fixedRate = 100)
	  public void sendMessage2() {
		  
		      System.out.println("sending: " +2 );
		      
		      jmsTemplate.send("/queue/mailbox2", new MessageCreator() {
		          @Override
		          public Message createMessage(Session session) throws JMSException {
		        	  Email email = new Email() ;
		        	  email.setBody(new Date() + "#######Box2" );
		        	  email.setTo("arm.m04@gmail.com");
		              return session.createTextMessage(email.toString());
		          }
		      });
		      System.out.println("sending: " +2 +":Complete");
	      
	  }
}
