package org.appfabs.sample.logaggregation.supplier.service;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Component
public class SupplierService implements CommandLineRunner{

	private Logger logger = (Logger)LoggerFactory.getLogger(SupplierService.class);
	
	public void run(String... arg0) throws Exception {
		int i = 0;
		while (true) {
			if (i++ > 500) {
				break;
			}
			logger.debug("debug message slf4j");
			logger.info("info message slf4j");
			logger.warn("warn message slf4j");
			logger.error("error message slf4j");
			
			Thread.sleep(1000);
		}
		
	}

}
