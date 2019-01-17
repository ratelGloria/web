package com.two.service;

import com.alipay.api.domain.ShopPromoInfo;
import com.two.controller.ServerResponse;
import com.two.pojo.Shipping;

public interface ShippingService {

    ServerResponse address(Integer userId,Shipping shipping);

    ServerResponse delAddress(Integer userId,Integer shippingId);

    ServerResponse updateAddress(Integer userId,Shipping shipping);

    ServerResponse selectAddress(Integer userId,Integer shipping);

    ServerResponse addressList(Integer userId);
}
