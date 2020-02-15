package com.example.demo.common;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//@Component
public class RedisManager {
	//@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public static final String ZWY_CONTEXT = "ZWYContext";
	
	public static final String TOKENCONTEXT = "TOKENContext";
	
	public static final String COMPANY_CONTEXT = "CompanyContext";
	
	public static final String WEIXIN_CONTEXT = "WEIXINContext";
	
	public static final String DEFALUT_COMPANY = "DefaultCompany";
	
	public static final String LOGIN_CAPTCHA = "loginCaptcha:";
	
	public static final String LOGIN_QR = "loginQR:";
	
	public static final String AUTH_WRONG_TIME ="authWrongTime:";
	
	public static final String VCODE_IP_SEND_TIME ="vcodeIpSendTime:";
	
	public static final String COMP_VERIFY_VISIT_TIME = "compVerifyIpVisitTime:";
	
	public static final String ACCOUNTCOMPANY_APPLY_VCODE="accountCompanyVcode:";
	
	public static final String JOIN_COMPANY_VCODE="joinCompanyVcode:";
	
	public static final String ACCOUNTCOMPANY_APPLY_CAPTCHA = "accountCompanyCaptcha:";
	
	public static final String FUNCOPERINFO = "funcoperInfo:";
	
	public void setContext(String context,String key,Object object){
		redisTemplate.opsForHash().put(context, key, object);
	}
	
	public Object getContext(String context,String key){
		if(redisTemplate.hasKey(context))
		return redisTemplate.opsForHash().get(context, key);
		else return null;
	}
	public void deleteContext(String context,String key){
		if(redisTemplate.hasKey(context))
		redisTemplate.opsForHash().delete(context,key);
	}
	public void deleteAllContext(String context){
		if(redisTemplate.hasKey(context))
		redisTemplate.opsForHash().delete(context);
	}
	
	public void increment(String key,Object value,int expired){
		redisTemplate.opsForValue().increment(key, (long)value);
		redisTemplate.expire(key, expired,TimeUnit.MINUTES);
	}
	public void incrementHours(String key,Object value,int expired){
		redisTemplate.opsForValue().increment(key, (long)value);
		redisTemplate.expire(key, expired,TimeUnit.HOURS);
	}
	public void setString(String key,String value,int expired){
		redisTemplate.opsForValue().set(key, value, expired, TimeUnit.MINUTES);
	}
	public Object getString(String key){
		return redisTemplate.opsForValue().get(key);
	}
	public void removeString(String key){
		if(redisTemplate.hasKey(key))redisTemplate.delete(key);
	}
	
	public boolean setStringNX(String key,String value,int expired){
		boolean b = redisTemplate.opsForValue().setIfAbsent(key, value);
		if(b)redisTemplate.expire(key, expired, TimeUnit.MILLISECONDS);
		return b;
	}
	
	/**
	 * 获取锁
	 * @param key 
	 * @param expired 超时时间 毫秒
	 * @return
	 * @throws InterruptedException 
	 */
	public void getLock(String key,int expired) throws InterruptedException{
		 boolean b = false;
		 while (!b) {
			 b = setStringNX(key,String.valueOf(new Date().getTime()),expired);
			 if(b) return;
			 else {
				 Thread.sleep(100);
			 }
		 }
	}
	
	/**
	 * 删除锁
	 * @param key
	 */
	public void removeLock(String key){
		removeString(key);
	}
}
