package com.two.controller.backend;

import com.github.pagehelper.PageHelper;
import com.two.common.Const;
import com.two.controller.ServerResponse;
import com.two.pojo.Product;
import com.two.pojo.UserInfo;
import com.two.service.ProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    ProductManageService productManageService;


    @RequestMapping("/addProduct")
    public ServerResponse addProduct(HttpSession session,Product product) {

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NEED_LOGIN.getCode(),Const.ReponseCodeEnum.NEED_LOGIN.getDesc());
        }

        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
        }

        return productManageService.addProduct(product);

    }

    @RequestMapping("/changeProductStatus")
    public ServerResponse changeProductStatus(HttpSession session,Integer id, Integer status) {
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NEED_LOGIN.getCode(),Const.ReponseCodeEnum.NEED_LOGIN.getDesc());
        }

        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
        }

        return productManageService.changeProductStatus(id, status);
    }


    @RequestMapping("/getVoById")
    public ServerResponse getVoById(HttpSession session,Integer id) {
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NEED_LOGIN.getCode(),Const.ReponseCodeEnum.NEED_LOGIN.getDesc());
        }

        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
        }

        return productManageService.getVoById(id);
    }

    @RequestMapping("/getVoByList/{pageName}/{pageSize}")
    public ServerResponse list(HttpSession session,
            @RequestParam(value = "pageName",required = false,defaultValue ="1") Integer pageName,
                               @RequestParam(value = "pageName",required = false,defaultValue ="10") Integer pageSize) {
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NEED_LOGIN.getCode(),Const.ReponseCodeEnum.NEED_LOGIN.getDesc());
        }

        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return productManageService.list(pageName,pageSize);
    }


    @RequestMapping("/search/{pageName}/{pageSize}")
    public ServerResponse search(HttpSession session,Integer productId, String productName,
                                 @RequestParam(value = "pageName",required = false,defaultValue ="1") Integer pageName,
                                 @RequestParam(value = "pageName",required = false,defaultValue ="10") Integer pageSize) {
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);
        if(userInfo == null){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NEED_LOGIN.getCode(),Const.ReponseCodeEnum.NEED_LOGIN.getDesc());
        }

        if(userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
        }
        return  productManageService.search(productId, productName, pageName, pageSize);

    }

}
