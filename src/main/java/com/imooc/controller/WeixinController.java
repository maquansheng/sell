package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
/**
*@Author:ma
*@Data:21:52 2018/11/27
 * 手工获取openid测试
*/
public class WeixinController {
    @GetMapping("/author")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth的方法");
        log.info("code={}",code);

        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx5271c0fec72093db&secret=246690309fedaf15eb62ac16ce12bae9&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate=new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        log.info("result={}"+result);
    }
}
