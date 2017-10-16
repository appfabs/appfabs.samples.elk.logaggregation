package org.appfabs.sample.logaggregation.customer.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Component
public class CustomerService implements CommandLineRunner{

	private Logger logger = (Logger)LoggerFactory.getLogger(CustomerService.class);
		
	/**
	 * Access spring cloud config variable using property 
	 */
	@Value("${logging.spring-customer-service.file}")
	private String loggingFileName;
	
	public void run(String... arg0) throws Exception {
		int i = 0;
		while (true) {
			if (i++ > 500) {
				break;
			}
			logger.debug("debug message slf4j");
			logger.info("info message slf4j");
			logger.warn("warn message slf4j");
			logger.error("error message slf4j - log file name is {}", loggingFileName);
			
			Thread.sleep(1000);
		}
		
	}

}
