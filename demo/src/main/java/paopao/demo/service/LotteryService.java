package paopao.demo.service;

import paopao.demo.entity.Prize;

public interface LotteryService {
    Prize draw(int userId);
}