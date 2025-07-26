package paopao.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prize {
    private Integer id;
    private String name;
    private Integer quantity;
    // getter & setter
}
