package com.bonc.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description: 根据code查询对应有权限的景区
 * @Date:Created in 13:32 2018/8/29
 * @Modified By:
 */
public interface ValidMapper {

    List<String> getTourismId(@Param("code") String code);
}
