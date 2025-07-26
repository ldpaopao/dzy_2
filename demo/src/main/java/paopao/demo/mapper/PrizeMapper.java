package paopao.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import paopao.demo.entity.Prize;

import java.util.List;

@Mapper
public interface PrizeMapper {
    List<Prize> findAvailablePrizes();
    void decrementPrize(@Param("id") Integer id);
}
