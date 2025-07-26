package paopao.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String LOTTERY_QUEUE = "lottery.queue";
    public static final String LOTTERY_EXCHANGE = "lottery.exchange";
    public static final String LOTTERY_ROUTING_KEY = "lottery.routing.key";

    @Bean
    public Queue lotteryQueue() {
        return new Queue(LOTTERY_QUEUE, true); // 持久化队列
    }

    @Bean
    public DirectExchange lotteryExchange() {
        return new DirectExchange(LOTTERY_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(LOTTERY_ROUTING_KEY);
    }
}
