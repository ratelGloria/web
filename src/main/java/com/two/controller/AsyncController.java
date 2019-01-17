/*
package com.two.controller;

import com.two.dao.UserInfoMapper;
import com.two.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AsyncController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ServerResponse serverResponse;


    @Async
    public ServerResponse checkUserName(String username){

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(username);

        int i = userInfoMapper.selectUserName(userInfo);

        if(i != 0){
            serverResponse=ServerResponse.createServletResponse(ResponseCode.ERROR, "用户名已存在", null);
            System.out.println("-----------"+serverResponse);
            return serverResponse;
        }else if(i == 0){
            */
/*serverResponse=ServerResponse.createServletResponse(ResponseCode.SUCCESS,"用户名可以使用",null);
            System.out.println("-----------"+serverResponse);
            return serverResponse;*//*

            return ServerResponse.createServletResponse(ResponseCode.SUCCESS,"用户名可以使用",null);
        }

      */
/*  model.addAttribute("serverResponse",serverResponse);*//*


        */
/*  return "frontpage/logUp";*//*

        return null;
    }

}
*/
