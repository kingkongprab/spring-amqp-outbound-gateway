package com.amqp.outbound.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OutboundConfiguration {
	
	
	@Bean
	public RabbitTemplate template(ConnectionFactory rabbitConnectionFactory){
		final RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
		template.setQueue("reply_queue");
		template.setMessagePropertiesConverter(new DefaultMessagePropertiesConverter());
		return template;
	}
	
	
	
	@Bean
	public Binding binding(){
		return BindingBuilder.bind(this.queue()).to(this.exchange()).with("request_exchange_queue");
	}
	
	@Bean
	public DirectExchange exchange(){
		return new DirectExchange("request_exchange");
	}
	
	@Bean
	public Queue queue(){
		return new Queue("request_queue", true, false, true);
	}
	
	@Bean
	public Binding bindingReply(){
		return BindingBuilder.bind(this.queue()).to(this.exchange()).with("reply_exchange_queue");
	}
	
	@Bean
	public DirectExchange exchangeReply(){
		return new DirectExchange("reply_exchange");
	}

	
	@Bean
	public Queue replyQueue(){
		return new Queue("reply_queue", true, false, true);
	}


	/*@Bean
	 public SimpleMessageListenerContainer simpleListenerContainer(ConnectionFactory rabbitConnectionFactory){
		 final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitConnectionFactory);
		 container.setQueues(replyQueue());
		 container.setMessageListener(template(rabbitConnectionFactory));
		 return container;
	 }*/
}
