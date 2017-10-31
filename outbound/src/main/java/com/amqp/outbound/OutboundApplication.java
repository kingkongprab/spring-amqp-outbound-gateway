package com.amqp.outbound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@EnableIntegration
//@EnableAutoConfiguration
@ImportResource({"classpath:amqp-outbound.xml"})
public class OutboundApplication {

	/*@Autowired
	public static OutboundGateway gateway;*/

	public static void main(String[] args) {
		SpringApplication.run(OutboundApplication.class, args);
	}
}
