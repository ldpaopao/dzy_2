package paopao.demo.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paopao.demo.mapper.UserPrizeMapper;
import paopao.demo.res.UsPrRes;
import paopao.demo.security.JwtUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PrizeController {

    @Autowired
    UserPrizeMapper userPrizeMapper;

    @GetMapping("/getList")
    public List<UsPrRes> getList(@RequestHeader("Token") String token) {
        Integer id = JwtUtils.parseToken(token).get("id", Integer.class);
        if(id != 3){
            return null;
        }

        return userPrizeMapper.findPrizeListByUserId(1);
    }

}
