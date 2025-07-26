package paopao.demo;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class DemoApplicationTests {
    @Test
    public  void test() {
        // 生成安全的 HS256 密钥
        String secret = Keys.secretKeyFor(SignatureAlgorithm.HS256).toString();
        // 输出密钥
        System.out.println(secret);
    }

}
