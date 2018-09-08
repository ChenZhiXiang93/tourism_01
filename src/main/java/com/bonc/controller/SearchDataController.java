
package com.bonc.controller;

import com.bonc.exception.GlobalException;
import com.bonc.redis.RedisService;
import com.bonc.response.CommResponseEnum;
import com.bonc.response.ResponseData;
import com.bonc.service.ValidService;
import com.bonc.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tourism/scenic")
public class SearchDataController {

	@Autowired
	private ValidService validService;

	@Autowired
	private RedisService redisService;

	public Boolean valid(String viewSpotStr,String token) {
		//获得用户对应的鉴权码
		String code = redisService.get(token);
		if (code == null){
			throw new GlobalException(CommResponseEnum.USER1);
		}
		//根据鉴权码去获取该鉴权码下对应的景区id
		List<String> resList = validService.getTourismId(code);
		//判断与传入的景区是否存在于该鉴权码下
		if (resList.size() <= 0 || resList == null) {
			return false;
		} else {
			if (resList.contains(viewSpotStr)) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 实时人数监控
	 *
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRealTimeData", produces = "text/html;charset=utf-8")
	public String getRealTimePeopleNum(@RequestParam(value = "viewSpotStr", required = false) String viewSpotStr,
									   @RequestParam(value = "startDate", required = false) String startDate,
									   @RequestParam(value = "endDate", required = false) String endDate,
									   @RequestParam(value = "token", required = false) String token) throws Exception {
		String res = "";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr+"&startDate="+startDate+"&endDate="+endDate;
			String url = "http://10.202.12.244:8080/tourism/scenic/getRealTimeData";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
	}

/**
	 * 当日人数趋势
	 *
	 * @throws Exception
	 */

	@RequestMapping(value = "/getNumByTime", produces = "text/html;charset=utf-8")
	public String getTodayPeopleNumTrend(@RequestParam(value = "viewSpotStr", required = false) String viewSpotStr,
										 @RequestParam(value = "startTime", required = false) String startTime,
										 @RequestParam(value = "endTime", required = false) String endTime,
										 @RequestParam(value = "token", required = false) String token) throws Exception {
		String res = "";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr+"&startTime="+startTime+"&endTime="+endTime;
			String url = "http://10.202.12.244:8080/tourism/scenic/getNumByTime";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
	}

	/**
     * 每日人数趋势
	 * @throws Exception 
     */

    @RequestMapping(value = "/getNumByDay",  produces = "text/html;charset=utf-8")
    public String getDaysPeopleNumTrend(@RequestParam(value="viewSpotStr",required=false)String viewSpotStr,
    		@RequestParam(value="startDate",required=false)String startDate,
    		@RequestParam(value="endDate",required=false)String endDate, @RequestParam(value = "token", required = false) String token) throws Exception {
    	String res ="";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr+"&startDate="+startDate+"&endDate="+endDate;
			String url = "http://10.202.12.244:8080/tourism/scenic/getNumByDay";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
    }
/**
     * 国内来源
	 * @throws Exception 
     */

    @RequestMapping(value = "/getNumByProvince",  produces = "text/html;charset=utf-8")
    public String getCountryOrigin(@RequestParam(value="viewSpotStr",required=false)String viewSpotStr,@RequestParam(value = "token", required = false) String token) throws Exception {
		String res = "";
		Boolean flag = valid(viewSpotStr,token);
		if (flag) {
			String param = "viewSpotStr=" + viewSpotStr;
			String url = "http://10.202.12.244:8080/tourism/scenic/getNumByProvince";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
	}
	/**
     * 省内来源
	 * @throws Exception 
     */

    @RequestMapping(value = "/getNumByCity",  produces = "text/html;charset=utf-8")
    public String getProvinceOrigin(@RequestParam(value="viewSpotStr",required=false)String viewSpotStr,
									@RequestParam(value = "token", required = false) String token) throws Exception {
    	String res ="";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr;
			String url = "http://10.202.12.244:8080/tourism/scenic/getNumByCity";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
    }
	/**
     * 性别分布
	 * @throws Exception 
     */

    @RequestMapping(value = "/getNumBySex",produces = "text/html;charset=utf-8")
    public String getSexDistribute(@RequestParam(value="viewSpotStr",required=false)String viewSpotStr,@RequestParam(value = "token", required = false) String token
	) throws Exception {
    	String res ="";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr;
			String url = "http://10.202.12.244:8080/tourism/scenic/getNumBySex";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
    }
	/**
     * 年龄分布
	 * @throws Exception 
     */

    @RequestMapping(value = "/getNumByAge",produces = "text/html;charset=utf-8")
    public String getAgeDistribute(@RequestParam(value="viewSpotStr",required=false)String viewSpotStr,
								   @RequestParam(value = "token", required = false) String token) throws Exception {
    	String res ="";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr;
			String url = "http://10.202.12.244:8080/tourism/scenic/getNumByAge";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
    }
	/**
     * 景区人数汇总数据
	 * @throws Exception 
     */

    @RequestMapping(value = "/getScenicTotalPeopleNum",produces = "text/html;charset=utf-8")
    public String getScenicTotalPeopleNum(@RequestParam(value="viewSpotStr",required=false)String viewSpotStr,
    		@RequestParam(value="timeType",required=false)String timeType,
    		@RequestParam(value="searchTime",required=false)String searchTime,
										  @RequestParam(value = "token", required = false) String token) throws Exception {
    	String res ="";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr+"timeType=" + timeType+"searchTime=" + searchTime;
			String url = " http://10.202.12.244:18443/tourism/scenic/getScenicTotalPeopleNum";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
    }
	/**
     * 景区游客停留时长
	 * @throws Exception 
     */

    @RequestMapping(value = "/getScenicPeopleStayTime",produces = "text/html;charset=utf-8")
    public String getScenicPeopleStayTime(@RequestParam(value="viewSpotStr",required=false)String viewSpotStr,
    		@RequestParam(value="timeType",required=false)String timeType,
    		@RequestParam(value="stayTime",required=false)String stayTime,
    		@RequestParam(value="searchTime",required=false)String searchTime,
										  @RequestParam(value = "token", required = false) String token) throws Exception {
    	String res ="";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr+"timeType=" + timeType+"searchTime=" + searchTime+"stayTime=" + stayTime;
			String url = "http://10.202.12.244:18443/tourism/scenic/getScenicPeopleStayTime";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
    }
	/**
     * 景区游客出行特征
	 * @throws Exception 
     */

    @RequestMapping(value = "/getScenicPeopleTravelCharacteristics",produces = "text/html;charset=utf-8")
    public String getScenicPeopleTravelCharacteristics(@RequestParam(value="viewSpotStr",required=false)String viewSpotStr,
    		@RequestParam(value="timeType",required=false)String timeType,
    		@RequestParam(value="travelType",required=false)String travelType,
    		@RequestParam(value="searchTime",required=false)String searchTime,
													   @RequestParam(value = "token", required = false) String token) throws Exception {
    	String res ="";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr+"timeType=" + timeType+"searchTime=" + searchTime+"travelType=" + travelType;
			String url = "http://10.202.12.244:18443/tourism/scenic/getScenicPeopleTravelCharacteristics";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
    }
	/**
     * 景群游客使用app统计
	 * @throws Exception 
     */

    @RequestMapping(value = "/getScenicPeopleUseAppData",produces = "text/html;charset=utf-8")
    public String getScenicPeopleUseAppData(@RequestParam(value="viewSpotStr",required=false)String viewSpotStr,
    		@RequestParam(value="timeType",required=false)String timeType,
    		@RequestParam(value="appType",required=false)String appType,
    		@RequestParam(value="searchTime",required=false)String searchTime,
											@RequestParam(value = "token", required = false) String token) throws Exception {
    	String res ="";
		Boolean flag = valid(viewSpotStr,token);
		if (flag){
			String param = "viewSpotStr=" + viewSpotStr+"timeType=" + timeType+"searchTime=" + searchTime+"appType=" + appType;
			String url = "http://10.202.12.244:18443/tourism/scenic/getScenicPeopleUseAppData";
			res = HttpUtil.doPost(url, param);
			return res;
		}
		return ResponseData.failureResponse(CommResponseEnum.USER2);
    }
}