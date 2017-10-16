package org.appfabs.sample.logaggregation.supplier.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(value ="org.appfabs.sample.logaggregation.**")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SupplierServiceApplication 
{
    public static void main( String[] arguments )
    {
    	 SpringApplication.run(SupplierServiceApplication.class, arguments);
    }
}
