package com.arm.cloudboy.services.SpringActiveMqBroker.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.activemq.xbean.BrokerFactoryBean;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;

@Configuration
public class ActiveMQBrokerServiceConfig {
	    @Value("classpath:static/activemq-persit-on.xml")
	    Resource mqRes;
	   
	    @Autowired
	    private ApplicationContext appContext;
	   
	    //@Autowired
	    DataSource mqDatasource ;
	    
	    @Bean(name="actvieMqEmbbedd")
	    @DependsOn({"amqDs"})
	    public BrokerFactoryBean brokerFactory() throws SQLException{
	    	BrokerFactoryBean brokerFactory = new BrokerFactoryBean() ;
	    	//mqDatasource.getConnection().close();
	    	Resource res  = mqRes;
	    	brokerFactory.setConfig(res);
	    	brokerFactory.setStart(true);
	    	brokerFactory.setApplicationContext(appContext);
	    	return brokerFactory;
	    }
	    
	   
	    @Bean("amqDs")
	    public DataSource getEembededDerby(){

	    	JdbcDataSource ds = new JdbcDataSource();
	        ds.setURL("jdbc:h2:/Users/armm/TEST_ANYTHING/armAmq.db");
	        ds.setUser("sa");
	        return ds;
	    }

}
