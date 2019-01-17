/*
package com.two.controller;

import com.two.dao.UserInfoMapper;
import com.two.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.TimeZone;

@Controller

public class UserController {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    public ServerResponse serverResponse;

    @Autowired
    private AsyncController asyncController;


    @RequestMapping("/logInServlet")
    public String logIn(Model model,HttpServletRequest request, HttpServletResponse response){

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(userName);

        UserInfo userInfoResult = userInfoMapper.selectUserNamePassword(userInfo);

        if(userInfoResult.getUsername().equals(userName)  && userInfoResult.getPassword().equals(password)){

            serverResponse=ServerResponse.createServletResponse(ResponseCode.SUCCESS,null,userInfoResult);
        }else {

            serverResponse=ServerResponse.createServletResponse(ResponseCode.ERROR,"用户名和密码错误",null);
            model.addAttribute("servletResponse",serverResponse);
            return "frontpage/logIn";
        }

      model.addAttribute("servletResponse",serverResponse);

        return "frontpage/front_index";

    }

    @RequestMapping("/logUpUser")
    public String logUp(HttpServletRequest request,HttpServletResponse response){
        //中文乱码
        response.setContentType("text/html;charset=utf-8");
        //响应乱码
        response.setCharacterEncoding("utf-8");

        String username = request.getParameter("logUp_userName");
        String pwd = request.getParameter("logUp_pwd");
        String email = request.getParameter("logUp_email");
        String phone = request.getParameter("logUp_phone");
        String questionSelect = request.getParameter("questionSelect");
        String answer = request.getParameter("answer");
        String role_str = request.getParameter("role");

        System.out.println("pppppppp"+questionSelect);

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

        int dateD = c.get(Calendar.DATE);
        int dateM =  c.get(Calendar.MONTH)+1;
        int dateY =  c.get(Calendar.YEAR);

        java.sql.Date date = java.sql.Date.valueOf(dateY+"-"+dateM+"-"+dateD);
        System.out.println("date"+date);
        int role = Integer.parseInt(role_str);

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(pwd);
        userInfo.setEmail(email);
        userInfo.setPhone(phone);
        userInfo.setQuestion(questionSelect);
        userInfo.setAnswer(answer);
        userInfo.setRole(role);
        userInfo.setCreateTime(date);
        userInfo.setUpdateTime(date);

        int insert = userInfoMapper.insert(userInfo);


       return "frontpage/logUpOk";

    }

    @RequestMapping("/checkUserName/{username}")
    @ResponseBody

    public ServerResponse checkUserName(@PathVariable String username,HttpServletResponse response) throws IOException {


     */
/*   ServerResponse serverResponse = asyncController.checkUserName(username);
        System.out.println("111111"+serverResponse);

        return serverResponse;*//*

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

    @RequestMapping("/forgetPwdController")
    public String forgetPwdController(HttpServletRequest request,Model model,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("utf-8");

        String userName = request.getParameter("userName");

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        UserInfo userInfo1 = userInfoMapper.selectUserNameInfo(userInfo);

     */
/*   String question = userInfo1.getQuestion();*//*

      */
/*  System.out.println(">>>>>>>>"+question);*//*

        model.addAttribute("userInfo1",userInfo1);

        return "frontpage/forgetPwdQuestion";
    }

    @RequestMapping("/matchAnswer")
    public String matchAnswer(HttpServletRequest request,Model model,HttpServletResponse response){

        String userName = request.getParameter("userName");
        String answer = request.getParameter("answer");

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(userName);
        userInfo.setAnswer(answer);

        UserInfo userInfo1 = userInfoMapper.selectUserCondition(userInfo);
        System.out.println("]]]]]]]]]]]]"+userInfo1);
        if(userInfo1 == null){
            return "frontpage/forgetPwdQuestion";
        }else {
            return "";
        }

    }
    }

*/
