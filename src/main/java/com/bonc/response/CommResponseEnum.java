package com.bonc.response;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 14:29 2018/8/30
 * @Modified By:
 */
public enum CommResponseEnum implements IResponseEnum {

    USER1(1001,"请获取令牌"),
    USER2(1002,"没有权限"),
    SUCCESS(200,"操作成功"),
    FAILURE(0,"未知错误");

    private int respCode;
    private String respMsg;

    CommResponseEnum(int respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    @Override
    public int code() {
        return this.respCode;
    }

    @Override
    public String msg() {
        return this.respMsg;
    }
}
