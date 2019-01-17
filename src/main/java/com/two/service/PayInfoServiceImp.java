package com.two.service;

import com.two.controller.ServerResponse;
import com.two.dao.OrderMapper;
import com.two.dao.PayInfoMapper;
import com.two.pojo.Order;
import com.two.pojo.PayInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PayInfoServiceImp implements PayInfoService
{
    @Autowired
    PayInfoMapper payInfoMapper;

    @Autowired
    OrderMapper orderMapper;

   /* @Override
    public ServerResponse pay(Integer userId, Long orderNo) {

        if(orderNo == null){
            return ServerResponse.serverResponseByError("请输入订单号");
        }
        PayInfo payInfo = payInfoMapper.selectUserIdAndOrderNo(userId, orderNo);
        if(payInfo != null){
            return ServerResponse.serverResponseByError("订单已存在");
        }

        List<Order> orders = orderMapper.selectOrderByDetail(userId, orderNo);
        if(orders.size()<= 0){
            return ServerResponse.serverResponseByError("订单b不存在");
        }


        payInfoMapper.insert()

        return null;
    }*/

   /* private PayInfo getPayInfo(Order order){

        PayInfo payInfo = new PayInfo();

        payInfo.setOrderNo(order.setOrderNo());
        payInfo.setPayPlatform();
        payInfo.setPlatformStatus();
        payInfo.setUserId(order.getUserId() i );
        payInfo.setPlatformNumber();
    }*/
}
