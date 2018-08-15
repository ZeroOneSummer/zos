package com.dm.zos.controller.produce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produce")
public class ProduceController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RabbitTemplate template;

	@RequestMapping("/send")
	public void send()  {
		logger.info("开始发送消息到MQ中");
		for (int i = 0; i < 5; i++){
			template.convertAndSend("Fanoutqueue:couponQueue", "我是一条MQ消息");
		}
		logger.info("发送消息到MQ结束");
	}


}
