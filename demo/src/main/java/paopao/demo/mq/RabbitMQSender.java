package paopao.demo.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import paopao.demo.config.RabbitConfig;
import paopao.demo.entity.LotteryResult;

@Component
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(LotteryResult result) {
        System.out.println("准备发送消息: " + result); // 添加这行
        rabbitTemplate.convertAndSend(RabbitConfig.LOTTERY_EXCHANGE, 
                                RabbitConfig.LOTTERY_ROUTING_KEY, 
                                result);
    }
}