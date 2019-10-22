package com.xxl.sso.sample.controller;

import com.xxl.sso.core.conf.Conf;
import com.xxl.sso.core.entity.ReturnT;
import com.xxl.sso.core.user.XxlSsoUser;
import com.xxl.sso.core.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author xuxueli 2017-08-01 21:39:47
 */
@Controller
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {

        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        model.addAttribute("xxlUser", xxlUser);
        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public ReturnT<XxlSsoUser> json(Model model, HttpServletRequest request) {
        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        return new ReturnT(xxlUser);
    }

    @RequestMapping("/load-api-info-by-token")
    @ResponseBody
    public String loadApiInfoByToken(HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add(Conf.SSO_SESSIONID, CookieUtil.getValue(request,Conf.SSO_SESSIONID));
      ResponseEntity responseEntity= restTemplate.exchange("http://xxlssoclient2.com:8082/xxl-sso-token-sample-springboot/", HttpMethod.GET,new HttpEntity<Object>(headers),String.class);
      String str= responseEntity.getBody().toString();
      return str;
    }

    @RequestMapping("/load-api-no-authorization")
    @ResponseBody
    public ReturnT loadApiInfoNoAuthorization(HttpServletRequest request){
        if(request.getCookies().length == 0){
            return new ReturnT("无用户认证信息");
        }
        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        return new ReturnT(xxlUser);
    }

}