package com.two.controller.portal;

import com.two.common.Const;
/*import com.two.controller.AsyncController;*/
import com.two.controller.ResponseCode;
import com.two.controller.ServerResponse;
import com.two.dao.UserInfoMapper;
import com.two.pojo.UserInfo;
import com.two.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@RestController
@RequestMapping("/user")
public class UserController1 {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    public ServerResponse serverResponse;

  /*  @Autowired
    private AsyncController asyncController;*/

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping("/logInServlet")
    public ServerResponse logIn(String userName, String password, HttpSession session){

        ServerResponse serverResponse = userInfoService.logIn(userName, password);
System.out.println("----------------"+serverResponse);
        if(serverResponse.getStatus() == 0){
            UserInfo userInfo = (UserInfo)serverResponse.getData();

            session.setAttribute(Const.CURRENTUSER,userInfo);
            System.out.println("我真的set了"+userInfo);
        }
        return serverResponse;

    }

    @RequestMapping("/logUpUser")
    public ServerResponse logUp(String logUp_userName,String logUp_pwd,String logUp_email,String logUp_phone,String questionSelect,String answer,String role){

        return serverResponse = userInfoService.logUp(logUp_userName, logUp_pwd, logUp_email, logUp_phone, questionSelect, answer, role);


    }

    @RequestMapping("/checkUserName/{username}")
    @ResponseBody

    public ServerResponse checkUserName(@PathVariable String username) throws IOException {

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(username);

        int i = userInfoMapper.selectUserName(userInfo);


        if(i != 0){
            serverResponse=ServerResponse.createServletResponse(ResponseCode.ERROR, "用户名已存在", null);
            System.out.println("-----------"+serverResponse);
            return serverResponse;
        }else if(i == 0){
            /*serverResponse=ServerResponse.createServletResponse(ResponseCode.SUCCESS,"用户名可以使用",null);
            System.out.println("-----------"+serverResponse);
            return serverResponse;*/
            return ServerResponse.createServletResponse(ResponseCode.SUCCESS,"用户名可以使用",null);
        }

        /*  model.addAttribute("serverResponse",serverResponse);*/

        /*  return "frontpage/logUp";*/
        return null;

    }

    @RequestMapping("/forgetPwdController")
    public ServerResponse forgetPwdController(String userName) throws UnsupportedEncodingException {

        return serverResponse = userInfoService.forgetPwdController(userName);

    }

    @RequestMapping("/matchAnswer")
    public ServerResponse matchAnswer(String userName,String answer){

        return serverResponse = userInfoService.matchAnswer(userName, answer);

    }
    @RequestMapping("/changePwd")
    public ServerResponse changePwd(String userName,String pwdNew,String forgetToken){

        return serverResponse = userInfoService.changePwd(userName, pwdNew,forgetToken);
    }

    @RequestMapping("/checkUserNameOrEmail")
    public ServerResponse checkUserNameOrEmail(String str,String type){

        return serverResponse = userInfoService.checkUserNameOrEmail(str, type);
    }

    @RequestMapping("/getUserInfo")
    public ServerResponse getUserInfo(HttpSession session){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        System.out.println("::::::::::::"+userInfo);

        if(userInfo == null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        ServerResponse userInfo1 = userInfoService.getUserInfo(userInfo.getId());

        return userInfo1;
    }

    @RequestMapping("/logInChangePwd")
    public ServerResponse logInChangePwd(String userName,String passwordOld,String passwordNew){

        return serverResponse = userInfoService.logInChangePwd(userName, passwordOld, passwordNew);

    }
    @RequestMapping("/updateUserInfo")
    public ServerResponse updateUserInfo(HttpSession session,UserInfo user,String email,String phone,String question,String answer){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

        if(userInfo == null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        user.setId(userInfo.getId());
        user.setEmail(email);
        user.setPhone(phone);
        user.setQuestion(question);
        user.setAnswer(answer);
        return serverResponse = userInfoService.updateUserInfo(user);

    }

    @RequestMapping("/getParticularUserInfo")
    public ServerResponse getParticularUserInfo(HttpSession session){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        System.out.println("qqqqqqqqqqqqqqqq"+session.getAttribute(Const.CURRENTUSER));
        System.out.println("zzzzzzzzzzzzzzzzzzzz"+userInfo);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }


      /*  ServerResponse userInfo = userInfoService.getUserInfo(session);

        UserInfo userInfo1 = (UserInfo)userInfo.getData();
        userInfo1.setPassword("");*/

        return ServerResponse.serverResponseBySuccess(userInfoService.getUserInfo(userInfo.getId()));
    }

    @RequestMapping("/exit")
    public ServerResponse exit(HttpSession session){

        session.removeAttribute(Const.CURRENTUSER);

        return ServerResponse.serverResponseBySuccess();
    }

}

