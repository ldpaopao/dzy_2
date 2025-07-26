package paopao.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrize {
    private Integer id;
    private Integer userId;
    private Integer prizeId;
    private Timestamp wonTime;
    // getter & setter
}