package com.donghyeon.rabbitmqexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PracticalTipSender {

    private static final Logger log = LoggerFactory.getLogger(PracticalTipSender.class);

    private final RabbitTemplate rabbitTemplate;

    public PracticalTipSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //이 메세지가 한개이상 보내지기 원하므로 @Scheduled 어노테이션을 달아준다.
    // 3초마다 메세지가 보내진다.
    //메인 클래스에는 @EnableScheduling 을 붙여준다.
    @Scheduled(fixedDelay = 3000L)
    public void sendPraticalTip() {
        PracticalTipMessage tip = new PracticalTipMessage("Always use immutable classes in java",1,false);
        rabbitTemplate.convertAndSend(RabbitmqExampleApplication.EXCHANGE_NAME,RabbitmqExampleApplication.ROUTING_KEY,tip);
        log.info("pratical Tip sent");

    }
}
