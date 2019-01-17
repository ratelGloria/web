package com.two.controller.portal;

import com.two.common.Const;
import com.two.controller.ServerResponse;
import com.two.pojo.UserInfo;
import com.two.service.PayInfoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class PayController {

 /*   @Autowired
    PayInfoServiceImp payInfoServiceImp;

    @RequestMapping("/pay")
    ServerResponse pay(HttpSession session,Long orderNo){
         UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

         if(userInfo == null){
             return ServerResponse.serverResponseByError("请登录");
         }

         return payInfoServiceImp.pay(userInfo.getId(),orderNo);*/

}
