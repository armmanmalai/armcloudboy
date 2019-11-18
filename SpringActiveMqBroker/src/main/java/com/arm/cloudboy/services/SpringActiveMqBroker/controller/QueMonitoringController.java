package com.arm.cloudboy.services.SpringActiveMqBroker.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.advisory.AdvisorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueMonitoringController {
		
		@Autowired
		private ConnectionFactory connectionFactory;
		private JmsTemplate jmsTemplate;

	  @PostConstruct
	  public void init() {
	      this.jmsTemplate = new JmsTemplate(connectionFactory);
	  }
		
	  @GetMapping("/monitor/hello")
		public String hello() throws JMSException{
			
			return "Hello World:" + new Date() ;
		}
	  @GetMapping("/monitor/mon")
	  public String monitor(){
		  String  ret ="";
		  try
	        {
	            String serviceUrl = "service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi";
	            JMXServiceURL serviceURL = new JMXServiceURL(serviceUrl);

	            JMXConnector clientConnector = JMXConnectorFactory.connect(
	                serviceURL, null);
	            MBeanServerConnection mBeanClientConnection =
	                clientConnector.getMBeanServerConnection();

	            System.out.println("Connected...");
	            String objectName =
	                "org.apache.activemq:type=Broker,brokerName=armcloudboy,destinationType=Queue,destinationName=/queue/mailbox1";
	            ObjectName integrationMBeanObjectName = ObjectName.getInstance(objectName);

	            String serviceName = "performConcatenation";
	             ret = ret+  mBeanClientConnection.getAttribute(integrationMBeanObjectName, 
	            		"QueueSize") +"";

	            clientConnector.close();
	        }
	        catch(Exception exception)
	        {
	        	
	            exception.printStackTrace();
	            return "ERROR";
	        }
		  return ret ;
	  }
		
		
}
