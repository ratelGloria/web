package com.two.service;

import com.two.common.Const;
import com.two.controller.ResponseCode;
import com.two.controller.ServerResponse;
import com.two.dao.UserInfoMapper;
import com.two.pojo.UserInfo;
import com.two.utils.MD5Utils;
import com.two.utils.TokenCache;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

@Service
public class UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    /*注册*/
        public ServerResponse logUp(String logUp_userName,String logUp_pwd,String logUp_email,String logUp_phone,String questionSelect,String answer,String role){


            if(logUp_userName == null || "".equals(logUp_userName)){
                return ServerResponse.serverResponseByError("用户名不能为空");
            }else{
                UserInfo userInfo = new UserInfo();
                userInfo.setUsername(logUp_userName);
                UserInfo userInfo1 = userInfoMapper.selectUserCondition(userInfo);
              /*  System.out.println("+++++++++"+userInfo1);*/
                if(userInfo1 != null){
                    return ServerResponse.serverResponseByError("用户名已存在");
                }
            }
            if(logUp_pwd == null || "".equals(logUp_pwd)){
                return ServerResponse.serverResponseByError("密码不能为空");
            }
            if(logUp_email == null || "".equals(logUp_email)){
                return ServerResponse.serverResponseByError("邮箱不能为空");
            }else{
                UserInfo userInfo = new UserInfo();
                userInfo.setEmail(logUp_email);
                UserInfo userInfo1 = userInfoMapper.selectUserCondition(userInfo);
                System.out.println("+++++++++"+userInfo1);
                if(userInfo1 != null){
                    return ServerResponse.serverResponseByError("邮箱已存在");
                }
            }
            if(logUp_phone == null || "".equals(logUp_phone)){
                return ServerResponse.serverResponseByError("电话不能为空");
            }
            if(answer == null || "".equals(answer)){
                return ServerResponse.serverResponseByError("回答不能为空");
            }
            /*if(role == null || "".equals(role)){
                return ServerResponse.serverResponseByError("role不能为空");
            }*/


            Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

            int dateD = c.get(Calendar.DATE);
            int dateM =  c.get(Calendar.MONTH)+1;
            int dateY =  c.get(Calendar.YEAR);

            java.sql.Date date = java.sql.Date.valueOf(dateY+"-"+dateM+"-"+dateD);
            System.out.println("date"+date);
            int role_Integer = Integer.parseInt(role);

            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(logUp_userName);
            userInfo.setPassword(logUp_pwd);
            userInfo.setEmail(logUp_email);
            userInfo.setPhone(logUp_phone);
            userInfo.setQuestion(questionSelect);
            userInfo.setAnswer(answer);

            userInfo.setCreateTime(date);
            userInfo.setUpdateTime(date);


            userInfo.setRole(Const.RoleEnum.ROLE_ADMIN.getCode());
            userInfo.setPassword(MD5Utils.getMD5Code(userInfo.getPassword()));

            int insert = userInfoMapper.insert(userInfo);

            if(insert>0){
                return ServerResponse.serverResponseBySuccess("注册成功");
            }

            return ServerResponse.serverResponseByError("注册失败");
    }

    public ServerResponse forgetPwdController(String userName){

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        UserInfo userInfo1 = userInfoMapper.selectUserNameInfo(userInfo);

        return ServerResponse.serverResponseBySuccess(userInfo1.getQuestion());
    }

    /*通过答案修改密码*/
    public ServerResponse matchAnswer(String userName,String answer){

            if(answer == null || "".equals(answer)){
                return ServerResponse.serverResponseByError("答案不能为空");
            }

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(userName);
        userInfo.setAnswer(answer);

        UserInfo userInfo1 = userInfoMapper.selectUserCondition(userInfo);

        if(userInfo1 == null){
            return ServerResponse.serverResponseByError("答案错误");
        }/*else {
            return ServerResponse.serverResponseBySuccess("答案正确");
        }*/

        String forgetToken = UUID.randomUUID().toString();

        TokenCache.set(userName,forgetToken);

        return ServerResponse.serverResponseBySuccess(forgetToken);

    }


