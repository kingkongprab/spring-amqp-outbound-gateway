package com.amqp.outbound.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OutboundConfiguration {


	@Bean
	public RabbitTemplate template(ConnectionFactory rabbitConnectionFactory){
		final RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
		template.setReplyAddress(replyQueue().getName());
		return template;
	}

	@Bean
	public MessageListenerContainer replyContainer(RabbitTemplate template) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(template.getConnectionFactory());
		container.setQueues(replyQueue());
		container.setMessageListener(template);
		return container;
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
		return BindingBuilder.bind(this.replyQueue()).to(this.exchangeReply()).with("reply_exchange_queue");
	}

	@Bean
	public DirectExchange exchangeReply(){
		return new DirectExchange("reply_exchange");
	}


	@Bean
	public Queue replyQueue(){
		return new Queue("reply_queue", true, false, true);
	}

}
