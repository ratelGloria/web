package com.two.service;

import com.two.controller.ServerResponse;
import com.two.pojo.Product;

import java.util.List;

public interface ProductCustomerService {

    ServerResponse list(Integer id,String keyword,Integer pageNum,Integer pageSize,String order);

    ServerResponse detail(Integer id);



}
