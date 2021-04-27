package com.example.oauth2github.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.oauth2github.config.Oauth2Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
public class LoginController {

    private final Oauth2Properties oauth2Properties;

    public LoginController(Oauth2Properties oauth2Properties) {
        this.oauth2Properties = oauth2Properties;
    }

    @GetMapping("/authorize")
    public String authorize() {
        String url = oauth2Properties.getAuthorizeUrl() +
                "?client_id=" + oauth2Properties.getClientId() +
                "&redirect_uri=" + oauth2Properties.getRedirectUrl();

        log.info("授权url:{}", url);
        return "redirect:" + url;
    }

    @GetMapping("/oauth2/callback")
    public String callback(@RequestParam("code") String code) {
        log.info("code={}", code);

        String accessToken = getAccessToken(code);
        String userInfo = getUserInfo(accessToken);
        log.info("userInfo={}", userInfo);

        log.info("重定向到home");
        return "redirect:/home";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "hello world";
    }


    private String getAccessToken(String code){
        String url = oauth2Properties.getAccessTokenUrl() +
                "?client_id=" + oauth2Properties.getClientId() +
                "&client_secret=" + oauth2Properties.getClientSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";
        log.info("getAccessToken url:{}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        String responseStr = response.getBody();
        log.info("responseStr={}", responseStr);

        JSONObject jsonObject = JSONObject.parseObject(responseStr);

        String accessToken = jsonObject.getString("access_token");
        log.info("accessToken={}", accessToken);

        return accessToken;

    }

    private String getUserInfo(String accessToken) {
        String url = oauth2Properties.getUserInfoUrl();
        log.info("getUserInfo url:{}", url);

        HttpHeaders requestHeaders = new HttpHeaders();
        // 指定响应返回json格式
        requestHeaders.add("accept", "application/json");
        requestHeaders.add("Authorization", "token " + accessToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String userInfo = response.getBody();

        return userInfo;
    }

}
