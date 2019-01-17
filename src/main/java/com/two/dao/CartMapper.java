package com.two.dao;

import com.two.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {

    Cart selectCartByUserIdAndProductId(@Param("userId") Integer userId,
                                        @Param("productId") Integer productId);

    List<Cart> selectCartCondition(Cart cart);
    int addProductToCart(Cart cart);

    int updateProductToCart(@Param("userId") Integer userId,
                            @Param("productId") Integer productId,
                            @Param("quantity") Integer quantity);

    List<Cart> selectCartByUserId(@Param("userId") Integer userId);

    int updateProductToCart(Cart cart);

    /*int updateProductToCart(Integer productId,Integer quantity);*/

    int delete(@Param("userId") Integer userId,
               @Param("productId") List<Integer> productId);

    int chooseAll(@Param("userId") Integer userId,
                  @Param("status") Integer status);

    int chooseOne(@Param("userId") Integer userId,
                  @Param("productId") Integer productId,
                  @Param("status") Integer status);

    int selectOrUnselectProduct(@Param("userId") Integer userId,
                                @Param("productId") Integer productId,
                                @Param("checked") Integer checked);

    int get_cart_product_count(@Param("userId") Integer userId);


    List<Cart> findCartListByUserIdAndChecked(@Param("userId") Integer userId,
                                              @Param("checked") Integer checked);

    int batchDelete(List<Cart> cartList);
}
