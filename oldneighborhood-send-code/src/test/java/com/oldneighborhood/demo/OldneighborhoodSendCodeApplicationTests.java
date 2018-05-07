package com.oldneighborhood.demo;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.oldneighborhood.demo.controller.SendCodeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OldneighborhoodSendCodeApplicationTests {
	
	@Autowired
	private SendCodeController sendCodeController;

	@Test
	public void contextLoads() {
		Map<String, Object> requestMap = new HashMap<>();
		String email = "ren_blue@foxmail.com";
		requestMap.put("email", email);
		System.out.println(sendCodeController.sendMsg(requestMap));
	}
	
//	@Test
//	public void validateCode() {
//		Map<String, Object> map = new HashMap<>();
//		map.put("time", "20180502192012");
//		map.put("code", "501810");
//		map.put("hash", "79528DC604AE9AF5C4EAEF2BCF127670");
//		System.out.println(sendCodeController.validateCode(map));
//	}
}
