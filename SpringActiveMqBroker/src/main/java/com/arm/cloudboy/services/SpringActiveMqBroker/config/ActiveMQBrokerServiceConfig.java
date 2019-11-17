package com.arm.cloudboy.services.SpringActiveMqBroker.config;

import org.apache.activemq.xbean.BrokerFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ActiveMQBrokerServiceConfig {
	 @Value("classpath:static/activemq-persit-off.xml")
	    Resource mqRes;
	    
	    @Bean(name="actvieMqEmbbedd")
	    public BrokerFactoryBean brokerFactory(){
	    	BrokerFactoryBean brokerFactory = new BrokerFactoryBean() ;
	    	Resource res  = mqRes;
	    	brokerFactory.setConfig(res);
	    	brokerFactory.setStart(true);
	    	return brokerFactory;
	    }
}
