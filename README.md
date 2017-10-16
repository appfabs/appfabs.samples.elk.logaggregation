# Log Aggregation using ELK Stack

## Centralized Log Aggregation & Visualization using ELK Stack for Micro service Architecture

This repository is holding sample source code for centralized configuration for micro service architecture using spring cloud and two micro services for retrieving those configurations. The config client applications (customer-service and supplier-service) using net.logstash.logback.appender.LogstashTcpSocketAppender for sending all the logs to a remote machine where logstash is running. The logstash redirect all logs to elastic search.

# Spring Cloud Config Sample Repository

https://github.com/appfabs/appfabs.sample.springcloud-config-repo.git
This is a sample repository for storing centralized configuration and access those configurations using spring cloud config server. Those configuration will be available in a spring application as spring properties. 


## Project Details

- spring-cloudconfig : This is spring cloud config server application which stores all the configurations required for its clients. For getting configurations, all services needs to connect to this service.
- spring-customer-service : This is a sample spring boot application which retrieves configurations from spring cloud config server. Also by using LogstashTcpSocketAppender, this service sends logs to a remote machine for storing.
- spring-supplier-service : This is also a sample spring boot application which retrieves configurations from spring cloud config server. Also by using LogstashTcpSocketAppender, this service sends logs to a remote machine for storing.

## Running Project

- This is a maven project
- Use maven install for generating packages
- The packages will be generated in root folder under directory packages
- run spring cloud config server first using java -jar spring-cloudconfig-0.1.jar. It will connect to git repository https://github.com/appfabs/appfabs.sample.springcloud-config-repo.git and fetch configurations.
- spring-customer-service-0.1.jar and spring-supplier-service-0.1.jar can also be run using java -jar <jar_file.jar> options