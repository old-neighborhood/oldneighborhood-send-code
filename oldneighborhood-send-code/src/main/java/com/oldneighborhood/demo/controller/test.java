package com.oldneighborhood.demo.controller;

import java.util.HashMap;
import java.util.Map;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SendCodeController sendCodeController = new SendCodeController();
		Map<String,Object> requestMap = new HashMap<>();
		String phoneNumber = "18260099137";
		requestMap.put("phoneNumber", phoneNumber);
		Map<String,Object> resultMap=sendCodeController.sendMsg(requestMap);
		System.out.println(resultMap);
		String msgNum = resultMap.get("randomNum").toString();
		resultMap.put("msgNum", msgNum);
		int bo = sendCodeController.validateNum(resultMap);
		System.out.println(bo);
	}

}
