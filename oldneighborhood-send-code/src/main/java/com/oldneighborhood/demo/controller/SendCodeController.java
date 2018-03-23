package com.oldneighborhood.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldneighborhood.demo.tools.CommonUtils;
import com.oldneighborhood.demo.tools.MD5Utils;
import com.oldneighborhood.demo.tools.SendMsg;


@Controller
public class SendCodeController {
	
	private static final String KEY = "abc123";
	
	
//	传hashMap 里面有phonenumber；return一个resultMap 里面有五分钟后时间、MD5码
	@RequestMapping(value = "/sendMsg", method = RequestMethod.POST, headers = "Accept=application/json")
	public Map<String, Object> sendMsg(@RequestBody Map<String,Object> requestMap) {
	String phoneNumber = requestMap.get("phoneNumber").toString();
	String randomNum = CommonUtils.createRandomNum();// 生成随机数
	System.out.println(randomNum);
	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss ");
	Calendar c = Calendar.getInstance();
	c.add(Calendar.MINUTE, 5);
	String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期
//	SendMsg.sendMsg(phoneNumber,randomNum); //此处执行发送短信验证码方法
	String hash = MD5Utils.getMD5Code(KEY + "@" + currentTime + "@" + randomNum);//生成MD5值
	Map<String, Object> resultMap = new HashMap<>();
	resultMap.put("hash", hash);
	resultMap.put("tamp", currentTime);
//	resultMap.put("randomNum", randomNum);
	return resultMap; //将hash值和tamp时间返回给前端
}
	
//	传resultMap（界面put验证码“msgNum”）；return 0 成功 1 失败；2 超时
	@RequestMapping(value = "/validateNum", method = RequestMethod.POST, headers = "Accept=application/json")
	public int validateNum(@RequestBody Map<String,Object> requestMap) {
	String requestHash = requestMap.get("hash").toString();
	String tamp = requestMap.get("tamp").toString();
	String msgNum = requestMap.get("msgNum").toString();
	String hash = MD5Utils.getMD5Code(KEY + "@" + tamp + "@" + msgNum);
	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	Calendar c = Calendar.getInstance();
	String currentTime = sf.format(c.getTime());
	if (tamp.compareTo(currentTime) > 0) {
	if (hash.equalsIgnoreCase(requestHash)){
	//校验成功
		return 0;
	}else {
	//验证码不正确，校验失败
		return 1;
	}
	} else {
	// 超时
		return 2;
	}
	} 

}
