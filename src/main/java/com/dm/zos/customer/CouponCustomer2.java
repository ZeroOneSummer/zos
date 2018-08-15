package com.dm.zos.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCustomer2 {
    Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "Fanoutqueue:couponQueue")
    public void getMessage(String message){
        logger.info("消息队列监听方式2：@RabbitListener注解");
        logger.info("消息队列监听方式2：{}", message);
    }

}
