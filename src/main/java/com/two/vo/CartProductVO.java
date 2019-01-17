package com.two.vo;

import java.math.BigDecimal;

public class CartProductVO {

    private  Integer id;
    private  Integer  userId;
    private  Integer  quantity;
    private  String productName;
    private  String productSubtitle;
    private  String productMainImage;
    private BigDecimal productPrice;
    private BigDecimal productTotalPrice;
    private  Integer productStatus;
    private  Integer productStock;
    private  Integer productChecked;
    private  String limitQuantity;

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }



    public CartProductVO() {
    }

    public CartProductVO(Integer id, Integer userId, Integer quantity, String productName, String productSubtitle, String productMainImage, BigDecimal productPrice, BigDecimal productTotalPrice, Integer productStatus, Integer productStock, Integer productChecked, String limitQuantity) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
        this.productName = productName;
        this.productSubtitle = productSubtitle;
        this.productMainImage = productMainImage;
        this.productPrice = productPrice;
        this.productTotalPrice = productTotalPrice;
        this.productStatus = productStatus;
        this.productStock = productStock;
        this.productChecked = productChecked;
        this.limitQuantity = limitQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSubtitle() {
        return productSubtitle;
    }

    public void setProductSubtitle(String productSubtitle) {
        this.productSubtitle = productSubtitle;
    }

    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage;
    }

    public BigDecimal productPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getProuductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProductChecked() {
        return productChecked;
    }

    public void setProductChecked(Integer productChecked) {
        this.productChecked = productChecked;
    }

    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }
}
