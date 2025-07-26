package paopao.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotteryResult implements Serializable {
    private Integer userId;
    private String prizeName;
    // constructor, getter, setter
}