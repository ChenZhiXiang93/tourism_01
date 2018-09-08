package com.bonc.service.impl;

import com.bonc.mapper.ValidMapper;
import com.bonc.service.ValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 13:34 2018/8/29
 * @Modified By:
 */
@Service
public class ValidServiceImpl implements ValidService {

    @Autowired
    private ValidMapper validMapper;

    @Override
    public List<String> getTourismId(String code) {
        return validMapper.getTourismId(code);
    }
}
