package com.two.controller.portal;

import com.two.common.Const;
import com.two.controller.ServerResponse;
import com.two.pojo.Product;
import com.two.pojo.UserInfo;
import com.two.service.ProductCustomerService;
import com.two.service.ProductCustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/product")
public class ProductCustomerController {

    @Autowired
    ProductCustomerService productCustomerService;

    @RequestMapping("/list")
    public ServerResponse list(
            @RequestParam(value = "id",required = false) Integer id,
                               @RequestParam(value = "keyword",required = false) String keyword,
                               @RequestParam(value = "pageNum",required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "order",required = false,defaultValue = "") String order) {

        return productCustomerService.list(id,keyword, pageNum, pageSize,order);
    }

    @RequestMapping("/detail")
    public ServerResponse detail(Integer id) {


        return productCustomerService.detail(id);
    }
}
