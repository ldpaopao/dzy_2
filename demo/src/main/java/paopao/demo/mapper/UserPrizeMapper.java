package paopao.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import paopao.demo.dto.UserPrizeDTO;
import paopao.demo.res.UsPrRes;

import java.util.List;
@Mapper
public interface UserPrizeMapper {
    void insert(@Param("userId") Integer userId, @Param("prizeId") Integer prizeId);
    List<UserPrizeDTO> findByUserId(@Param("userId") Integer userId);

    List<UsPrRes> findPrizeListByUserId(@Param("id")int id);
}