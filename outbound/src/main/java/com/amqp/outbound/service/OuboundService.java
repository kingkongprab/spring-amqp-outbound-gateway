package com.amqp.outbound.service;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


@Service
public final class OuboundService {


	public String createRequest(String message){
		System.out.println("Inside createRequest : "+ message);
		return message;
	}


	public String processRequest(Message message){
		System.out.println("Inside process Request : " + message);
		return message.getPayload().toString().toUpperCase();
	}

}
