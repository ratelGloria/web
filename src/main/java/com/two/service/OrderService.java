package com.two.service;

import com.two.controller.ServerResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface OrderService {


    ServerResponse createOrder(Integer userId,Integer shippingId,Integer addressId);

    ServerResponse createCartOrder(Integer userId,Integer shippingId);

    ServerResponse cartCreateOrder(Integer userId,Integer shippingId);

    ServerResponse orderInfo(Integer userId);

    ServerResponse orderInfoLimit(Integer userId);

    ServerResponse orderDetail(Integer userId,Long orderNo);

    ServerResponse deleteOrder(Integer userId,Long orderNo);

    ServerResponse pay(Integer userId,Long OrderNo);

    ServerResponse alipay_callback(Map<String,String> map);

    ServerResponse send_goods(Long orderNo);

    ServerResponse selectOrderPayStatus(Long orderNo);

}
