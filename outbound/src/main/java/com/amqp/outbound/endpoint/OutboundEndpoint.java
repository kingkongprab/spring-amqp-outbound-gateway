package com.amqp.outbound.endpoint;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amqp.outbound.gateway.OutboundGateway;

@Controller
public class OutboundEndpoint {

	@Autowired
	private OutboundGateway gateway;

	@RequestMapping(value = {"/test"}, method = RequestMethod.POST, produces = "application/text")
	@ResponseBody
	public Callable<ResponseEntity<String>> processAuthorization(@RequestBody final String request){
		return ()-> {
			String result = gateway.process(request);
			System.out.println("Request/reply result: " + result);
			return ResponseEntity.ok(result);
		};
	}

}
