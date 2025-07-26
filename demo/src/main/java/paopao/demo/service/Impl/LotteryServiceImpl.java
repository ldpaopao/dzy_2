
package paopao.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paopao.demo.entity.LotteryResult;
import paopao.demo.entity.Prize;
import paopao.demo.mapper.PrizeMapper;
import paopao.demo.mapper.UserPrizeMapper;
import paopao.demo.mq.RabbitMQSender;
import paopao.demo.service.LotteryService;

import java.util.List;
import java.util.Random;

@Service
public class LotteryServiceImpl implements LotteryService {
    @Autowired
    private PrizeMapper prizeMapper;
    @Autowired
    private UserPrizeMapper userPrizeMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public Prize draw(int userId) {
        System.out.println("=== 抽奖调试信息 ===");
        System.out.println("用户ID: " + userId);

        List<Prize> availablePrizes = prizeMapper.findAvailablePrizes();
        System.out.println("可用奖品数量: " + availablePrizes.size());

        // 打印所有可用奖品
        for (Prize prize : availablePrizes) {
            System.out.println("奖品: " + prize.getName() + ", 库存: " + prize.getQuantity());
        }

        if (availablePrizes.isEmpty()) {
            System.out.println("没有可用奖品，返回null");
            System.out.println("==================");
            return null;
        }

        // 随机选择一个真实奖品
        int randomIndex = new Random().nextInt(availablePrizes.size());
        Prize prize = availablePrizes.get(randomIndex);
        System.out.println("随机选择奖品索引: " + randomIndex);
        System.out.println("选中奖品: " + prize.getName());

        if (prize.getQuantity() > 0) {
            System.out.println("奖品库存充足，执行抽奖");
            prizeMapper.decrementPrize(prize.getId());
            userPrizeMapper.insert(userId, prize.getId());

            // 发送抽奖结果到RabbitMQ
            rabbitMQSender.send(new LotteryResult(userId, prize.getName()));
            System.out.println("抽奖成功，返回奖品: " + prize.getName());
            System.out.println("==================");
            return prize;
        } else {
            System.out.println("奖品库存不足，返回null");
            System.out.println("==================");
            return null;
        }
    }
}