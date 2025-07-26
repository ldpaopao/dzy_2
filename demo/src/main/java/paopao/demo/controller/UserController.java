
package paopao.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import paopao.demo.dto.UserPrizeDTO;
import paopao.demo.entity.Prize;
import paopao.demo.entity.User;
import paopao.demo.mapper.UserPrizeMapper;
import paopao.demo.security.JwtUtils;
import paopao.demo.service.LotteryService;
import paopao.demo.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LotteryService lotteryService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("用户名已存在");
        }
        userService.register(user);
        return ResponseEntity.ok("注册成功");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        log.info("登录请求：username={}, password={}", user.getUsername(), user.getPassword());
        User dbUser = userService.findByUsername(user.getUsername());
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {

            Map<String,Object> userInfo = new HashMap<>();
            userInfo.put("id", dbUser.getId());
            userInfo.put("name", dbUser.getUsername());
            String token = JwtUtils.generateToken(userInfo);

            System.out.println(token);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("用户名或密码错误");
    }

    @PostMapping("/lottery")
    public ResponseEntity<?> lottery(@RequestHeader("Token") String token) {
        System.out.println("=== 后端抽奖接口调试 ===");
        System.out.println("收到抽奖请求，Token: " + token);

        Integer id = JwtUtils.parseToken(token).get("id", Integer.class);
        System.out.println("解析出的用户ID: " + id);

        if (id == null) {
            System.out.println("用户ID为空，返回401");
            return ResponseEntity.status(401).build();
        }

        Prize prize = lotteryService.draw(id);
        System.out.println("抽奖服务返回的奖品: " + (prize != null ? prize.getName() : "null"));

        if (prize != null) {
            String prizeName = prize.getName();
            System.out.println("返回奖品名称: " + prizeName);
            System.out.println("奖品名称类型: " + prizeName.getClass().getName());
            System.out.println("奖品名称长度: " + prizeName.length());
            System.out.println("=== 调试结束 ===");
            return ResponseEntity.ok(prizeName); // 显示中奖奖品名称
        } else {
            System.out.println("返回奖品库存不足");
            System.out.println("=== 调试结束 ===");
            return ResponseEntity.ok("奖品库存不足"); // 没有奖品时显示库存不足
        }
    }

    @Autowired
    private UserPrizeMapper userPrizeMapper;
}