package com.amqp.outbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

import com.amqp.outbound.gateway.OutboundGateway;

@SpringBootApplication
@EnableIntegration
@EnableAutoConfiguration
@ImportResource({"classpath:amqp-outbound.xml"})
public class OutboundApplication {
	
	@Autowired
	public static OutboundGateway gateway;

	public static void main(String[] args) {
		SpringApplication.run(OutboundApplication.class, args);
	}
}
