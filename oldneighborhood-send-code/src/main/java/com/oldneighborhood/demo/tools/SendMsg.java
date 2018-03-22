package com.oldneighborhood.demo.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SendMsg {
	public static void sendMsg(String phones,String randomNum) {  
		String content = "【签名】尊敬的用户，您的验证码为" + randomNum + "，请在10分钟内输入。请勿告诉其他人!";
        try {  
        	
            content = java.net.URLEncoder.encode(content,"utf-8");  
            System.out.println(content);  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    //短信接口URL提交地址  
    String url = "https://api.dingdongcloud.com/v1/sms/sendyzm?apikey=b46c4961aa875f626b7924aace0d53f7&mobile="+phones+"&content="+content;  

    Map<String, String> params = new HashMap<String, String>();  

    params.put("zh", "账号");  
    params.put("mm", "密码");  
    params.put("dxlbid", "短信类别编号");  
    params.put("extno", "扩展编号");  

    //手机号码，多个号码使用英文逗号进行分割  
    params.put("hm", phones);  
    //将短信内容进行URLEncoder编码  
    params.put("nr", URLEncoder.encode(content));  

    String str = HttpRequestUtil.getRequest(url, params);  
} 
}
