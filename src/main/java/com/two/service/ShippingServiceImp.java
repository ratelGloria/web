package com.two.service;

import com.alipay.api.domain.ShopPromoInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.controller.ServerResponse;
import com.two.dao.ShippingMapper;
import com.two.pojo.Shipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImp implements ShippingService {

    @Autowired
    ShippingMapper shippingMapper;

    @Override
    public ServerResponse address(Integer userId,Shipping shipping) {

        if(shipping == null){
            return ServerResponse.serverResponseByError("地址不能为空");
        }
        Shipping shipping1 = new Shipping();
        shipping1.setReceiverName(shipping.getReceiverName());

        List<Shipping> shippings1 = shippingMapper.selectByCondition(shipping1);
        if(shippings1.size()>0){
            return ServerResponse.serverResponseByError("地址名已存在");
        }

        shipping.setUserId(userId);
        int insert = shippingMapper.insert(shipping);

        if(insert>0){
            List<Shipping> shippings = shippingMapper.selectByCondition(shipping);
            if(shippings.size()>0){
                return ServerResponse.serverResponseBySuccess(shippings.get(0).getId(),"新建地址成功");
            }
            return ServerResponse.serverResponseByError();
        }


        return ServerResponse.serverResponseByError("地址添加失败");
    }

    @Override
    public ServerResponse delAddress(Integer userId, Integer shippingId) {

        if(shippingId == null){
            return ServerResponse.serverResponseByError("参数不能为空");
        }
        Shipping shipping = shippingMapper.selectByPrimaryKey(shippingId);
        if(shipping != null){
            int i = shippingMapper.deleteByPrimaryKey(shippingId);
            if(i>0){
                return ServerResponse.serverResponseBySuccess("删除成功");
            }else {
                return ServerResponse.serverResponseByError("删除失败");
            }

        }else {
            return ServerResponse.serverResponseByError("地址不存在");
        }
    }

    @Override
    public ServerResponse updateAddress(Integer userId, Shipping shipping) {

        if(shipping == null){
            return ServerResponse.serverResponseByError("参数不能为空");
        }

        Shipping shipping1 = shippingMapper.selectByPrimaryKey(shipping.getId());
        if(shipping1 == null){
            return ServerResponse.serverResponseByError("地址不存在");
        }
        shipping.setUserId(userId);
        int i = shippingMapper.updateByPrimaryKey(shipping);
        if(i>0){
            return ServerResponse.serverResponseBySuccess("更新地址成功");
        }


        return ServerResponse.serverResponseByError("更新地址失败");
    }

    @Override
    public ServerResponse selectAddress(Integer userId, Integer shipping) {

        if(shipping == null){
            return ServerResponse.serverResponseByError("");
        }
        Shipping shipping1 = shippingMapper.selectAddress(shipping);
        if(shipping1 == null){
            return ServerResponse.serverResponseByError("不存在");
        }

        return ServerResponse.serverResponseBySuccess(shipping1);
    }

    @Override
    public ServerResponse addressList(Integer userId) {

        /*Shipping shipping = new Shipping();
        shipping.setUserId(userId);*/
        PageHelper.startPage(1,10);
        List<Shipping> shippings = shippingMapper.addressList(userId);
        PageInfo pageInfo = new PageInfo(shippings);
        return ServerResponse.serverResponseBySuccess(pageInfo);
    }
}
