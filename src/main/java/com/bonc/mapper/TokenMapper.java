package com.bonc.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 11:04 2018/9/6
 * @Modified By:
 */

public interface TokenMapper {

    Object getToken(@Param("username") String username, @Param("password") String password);

}
