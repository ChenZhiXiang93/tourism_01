package com.bonc.exception;

import com.bonc.response.IResponseEnum;
import com.bonc.response.ResponseData;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.BindException;

/**
 * @Author:ChenZhiXiang
 * @Description: 过滤所有异常，防止泄露系统和接口细节
 * @Date:Created in 15:38 2018/8/30
 * @Modified By:
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     *@Author:ChenZhiXiang
     *@Description: 处理不可控的异常，对调用方屏蔽错误细节
     *@Date: 15:40 2018/8/30
     */
    @ExceptionHandler(value = Throwable.class)
    public ResponseData defaultHandler(Throwable throwable){
        return ResponseData.FAILURE;
    }

    /**
     *@Author:ChenZhiXiang
     *@Description: 处理参数异常
     *@Date: 15:44 2018/8/30
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseData bindingResultHandler(BindException be, BindingResult result) throws BindException {
        throw be;
    }

    /**
     *@Author:ChenZhiXiang
     *@Description: 封装业务异常为返回信息
     *@Date: 15:49 2018/8/30
     */
    @ExceptionHandler(value = GlobalException.class)
    public ResponseData globalExceptionHandler(GlobalException g){
        IResponseEnum responseEnum = g.getResponseEnum();
        //如果没有异常码，则按照不可控异常处理
        if (responseEnum == null){
            return defaultHandler(g);
        }
        //自带堆栈，则打印
        if (g.getCause() != null){
            System.out.println(g.getMessage());
        }
        ResponseData responseData = new ResponseData(responseEnum,g.getData());
        System.out.println(responseData);
        /*return new ResponseData(responseEnum,g.getData());*/
        return responseData;
    }
}
