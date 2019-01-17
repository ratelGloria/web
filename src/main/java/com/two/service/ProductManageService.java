package com.two.service;

import com.two.controller.ServerResponse;
import com.two.pojo.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.SunHints;

import java.util.List;


public interface ProductManageService {

    /*add*/

    ServerResponse addProduct(Product product);


    /*上下架*/

    ServerResponse changeProductStatus(Integer categoryId,Integer status);

    ServerResponse getVoById(Integer id);

    ServerResponse list(Integer pageName,Integer pageSize);

    ServerResponse search(Integer productId, String productName, @RequestParam(value = "pageName",defaultValue = "1")Integer pageName,
                          @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize);


    ServerResponse upload(MultipartFile file,String path);


}
