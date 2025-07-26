//package paopao.demo.mq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//import paopao.demo.entity.LotteryRecord;
//
//@Component
//       public class RabbitMQListener {
//          @RabbitListener(queues = "lottery.result.queue")
//          public void handleLotteryResult(LotteryRecord record) {
//               System.out.println("抽奖结果已发送: " + record);
//           }
