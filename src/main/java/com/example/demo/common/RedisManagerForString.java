package com.example.demo.common;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RedisManagerForString {
	@Autowired
	private RedisTemplate<String, Object> redisTemplateForString;
	
	public static final String USERTOKEN_CONTEXT = "Token2UserId";
	
	public static final String ACT_ROUTE_CONTEXT = "ActUserId2Url";
	
	public static final String ENT_ROUTE_CONTEXT = "EntUserId2Url";
	
	@Async
	public void setContext(String context,String key,Object object){
		redisTemplateForString.opsForHash().put(context, key, object);
	}
	
	public Object getContext(String context,String key){
		if(redisTemplateForString.hasKey(context))
		return redisTemplateForString.opsForHash().get(context, key);
		else return null;
	}
	public void deleteContext(String context,String key){
		if(redisTemplateForString.hasKey(context))
		redisTemplateForString.opsForHash().delete(context,key);
	}
	public void deleteContext(String context,Object... key){
		if(redisTemplateForString.hasKey(context))
		redisTemplateForString.opsForHash().delete(context,key);
	}
	
	public void deleteAllContext(String context){
		if(redisTemplateForString.hasKey(context))
		redisTemplateForString.opsForHash().delete(context);
	}
	
	public void increment(String key,Object value,int expired){
		redisTemplateForString.opsForValue().increment(key, (long)value);
		redisTemplateForString.expire(key, expired,TimeUnit.MINUTES);
	}
	public void incrementHours(String key,Object value,int expired){
		redisTemplateForString.opsForValue().increment(key, (long)value);
		redisTemplateForString.expire(key, expired,TimeUnit.HOURS);
	}
	@Async
	public void setString(String key,String value,int expired){
		redisTemplateForString.opsForValue().set(key, value, expired, TimeUnit.MINUTES);
	}
	
	public Object getString(String key){
		return redisTemplateForString.opsForValue().get(key);
	}
	public void removeString(String key){
		if(redisTemplateForString.hasKey(key))redisTemplateForString.delete(key);
	}

	public Map<Object,Object> getContextAll(String context) {
		if(redisTemplateForString.hasKey(context))
		return redisTemplateForString.opsForHash().entries(context);
		else return null;
	}

	public void setInpiple(final String context,final Map<String,String> addAndEdit) {
		try{  
            final RedisSerializer<String> serializer = redisTemplateForString.getStringSerializer();   
            redisTemplateForString.executePipelined(new RedisCallback<String>() {    
                @Override    
                public String doInRedis(RedisConnection conn)    
                        throws DataAccessException {    
                        for(Entry entry : addAndEdit.entrySet()) {
                            Object key = entry.getKey();
                            Object value = entry.getValue();
                            conn.hSet(context.getBytes(), key.toString().getBytes(), value.toString().getBytes());
                            // do what you have to do here
                            // In your case, an other loop.
                        }
                    return null;    
                }  
            }, serializer);  
        }catch(Exception e){  
            System.out.println("使用管道操作出错:"+e.getMessage());  
        }  
	}

//	public void deleteInpiple(final String context,final Map<String, String> delete) {
//		try{  
//            final RedisSerializer<String> serializer = redisTemplateForString.getStringSerializer();   
//            redisTemplateForString.executePipelined(new RedisCallback<String>() {    
//                @Override    
//                public String doInRedis(RedisConnection conn)    
//                        throws DataAccessException {
//                        conn.hDel(context, delete.keySet().toArray(new String[delete.keySet().size()]));
//                    return null;    
//                }  
//            }, serializer);  
//        }catch(Exception e){  
//            System.out.println("使用管道操作出错:"+e.getMessage());  
//        }  
//	}
}
