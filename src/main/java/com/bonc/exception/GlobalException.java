package com.bonc.exception;

import com.bonc.response.IResponseEnum;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 15:23 2018/8/30
 * @Modified By:
 */
public class GlobalException extends RuntimeException {

    private IResponseEnum responseEnum;

    private Object data;

    /**
     *@Author:ChenZhiXiang
     *@Description: 抛出异常
     *@Date: 15:28 2018/8/30
     */
    public GlobalException(IResponseEnum responseEnum){
        this.responseEnum = responseEnum;
    }

    /**
     *@Author:ChenZhiXiang
     *@Description: 带数据的异常
     *@Date: 15:29 2018/8/30
     */
    public GlobalException(IResponseEnum responseEnum,Object data){
        this.responseEnum = responseEnum;
        this.data = data;
    }

    public GlobalException(IResponseEnum responseEnum,Throwable cause){
        super(cause);
        this.responseEnum = responseEnum;
    }

    public GlobalException(IResponseEnum responseEnum,Object data,Throwable cause){
        super(cause);
        this.data = data;
        this.responseEnum = responseEnum;
    }

    public IResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(IResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getMessage() {
        return responseEnum == null ? "" : responseEnum.msg();
    }
}
