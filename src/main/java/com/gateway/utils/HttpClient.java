package com.gateway.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2019/1/25.
 */
@Service
public class HttpClient {

    public String client(String url, String httpMethod, MultiValueMap multiValueMap){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity;
        if(httpMethod.toLowerCase().equals("get")){
            responseEntity = restTemplate.getForEntity(url,String.class);
        }else{
            responseEntity = restTemplate.postForEntity(url,HttpMethod.POST,String.class);
        }

        return responseEntity.getBody();
    }
}
