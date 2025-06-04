package com.example.globaljava.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue usuarioQueue() {
        return QueueBuilder.durable("fila-usuario").build();
    }

    @Bean
    public Exchange usuarioExchange() {
        return ExchangeBuilder.directExchange("usuarioExchange").build();
    }

    @Bean
    public Binding usuarioBinding(Queue usuarioQueue, Exchange usuarioExchange) {
        return BindingBuilder.bind(usuarioQueue).to(usuarioExchange).with("routingKey").noargs();
    }

    @Bean
    public Queue funcionarioQueue() {
        return QueueBuilder.durable("fila-funcionario").build();
    }

    @Bean
    public Exchange funcionarioExchange() {
        return ExchangeBuilder.directExchange("funcionarioExchange").build();
    }

    @Bean
    public Binding funcionarioBinding(Queue funcionarioQueue, Exchange funcionarioExchange) {
        return BindingBuilder.bind(funcionarioQueue).to(funcionarioExchange).with("routingKey").noargs();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
