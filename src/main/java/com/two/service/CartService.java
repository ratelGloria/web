package com.two.service;

import com.two.controller.ServerResponse;
import com.two.pojo.Product;
import org.springframework.stereotype.Service;


public interface CartService {


    ServerResponse addProductToCart(Integer userId,Integer productId,Integer quantity);

    ServerResponse list(Integer userId);

    ServerResponse updateProductInfo(Integer userId, Integer productId,Integer quantity);

    ServerResponse delete(Integer userId, String productId);

    ServerResponse chooseAll(Integer userId);

    ServerResponse chooseOne(Integer userId,Integer productId);

    ServerResponse select(Integer userId,Integer productId,Integer checked);

    ServerResponse un_select(Integer userId,Integer productId,Integer checked);

    ServerResponse get_cart_product_count(Integer userId);



}
