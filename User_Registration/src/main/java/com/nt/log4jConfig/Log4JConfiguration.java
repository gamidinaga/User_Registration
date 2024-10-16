package com.nt.log4jConfig;

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:/com/nt/commons/log4j.properties" })
public class Log4JConfiguration {
           
	public static void  log4jConfigurer() {
		ResourceBundle  bundle=null;
		Set<String> keys=null;
		Properties props=null;
		//get the bundle object
		bundle=ResourceBundle.getBundle("com/nt/commons/log4j");
		//get keySet from bundle
		keys=bundle.keySet();
		props=new Properties();
		//get all keys from log4j file
		for(String key:keys) {
			props.setProperty(key,bundle.getString(key));
		}
		PropertyConfigurator.configure(props);
	}	
}
