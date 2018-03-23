package com.oldneighborhood.demo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.oldneighborhood.demo.controller.SendCodeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OldneighborhoodSendCodeApplicationTests {

	@Test
	public void contextLoads() {
		SendCodeController sendCodeController = new SendCodeController();
		Map<String,Object> requestMap = new HashMap<>();
		String phoneNumber = "18260099137";
		requestMap.put("phoneNumber", phoneNumber);
		System.out.println(sendCodeController.sendMsg(requestMap));
		
	}

}
