package com.service.person;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *Configuration for Person service, mostly for RabbitMQ
 * @author Dorian Coqueron & Pierre Gaultier
 * @version 1.0
 */
@Configuration
@ComponentScan
@EntityScan({"com.service.person", "com.modele"})
public class PersonConfiguration {
	
	@Bean
	public ConnectionFactory connectionFactory() {
		    CachingConnectionFactory connectionFactory =
			        new CachingConnectionFactory(Constants.getINSTANCE().getRabbitmqserverAddr());
		    		connectionFactory.setUsername(Constants.getINSTANCE().getRabbitmqUsername());
		    		connectionFactory.setPassword(Constants.getINSTANCE().getRabbitmqPassword());
			    return connectionFactory;
	}
		

	@Bean
	public DirectExchange direct() {
		return new DirectExchange("exc.person");
	}

	@Bean
	public PersonServer server() {
		return new PersonServer();
	}

	@Bean
	public Queue getPersonQueueById() {
		return new AnonymousQueue();
	}
	
	@Bean
	public Queue getPersonQueueByEmail() {
		return new AnonymousQueue();
	}
	
	@Bean
	public Queue getAllPersonQueue() {
		return new AnonymousQueue();
	}
	
	@Bean
	public Queue addPersonQueue() {
		return new AnonymousQueue();
	}

	@Bean
	public Binding binding1(DirectExchange direct, Queue getPersonQueueById) {
		return BindingBuilder.bind(getPersonQueueById).to(direct).with("getPersonById");
	}
	
	@Bean
	public Binding binding2(DirectExchange direct, Queue getPersonQueueByEmail) {
		return BindingBuilder.bind(getPersonQueueByEmail).to(direct).with("getPersonByEmail");
	}
	
	@Bean
	public Binding binding3(DirectExchange direct, Queue getAllPersonQueue) {
		return BindingBuilder.bind(getAllPersonQueue).to(direct).with("getAllPerson");
	}
	
	@Bean
	public Binding binding4(DirectExchange direct, Queue addPersonQueue) {
		return BindingBuilder.bind(addPersonQueue).to(direct).with("addPerson");
	}
}
