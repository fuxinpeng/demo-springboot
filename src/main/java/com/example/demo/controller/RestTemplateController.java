package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2019/8/3
 */
@Controller
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    public void getExchange(){
        String URL = "";
        URI targetUrl = UriComponentsBuilder.fromUriString(URL).path("").build().toUri();
        long time = System.currentTimeMillis();
        HttpHeaders headers = new HttpHeaders();
        headers.add("order-client-time", String.valueOf(time));
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,Object> map = new HashMap<String,Object>();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(map, headers);
        ResponseEntity<JSONObject> response = restTemplate.exchange(targetUrl, HttpMethod.GET, entity, JSONObject.class);
    }

    public void getObject(String url,JSONObject json){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(json.toString(), headers);
        String result = restTemplate.getForObject(url, String.class,formEntity);
    }

    public void getEntity(){
        URI targetUrl = UriComponentsBuilder.fromUriString("").path("").build().toUri();
        HttpHeaders headers = new HttpHeaders();
        //getForEntity默认就是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,Object> parameters = new LinkedMultiValueMap<String,Object>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(parameters, headers);
        //发送请求，设置请求返回数据格式为String
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(targetUrl.toString(), String.class,request);
    }

    public void postObject(){
        URI targetUrl = UriComponentsBuilder.fromUriString("").queryParam("userName", "")
                .queryParam("buyMsgNum", "").build().encode().toUri();
        //用postobject参数用MultiValueMap,不要用HashMap,并且参数默认是json格式
        MultiValueMap<String,Object> parameters = new LinkedMultiValueMap<String,Object>();
        parameters.add("action", "orderSync");
        parameters.add("shortName", "Kdzhj");
        parameters.add("iv", "");
        parameters.add("format", "");
        parameters.add("text", "");
        JSONObject jsonObject =  restTemplate.postForObject(targetUrl, parameters, JSONObject.class);
    }

    public JSONObject postForEntity(MultiValueMap<String, Object> paramMap,String url){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,headers);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, httpEntity, JSONObject.class);
        return response.getBody();
    }

}
