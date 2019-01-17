package com.two.controller.portal;


import com.two.common.Const;
import com.two.controller.ServerResponse;
import com.two.pojo.UserInfo;
import com.two.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping("/add")
    public ServerResponse addProductToCart(HttpSession session,  Integer productId, Integer quantity) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }

        return cartService.addProductToCart(userInfo.getId(),productId,quantity);
    }

    @RequestMapping("/update")
    public ServerResponse updateProductInfo(HttpSession session, Integer productId,Integer quantity) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }


        return cartService.updateProductInfo(userInfo.getId(),productId,quantity);
    }

    @RequestMapping("/list")
    public ServerResponse list(HttpSession session) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }

        return cartService.list(userInfo.getId());
    }

    @RequestMapping("/delete")
    public ServerResponse deleteProductInfo(HttpSession session, String productId) {

         UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
         if(userInfo == null){
             return ServerResponse.serverResponseByError("需要登陆");
         }

        return cartService.delete(userInfo.getId(),productId);
    }

    @RequestMapping("/chooseAll")
    public ServerResponse chooseAll(HttpSession session) {
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }

        return cartService.chooseAll(userInfo.getId());
    }

    @RequestMapping("/chooseOne")
    public ServerResponse chooseOne(HttpSession session,Integer productId){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }

        return cartService.chooseOne(userInfo.getId(),productId);
    }

    @RequestMapping("/select")
    public ServerResponse select(HttpSession session, Integer productId) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }

        return cartService.select(userInfo.getId(),productId, Const.CartCheckEnum.PRODUCT_CHECKED.getCode());

    }

    @RequestMapping("/un_select")
    public ServerResponse un_select(HttpSession session, Integer productId) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }

        return cartService.un_select(userInfo.getId(),productId, Const.CartCheckEnum.PRODUCT_UNCHECKED.getCode());

    }
    @RequestMapping("/select_all")
    public ServerResponse select_all(HttpSession session) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }

        return cartService.select(userInfo.getId(),null, Const.CartCheckEnum.PRODUCT_CHECKED.getCode());

    }

    @RequestMapping("/un_select_all")
    public ServerResponse un_select_all(HttpSession session) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }

        return cartService.un_select(userInfo.getId(),null, Const.CartCheckEnum.PRODUCT_UNCHECKED.getCode());

    }

    @RequestMapping("/get_cart_product_count")
    public ServerResponse get_cart_product_count(HttpSession session) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError("需要登陆");
        }

        return cartService.get_cart_product_count(userInfo.getId());

    }

}
