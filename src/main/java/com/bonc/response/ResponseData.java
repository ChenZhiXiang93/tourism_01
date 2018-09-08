package com.bonc.response;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 14:36 2018/8/30
 * @Modified By:
 */
public class ResponseData {

    private int respCode;

    private String respMsg;

    private Object data;


    public static final ResponseData SUCCESS = new ResponseData(CommResponseEnum.SUCCESS);

    public static final ResponseData FAILURE = new ResponseData(CommResponseEnum.FAILURE);
    /**
     *@Author:ChenZhiXiang
     *@Description: 异常码反馈
     *@Date: 14:56 2018/8/30
     *@Param responseEnum
     */
    public ResponseData(IResponseEnum responseEnum) {
        this.respCode = responseEnum.code();
        this.respMsg = responseEnum.msg();
    }

    /**
     *@Author:ChenZhiXiang
     *@Description: 异常还需要数据的情况
     *@Date: 14:57 2018/8/30
     *@Param  responseEnum   data
     */
    public ResponseData(IResponseEnum responseEnum,Object data) {
        this.respCode = responseEnum.code();
        this.respMsg = responseEnum.msg();
        this.data = data;
    }

    /**
     *@Author:ChenZhiXiang
     *@Description: 成功消息
     *@Date: 15:00 2018/8/30
     *@Param: data 请求成功返回的数据
     */
    public static String turnResponse(Object data) throws Exception {
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("respCode",200);
        resMap.put("msg","操作成功");
        resMap.put("data",data);
        return new ObjectMapper().writeValueAsString(resMap);
    }

    /**
     *@Author:ChenZhiXiang
     *@Description: 不包含的成功消息
     *@Date: 15:10 2018/8/30
     */
    public static String OKResponse() throws Exception {
        Map<String,Object> respMap = new HashMap<>();
        respMap.put("respCode",200);
        respMap.put("respMsg","操作成功");
        return new ObjectMapper().writeValueAsString(respMap);
    }

    /**
     *@Author:ChenZhiXiang
     *@Description: 失败信息，但事物不回滚
     *@Date: 15:15 2018/8/30
     */
    public static String failureResponse(CommResponseEnum responseEnum) throws Exception {
        Map<String,Object> respMap = new HashMap<>();
        respMap.put("respCode",responseEnum.code());
        respMap.put("respMsg",responseEnum.msg());
        return new ObjectMapper().writeValueAsString(respMap);
    }

    /**
     *@Author:ChenZhiXiang
     *@Description: get  set方法
     *@Date: 15:20 2018/8/30
     */

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
