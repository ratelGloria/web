package com.two.vo;

import java.math.BigDecimal;
import java.util.List;

public class CartVO {

    private List<CartProductVO> cartProductVOList;

    private boolean isallchecked;

    private BigDecimal carttoalprice;

    public List<CartProductVO> getCartProductVOList() {
        return cartProductVOList;
    }

    public void setCartProductVOList(List<CartProductVO> cartProductVOList) {
        this.cartProductVOList = cartProductVOList;
    }

    public boolean isIsallchecked() {
        return isallchecked;
    }

    public void setIsallchecked(boolean isallchecked) {
        this.isallchecked = isallchecked;
    }

    public BigDecimal getCarttoalprice() {
        return carttoalprice;
    }

    public void setCarttoalprice(BigDecimal carttoalprice) {
        this.carttoalprice = carttoalprice;
    }
}
