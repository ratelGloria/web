package com.two.controller.portal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.two.common.Const;
import com.two.controller.ServerResponse;
import com.two.pojo.UserInfo;
import com.two.service.OrderService;
import com.two.service.PayInfoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    PayInfoServiceImp payInfoServiceImp;

    @RequestMapping("/createOrder")
    public ServerResponse createOrder(HttpSession session,Integer product, Integer shippingId){

         UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

         if(userInfo == null){
             return ServerResponse.serverResponseByError("请登录");
         }

         return orderService.cartCreateOrder(userInfo.getId(),shippingId);
    }

    @RequestMapping("/orderInfo")
    public ServerResponse orderInfo(HttpSession session){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }

        return orderService.orderInfo(userInfo.getId());
    }
    @RequestMapping("/orderInfoLimit")
    public ServerResponse orderInfoLimit(HttpSession session){

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }

        return orderService.orderInfoLimit(userInfo.getId());
    }

    @RequestMapping("/orderDetail")
    public ServerResponse orderDetail(HttpSession session, Long orderNo) {
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }

        return orderService.orderDetail(userInfo.getId(),orderNo);
    }

    @RequestMapping("/deleteOrder")
    public ServerResponse deleteOrder(HttpSession session, Long orderNo) {
        System.out.println("!!!!!!!!!!!!!!!!!"+orderNo);
        if(orderNo == null){
            return ServerResponse.serverResponseByError("请输入订单号");
        }
        if(!orderNo.getClass().toString().equals("Long")){

            return ServerResponse.serverResponseByError("输入不合法");
        }

        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }
      /*  Long orderNo_1 = new Long(orderNo);*/

        return orderService.deleteOrder(userInfo.getId(),orderNo);
    }

    @RequestMapping("/pay")
    public ServerResponse pay(HttpSession session,Long orderNo){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }

        return orderService.pay(userInfo.getId(),orderNo);
    }

    /*
    * 支付宝服务器回调应用服务器接口
    * */
    @RequestMapping("/callback")
    public ServerResponse callback(HttpServletRequest request){

        System.out.println("================");

        Map<String, String[]> parameterMap = request.getParameterMap();
        HashMap<String, String> requestHashMap = Maps.newHashMap();
        Iterator<String> iterator = parameterMap.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            String[] strings = parameterMap.get(key);
            String value = "";
            for(int i = 0;i<strings.length;i++){
                value = (i == strings.length-1?value+strings[i]:value+strings[i]+",");
            }
            requestHashMap.put(key,value);
        }
        //支付宝验签
        try {
            requestHashMap.remove("sign_type");
            boolean result = AlipaySignature.rsaCheckV2(requestHashMap, Configs.getAlipayPublicKey(), "utf-8", Configs.getSignType());

            if(!result){
                return ServerResponse.serverResponseByError("非法请求，验证不通过");
            }


        } catch (AlipayApiException e) {
            e.printStackTrace();
        }


        return orderService.alipay_callback(requestHashMap);
    }

    @RequestMapping("/selectOrderPayStatus")
    public ServerResponse selectOrderPayStatus(HttpSession session,Long orderNo){
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

        if(userInfo == null){
            return ServerResponse.serverResponseByError("请登录");
        }
        return orderService.selectOrderPayStatus(orderNo);
    }

}
