# Centralized Log Aggregation & Visualization using ELK Stack for Micro service Architecture

![alt text](https://github.com/appfabs/appfabs.samples.elk.logaggregation/blob/master/docs/architecture.png)

## Source code part

### About source

This repository is holding sample source code for centralized configuration for micro service architecture using spring cloud and two micro services for retrieving those configurations. The config client applications (customer-service and supplier-service) using net.logstash.logback.appender.LogstashTcpSocketAppender for sending all the logs to a remote machine where logstash is running. The logstash redirect all logs to elastic search.

### Spring Cloud Config Sample Repository

https://github.com/appfabs/appfabs.sample.springcloud-config-repo.git
This is a sample repository for storing centralized configuration and access those configurations using spring cloud config server. Those configuration will be available in a spring application as spring properties. 


### Project Details

- spring-cloudconfig : This is spring cloud config server application which stores all the configurations required for its clients. For getting configurations, all services needs to connect to this service.
- spring-customer-service : This is a sample spring boot application which retrieves configurations from spring cloud config server. Also by using LogstashTcpSocketAppender, this service sends logs to a remote machine for storing.
- spring-supplier-service : This is also a sample spring boot application which retrieves configurations from spring cloud config server. Also by using LogstashTcpSocketAppender, this service sends logs to a remote machine for storing.

### Running Project

- This is a maven project
- Use maven install for generating packages
- The packages will be generated in root folder under directory packages
- run spring cloud config server first using java -jar spring-cloudconfig-0.1.jar. It will connect to git repository https://github.com/appfabs/appfabs.sample.springcloud-config-repo.git and fetch configurations.
- spring-customer-service-0.1.jar and spring-supplier-service-0.1.jar can also be run using java -jar <jar_file.jar> options

## ELK Configurations

Here giving Ubuntu 16.04 as our reference operating system to explain installation steps

### Install Elastic Search

Goto https://www.elastic.co/downloads/elasticsearch and download the required package for your operating system. 

For ubuntu, download the latest debian package.
Then install the package using

`$ sudo dpkg –i elasticsearch-5.6.3.deb`

The default installation directory of elastic search is /usr/share/elasticsearch. Elasticsearch configuration file can be located at /etc/elasticsearch/elasticsearch.yml. You need to modify http.port and network.host properties to a valid IP address and port number for accessing from outside network. 

Example :

network.host : 192.168.0.104

http.port : 9200

Starting the elastic search service using

`$ sudo service elasticsearch start`

The elastic search can be accessed using http://192.168.0.104:9200

### Install Kibana

Download latest Kibana from https://www.elastic.co/downloads/kibana

Then install the package using

`$ sudo dpkg –i kibana-5.6.3-amd64.deb`

The default installation directory of kibana is /usr/share/kibana. Kibana configuration file can be located at /etc/kibana/kibana.yml. You need to modify server.port and server.host properties to a valid IP address and port number for accessing from outside network. 

Example :

server.host : 192.168.0.104
server.port : 5601

Starting Kibana Service

`$ sudo service kibana start`

Now pointed to your web-browser and type the address http://192.168.0.104:5601 will bring kibana web interface in front of you.

### Install Log Stash

Download latest Logstash from https://www.elastic.co/downloads/logstash 

Then install the package using

`$ sudo dpkg –i logstash-5.6.3.deb`

Now the package is installed to /usr/share/logstash. Logstash configurations are available at /etc/logstash/. You can edit logstash.yml file and can enter a valid ip address in http.host property for remote access.

Example :

http.host : 192.168.0.104

#### Logstash Configuration

Create a configuration file named appfabs-log-configuration.conf at location /usr/share/Logstash/ with the below entries.

```
input {
    tcp {
        port => 5043
        codec => json_lines
    }
}
filter {
}
output {
  stdout {
      codec => rubydebug
  }
  elasticsearch {
      hosts=>"192.168.0.104:9200"
      index=>"appfabs-logback"
  }
}
```

**Running configuraion**

In the terminal, goto /usr/share/Logstash. Enter the below command. 

`$ bin/logstash –f appshield-log-configuration.conf`

## Final Steps

If all the ELK services are running correctly and the microservices running with sending logs, then the logstash terminal will be listed with json strings as shown below

```
{
      "logfiledirectory" => "/var/log",
                 "level" => "WARN",
               "logfile" => "spring-customer-service.log",
            "maxHistory" => "60",
          "logstashhost" => "192.168.0.104:5043",
           "maxFileSize" => "100MB",
               "message" => "warn message slf4j",
        "maxArchiveSize" => "10GB",
            "@timestamp" => 2017-10-16T17:17:14.102Z,
                  "port" => 15646,
           "thread_name" => "main",
           "level_value" => 30000,
    "productionLogLevel" => "INFO",
              "@version" => 1,
                  "host" => "192.168.0.101",
       "stagingLogLevel" => "INFO",
           "logger_name" => "org.appfabs.sample.logaggregation.customer.service.CustomerService",
           "devLogLevel" => "DEBUG"
}
```

Now goto kibana and create index pattern using appfabs-logback*. Now the logs can be filtered using that pattern.


 
