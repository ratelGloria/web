package com.two.controller.portal;

import com.two.pojo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Page {


   @RequestMapping("/AfterIndex")
    public String indexPage(){

        return "after_index";
    }

    @RequestMapping("/logIn")
    public String logInPage(){
System.out.println("我被访问");
        return "frontpage/logIn";
    }

    @RequestMapping("/logUp")
    public String logUpPage(){

        return "frontpage/logUp";
    }

    @RequestMapping("/test")
    public String test(Model model){

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername("hhhhhhhhhhhh");
        model.addAttribute("name",userInfo);
        return "test";
    }

    @RequestMapping("/forgetPwd")
    public String forgetPwd(){

        return "frontpage/forgetPwd";
    }

}