/*登陆改密码*/
    public ServerResponse changePwd(String userName,String pwdNew,String forgetToken){

            if(userName == null || "".equals(userName)){
                return ServerResponse.serverResponseByError("用户名不能为空");
            }
        if(pwdNew == null || "".equals(pwdNew)){
            return ServerResponse.serverResponseByError("密码不能为空");
        }
        if(forgetToken == null || "".equals(forgetToken)){
            return ServerResponse.serverResponseByError("token不能为空");
        }


            String token = TokenCache.get(userName);
            if(token == null){
                return ServerResponse.serverResponseByError("token过期");
            }
            if(!token.equals(forgetToken)){
                return ServerResponse.serverResponseByError("无效的token");
            }

        UserInfo userInfo = new UserInfo();

        int i = userInfoMapper.updatePwd(userName, MD5Utils.getMD5Code(pwdNew));

       if(i>0){
           return ServerResponse.serverResponseBySuccess("修改成功");
       }else {
           return ServerResponse.serverResponseByError("密码修改失败");
       }

    }

    /*检查用户名邮箱是否存在*/
    public ServerResponse checkUserNameOrEmail(String str,String type){

        if(str == null || "".equals(str)){
            return ServerResponse.serverResponseByError("str不能为空");
        }
        if(type == null || "".equals(type)){
            return ServerResponse.serverResponseByError("type不能为空");
        }

        if(type.equals("username")){
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(str);
            int i = userInfoMapper.selectUserName(userInfo);

            if(i>0){
                return ServerResponse.serverResponseByError("用户名已存在");
            }else {
                return ServerResponse.serverResponseBySuccess();
            }
        }else if(type.equals("email")){
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(str);
            int i = userInfoMapper.selectUserName(userInfo);

            if(i>0){
                return ServerResponse.serverResponseByError("邮箱已存在");
            }else {
                return ServerResponse.serverResponseBySuccess();
            }
        }else {
            return ServerResponse.serverResponseByError("参数类型错误");
        }
    }

    /*获取用户信息*/
    public ServerResponse getUserInfo(Integer userId){

      /*  UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);*/
        /*Integer id = userInfo.getId();
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(id);
        UserInfo userInfo2 = userInfoMapper.selectUserCondition(userInfo1);
        if(userInfo2 == null){
            return ServerResponse.serverResponseByError("用户未登录");
        }*/
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        UserInfo userInfo1 = userInfoMapper.selectUserCondition(userInfo);
        return ServerResponse.serverResponseBySuccess(userInfo1);

    }

    /*普通用户登陆*/
    public ServerResponse logIn(String userName, String password) {
        if (userName == null || "".equals(userName)) {
            return ServerResponse.serverResponseByError("用户名不能为空");
        }
        if (password == null || "".equals(password)) {
            return ServerResponse.serverResponseByError("密码不能为空");
        }

        UserInfo userInfoResult = userInfoMapper.selectUserInfo(userName,MD5Utils.getMD5Code(password));
        System.out.println("000000000000000"+userInfoResult);
        if(userInfoResult == null){
            return ServerResponse.serverResponseByError("用户名和密码错误");
        }
        if (userInfoResult.getUsername().equals(userName) && userInfoResult.getPassword().equals(MD5Utils.getMD5Code(password))) {

            return ServerResponse.serverResponseBySuccess(userInfoResult);

        } else {

            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名和密码错误");

        }
    }

    /*管理员登陆*/
    public ServerResponse managerLogIn(String userName, String password) {
        if (userName == null || "".equals(userName)) {
            return ServerResponse.serverResponseByError("用户名不能为空");
        }
        if (password == null || "".equals(password)) {
            return ServerResponse.serverResponseByError("密码不能为空");
        }
        System.out.println("---------userName"+userName);
        System.out.println("---------password"+password);
        UserInfo userInfoResult = userInfoMapper.selectUserInfo(userName,MD5Utils.getMD5Code(password));
        System.out.println("---------"+userInfoResult);
        if(userInfoResult == null){
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名和密码错误");
        }

        if (userInfoResult.getUsername().equals(userName) && userInfoResult.getPassword().equals(MD5Utils.getMD5Code(password))) {

            if(userInfoResult.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()){
                return ServerResponse.serverResponseByError("非管理员");
            }else {
                return ServerResponse.serverResponseBySuccess(userInfoResult);
            }

        } else {

            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "用户名和密码错误");

        }


    }

    public ServerResponse logInChangePwd(String userName,String passwordOld,String passwordNew){
        if (passwordOld == null || "".equals(passwordOld)) {
            return ServerResponse.serverResponseByError("旧密码不能为空");
        }
        if (passwordNew == null || "".equals(passwordNew)) {
            return ServerResponse.serverResponseByError("新密码不能为空");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        userInfo.setPassword(MD5Utils.getMD5Code(passwordOld));
        UserInfo userInfo1 = userInfoMapper.selectUserCondition(userInfo);
        System.out.println(userInfo1);
        if(userInfo1 == null){
            return ServerResponse.serverResponseByError("旧密码错误");
        }else {
            int i = userInfoMapper.updatePwd(userName, MD5Utils.getMD5Code(passwordNew));
            if(i>0){
                return ServerResponse.serverResponseBySuccess("密码修改成功");
            }else {
                return ServerResponse.serverResponseByError("密码修改失败");
            }
        }

    }

    public ServerResponse updateUserInfo(UserInfo user){

            if(user == null){
                return ServerResponse.serverResponseByError("参数不能为空");
            }

        int i = userInfoMapper.updateUserInfo(user);

        if(i>0){
            return ServerResponse.serverResponseBySuccess("个人信息修改成功");
        }else {
            return ServerResponse.serverResponseByError("个人信息修改失败");
        }

    }


}
