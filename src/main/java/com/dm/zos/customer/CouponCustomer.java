package com.dm.zos.customer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCustomer implements ChannelAwareMessageListener{
    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String bm_message = new String(message.getBody(), "UTF-8");
            logger.info("MQ监听消息：" + bm_message);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
        }
    }

}
