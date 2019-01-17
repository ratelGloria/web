package com.two.controller.backend;

import com.two.common.Const;
import com.two.controller.ServerResponse;
import com.two.pojo.UserInfo;
import com.two.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manager/user")
public class ManagerController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/managerLogIn")
    public ServerResponse managerLogIn(String userName, String password, HttpSession session){

        ServerResponse serverResponse = userInfoService.managerLogIn(userName, password);

        if(serverResponse.getStatus() == 0){
            UserInfo userInfo = (UserInfo)serverResponse.getData();
            session.setAttribute(Const.CURRENTUSER,userInfo);
        }
        return serverResponse;

    }


}
