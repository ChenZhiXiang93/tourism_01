package com.bonc.service.impl;

import com.bonc.mapper.TokenMapper;
import com.bonc.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 11:11 2018/9/6
 * @Modified By:
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public Object getToken(String username, String password) {
        return tokenMapper.getToken(username,password);
    }
}
