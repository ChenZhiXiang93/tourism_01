package com.bonc.controller;

import com.bonc.pojo.User;
import com.bonc.redis.RedisService;
import com.bonc.response.ResponseData;
import com.bonc.service.TokenService;
import com.bonc.util.Expires;
import com.bonc.util.TokenProccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 11:12 2018/9/6
 * @Modified By:
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/getToken")
    public Object getToken(@RequestParam("username")String username,@RequestParam("password")String password) throws Exception {
        User user = (User) tokenService.getToken(username,password);
        String token = "";
        if (user != null){
            token = TokenProccessor.makeToken();
            redisService.set(token,user.getCode(),Expires.TWOHOUR.getValue());
            return ResponseData.turnResponse(token);
        }
        return ResponseData.FAILURE;
    }
}
