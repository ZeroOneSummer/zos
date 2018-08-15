package com.dm.zos.common.config;

import com.dm.zos.customer.CouponCustomer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerRabbitConfig {

    @Autowired
    CouponCustomer couponCustomer;

    /**
     * 创建交换机 如果生产者已经创建了，此处在创建没关系
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("exchange:bm:fanout:ws");
    }

    /**
     * 创建队列 券队列
     * @return
     */
    @Bean
    public Queue couponQueue() {
        return new Queue("Fanoutqueue:couponQueue");
    }


    /**
     * 将队列绑定到交换机
     * @param couponQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue couponQueue,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(couponQueue).to(fanoutExchange);
    }


    /**
     *  设置消息监听
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer couponCustomerLister(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(couponQueue());//监听队列名称
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(5);
        container.setConcurrentConsumers(1);
        //设置确认模式手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置监听
        container.setMessageListener(couponCustomer);
        //指定了我们接受消息的时候，以 JSON 传输的消息可以转换成对应的类型传入到方法中
        container.setMessageConverter(new Jackson2JsonMessageConverter());
        return container;
    }


}

