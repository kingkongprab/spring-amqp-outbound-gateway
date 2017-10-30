package com.amqp.outbound.service;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Service;


@Service
public final class OuboundService {
	
	
	public Message createRequest(String message){
		System.out.println("Inside createRequest : "+ message);
		final String transactionId = UUID.randomUUID().toString();
		final Message builtMessage = MessageBuilder.withBody(message.getBytes())
				.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
				.setCorrelationId(transactionId.getBytes())
				.setCorrelationIdString(transactionId)
				.setHeader(AmqpHeaders.CORRELATION_ID, transactionId)
				.build();
		return builtMessage;
	}
	
	
	public Message processRequest(Message message){
		System.out.println("Inside process Request : "+ new String(message.getBody()));
		System.out.println("Header values : "+message.getMessageProperties().getHeaders());
		System.out.println("Property values : "+message.getMessageProperties().getCorrelationIdString());
		final Message result = MessageBuilder.withBody("Successful".getBytes()).copyProperties(message.getMessageProperties())
								.copyHeaders(message.getMessageProperties().getHeaders()).build();
		System.out.println("Request Before sending back :"+result);
		return result;
	}

}
