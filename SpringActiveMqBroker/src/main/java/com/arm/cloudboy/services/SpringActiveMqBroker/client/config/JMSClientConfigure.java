package com.arm.cloudboy.services.SpringActiveMqBroker.client.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@Configuration
public class JMSClientConfigure {

	  @Bean
	  public ConnectionFactory connectionFactory() {
	      ConnectionFactory connectionFactory =
	              new ActiveMQConnectionFactory("tcp://localhost:61616");
	      System.out.println("#init Client");
	      return connectionFactory;
	  }
	  
	  @Bean(name="mail01listener")
	  public JmsListenerContainerFactory jmsListenerContainerFactory() {
	      DefaultJmsListenerContainerFactory factory =
	              new DefaultJmsListenerContainerFactory();
	      factory.setConnectionFactory(connectionFactory());
	      //core poll size=4 threads and max poll size 8 threads
	      factory.setConcurrency("4-8");
	      return factory;
	  }
}
