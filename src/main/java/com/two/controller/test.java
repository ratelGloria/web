package com.two.controller;

import com.two.dao.UserInfoMapper;
import com.two.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class test {

    @Autowired
    UserInfoMapper userInfoMapper;

    @RequestMapping("/upup")
    public String ok1(){
        /*  Class*/

        return "frontpage/test";
    }

    @RequestMapping("/hello")
    public ServerResponse test(){

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        UserInfo userInfo1 = userInfoMapper.selectUserCondition(userInfo);

        System.out.println(">>>>>>>>>>>>."+userInfo1.getCreateTime());


        return ServerResponse.serverResponseBySuccess();
    }

    @RequestMapping("/user")
    public ServerResponse test1(){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
        return ServerResponse.createServletResponse(ResponseCode.SUCCESS,null,userInfo);
    }

    @RequestMapping("/ok")
    public String ok(){
      /*  Class*/

        return "frontpage/logUpOk";
    }

}
