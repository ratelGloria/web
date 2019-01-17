package com.two.controller.portal;

import com.two.common.Const;
import com.two.controller.ServerResponse;
import com.two.pojo.Shipping;
import com.two.pojo.UserInfo;
import com.two.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/shipping")
public class ShoppingController {

    @Autowired
    ShippingService shippingService;

    @RequestMapping("/add")
    public ServerResponse address(HttpSession session, Shipping shipping) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }
        return ServerResponse.serverResponseBySuccess(shippingService.address(userInfo.getId(),shipping));
    }

    @RequestMapping("/delAddress")
    public ServerResponse delAddress(HttpSession session, Integer shippingId) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }
        return ServerResponse.serverResponseBySuccess(shippingService.delAddress(userInfo.getId(),shippingId));
    }

    @RequestMapping("/updateAddress")
    public ServerResponse updateAddress(HttpSession session, Shipping shipping) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }
        return ServerResponse.serverResponseBySuccess(shippingService.updateAddress(userInfo.getId(),shipping));
    }

    @RequestMapping("/selectAddress")
    public ServerResponse selectAddress(HttpSession session, Integer shipping) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }
        return ServerResponse.serverResponseBySuccess(shippingService.selectAddress(userInfo.getId(),shipping));
    }

    @RequestMapping("/addressList")
    public ServerResponse addressList(HttpSession session, Integer shipping) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }
        return ServerResponse.serverResponseBySuccess(shippingService.addressList(userInfo.getId()));
    }

}
