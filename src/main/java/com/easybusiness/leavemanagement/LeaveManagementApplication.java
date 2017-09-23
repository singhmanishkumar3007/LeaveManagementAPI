package com.easybusiness.leavemanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.easybusiness.leavemanagement", "com.easybusiness.leavepersistence" })
public class LeaveManagementApplication extends SpringBootServletInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveManagementApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	return builder.sources(LeaveManagementApplication.class);
    }

    public static void main(String[] args) {
	SpringApplication.run(LeaveManagementApplication.class, args);
	LOGGER.info("started LeaveManagementApplication");
    }

    @Override
    public void run(String... args) throws Exception {
	LOGGER.info("in run method");

    }

}
