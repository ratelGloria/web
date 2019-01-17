package com.two.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.common.Const;
import com.two.controller.ServerResponse;
import com.two.dao.CartMapper;
import com.two.dao.ProductMapper;
import com.two.pojo.Cart;
import com.two.pojo.Product;
import com.two.utils.CalculateUtil;
import com.two.vo.CartProductVO;
import com.two.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CartMapper cartMapper;

    @Override
    public ServerResponse addProductToCart(Integer userId,Integer productId,Integer quantity) {
        if(userId == null){
            return ServerResponse.serverResponseByError("请登录");
        }
        if(productId == null){
            return ServerResponse.serverResponseByError("商品不存在");
        }
        Product product = new Product();
        product.setId(productId);
        List<Product> products = productMapper.selectProductCondition(product);
        if(products.size()>0){
            if(products.get(0).getStatus() != Const.ProductStatusEnum.PRODUCT_ONLINE.getCode()){
                return ServerResponse.serverResponseByError("商品不存在");
            }
        }else {
            return ServerResponse.serverResponseByError("商品不存在");
        }

        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);

        if(cart == null){
            Cart cart1 = new Cart();
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            cart1.setChecked(Const.CartCheckEnum.PRODUCT_CHECKED.getCode());
            cart1.setQuantity(quantity);
            int i = cartMapper.addProductToCart(cart1);
            CartVO cartVO = getCartVO(userId);

             return ServerResponse.serverResponseBySuccess(cartVO);
        }else {
            Integer quantity1 = cart.getQuantity();

            int i = cartMapper.updateProductToCart(userId, productId,(quantity+quantity1));
            List<Cart> carts = cartMapper.selectCartByUserId(userId);
            if(i>0){
                CartVO cartVO = getCartVO(userId);
                return ServerResponse.serverResponseBySuccess(cartVO);
            }else {
                return ServerResponse.serverResponseByError("添加失败");
            }
        }
    }

    @Override
    public ServerResponse list(Integer userId/*,Integer pageNum,Integer pageSize*/) {

        List<Cart> carts = cartMapper.selectCartByUserId(userId);

        CartVO cartVO = getCartVO(userId);

        return ServerResponse.serverResponseBySuccess(cartVO);
    }

    @Override
    public ServerResponse updateProductInfo(Integer userId, Integer productId,Integer quantity) {

        if(productId == null || quantity == null){
            return ServerResponse.serverResponseByError("参数不能为空");
        }
        Cart cart1 = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if(cart1 != null){
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            List<Cart> carts = cartMapper.selectCartCondition(cart);
            if(carts.size()>0){
                carts.get(0).setQuantity(quantity);
                cartMapper.updateProductToCart(carts.get(0));
            }
            return ServerResponse.serverResponseBySuccess(getCartVO(userId));
        }
        return ServerResponse.serverResponseByError("不存在该商品");
        }


    @Override
    public ServerResponse delete(Integer userId, String productId) {
        List<Integer> productIds = new ArrayList<>();
        if(productId != null && !"".equals(productId)){
            String[] split = productId.split(",");
            for(int i=0;i<split.length;i++){
                int a = Integer.parseInt(split[i]);
                productIds.add(a);
            }
        }

        int delete = cartMapper.delete(userId, productIds);
        if(delete>0){

            CartVO cartVO = getCartVO(userId);

            return ServerResponse.serverResponseBySuccess(cartVO);
        }
        return ServerResponse.serverResponseBySuccess("删除失败");
    }

    @Override
    public ServerResponse chooseAll(Integer userId) {

        int i = cartMapper.chooseAll(userId, Const.CartCheckEnum.PRODUCT_CHECKED.getCode());

        if(i>0){
            return ServerResponse.serverResponseBySuccess(getCartVO(userId));
        }

        return ServerResponse.serverResponseByError("全选失败");
    }

    @Override
    public ServerResponse chooseOne(Integer userId, Integer productId) {

        int i = cartMapper.chooseOne(userId, productId, Const.CartCheckEnum.PRODUCT_CHECKED.getCode());
        if(i>0){
            return ServerResponse.serverResponseBySuccess(getCartVO(userId));
        }
        return ServerResponse.serverResponseByError("选择失败");
    }

    @Override
    public ServerResponse select(Integer userId, Integer productId,Integer checked) {
        checked= Const.CartCheckEnum.PRODUCT_CHECKED.getCode();
        if(productId == null){
            int i = cartMapper.selectOrUnselectProduct(userId, null,checked);
            return ServerResponse.serverResponseBySuccess(getCartVO(userId));
        }

        int i = cartMapper.selectOrUnselectProduct(userId, productId,checked);
        return ServerResponse.serverResponseBySuccess(getCartVO(userId));
    }

    @Override
    public ServerResponse un_select(Integer userId, Integer productId,Integer checked) {
        if(productId == null){
            int i = cartMapper.selectOrUnselectProduct(userId, null, Const.CartCheckEnum.PRODUCT_UNCHECKED.getCode());
            return ServerResponse.serverResponseBySuccess(getCartVO(userId));
        }
        Cart cart1 = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if(cart1 == null){
            return ServerResponse.serverResponseByError("不存在该商品");
        }
        int i = cartMapper.selectOrUnselectProduct(userId, productId, Const.CartCheckEnum.PRODUCT_UNCHECKED.getCode());
        return ServerResponse.serverResponseBySuccess(getCartVO(userId));
    }

    @Override
    public ServerResponse get_cart_product_count(Integer userId) {

        int cart_product_count = cartMapper.get_cart_product_count(userId);
        return ServerResponse.serverResponseBySuccess(cart_product_count);
    }




   /* private CartProductVO getCartProductVO(P){

    }*/

    private CartVO getCartVO(Integer userId){
       CartVO cartVO = new CartVO();
       ArrayList<CartProductVO> cartProductVOList = new ArrayList<>();

       List<Cart> carts = cartMapper.selectCartByUserId(userId);
       System.out.println("77777777777777"+carts);

       BigDecimal cartTotal =new BigDecimal(String.valueOf("0"));
       if(carts != null && carts.size()>0){
           for(int i= 0;i<carts.size();i++){
               CartProductVO cartProductVO = new CartProductVO();

    System.out.println("wwwwwwwwwwwwwww"+carts.get(i).getProductId());
               Product product = new Product();
               product.setId(carts.get(i).getProductId());
               List<Product> products = productMapper.selectProductCondition(product);

               System.out.println(">>>>>>>>>>>>"+products.get(0).getName());
               cartProductVO.setId(carts.get(i).getId());

               cartProductVO.setProductChecked(carts.get(i).getChecked());
               cartProductVO.setProductName(products.get(0).getName());
               cartProductVO.setProductStatus(products.get(0).getStatus());
               cartProductVO.setProductPrice(products.get(0).getPrice());
               cartProductVO.setProductStock(products.get(0).getStock());
               cartProductVO.setQuantity(carts.get(i).getQuantity());
               cartProductVO.setProductMainImage(products.get(0).getMainImage());
               cartProductVO.setProductSubtitle(products.get(0).getSubtitle());
               cartProductVO.setProductTotalPrice(CalculateUtil.multiply(products.get(0).getPrice(),carts.get(i).getQuantity()));
               cartProductVO.setUserId(userId);
               Integer stock = products.get(0).getStock();
              /* System.out.println("stock------"+stock);*/
               int limitProductCount=0;
               if(stock>=carts.get(i).getQuantity()){
                   limitProductCount= carts.get(i).getQuantity();
                   cartProductVO.setLimitQuantity("LIMIT_NUM_SUCCESS");
               }else {
                   limitProductCount=stock;
                   Cart cart = new Cart();
                   cart.setQuantity(stock);
                   cart.setId(userId);
                   cart.setUserId(carts.get(i).getUserId());
                   cart.setChecked(carts.get(i).getChecked());
                   cart.setProductId(carts.get(i).getProductId());
                   cartMapper.updateProductToCart(cart);
                   cartProductVO.setLimitQuantity("LIMIT_NUM_UNSUCCESS");
               }

               cartTotal = CalculateUtil.add(cartTotal, new BigDecimal(String.valueOf(cartProductVO.getProductTotalPrice())));
               cartProductVOList.add(cartProductVO);
           }
           cartVO.setCartProductVOList(cartProductVOList);
           cartVO.setCarttoalprice(cartTotal);

           Cart cart = new Cart();
           cart.setChecked(Const.CartCheckEnum.PRODUCT_UNCHECKED.getCode());
           List<Cart> carts1 = cartMapper.selectCartCondition(cart);
           if(carts1.size()>0){
               cartVO.setIsallchecked(false);
           }else {
               cartVO.setIsallchecked(true);
           }
       }
        return cartVO;
    }
}
