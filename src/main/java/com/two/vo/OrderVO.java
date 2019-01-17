package com.two.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVO {

    private Long orderNo;
    private BigDecimal payment;
    private Integer paymentType;
    private String paymentTypeDesc;

    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }

    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    private Integer postage;
    private Integer status;
    private Date paymentTime;
    private Date sendTime;
    private Date endTime;
    private Date closeTime;
    private Date createTime;
    private OrderItemVoList orderItemVoList;
    private Integer shippingId;
    private ShippingVO shippingVo;

    private String statusDesc;

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public OrderVO() {
    }

    private List<OrderItemVoList> orderItemVoLists;
    private String imageHost;

    private String receiverName;
    private ShippingVO ShippingVO;

    public List<OrderItemVoList> getOrderItemVoLists() {
        return orderItemVoLists;
    }

    public void setOrderItemVoLists(List<OrderItemVoList> orderItemVoLists) {
        this.orderItemVoLists = orderItemVoLists;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public com.two.vo.ShippingVO getShippingVO() {
        return ShippingVO;
    }

    public void setShippingVO(com.two.vo.ShippingVO shippingVO) {
        ShippingVO = shippingVO;
    }

    public OrderVO(Long orderNo, BigDecimal payment, Integer postage, Integer status, Date paymentTime, Date sendTime, Date endTime, Date closeTime, Date createTime, OrderItemVoList orderItemVoList, Integer shippingId, ShippingVO shippingVo) {
        this.orderNo = orderNo;
        this.payment = payment;
        this.postage = postage;
        this.status = status;
        this.paymentTime = paymentTime;
        this.sendTime = sendTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.createTime = createTime;
        this.orderItemVoList = orderItemVoList;
        this.shippingId = shippingId;
        this.shippingVo = shippingVo;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "orderNo=" + orderNo +
                ", payment=" + payment +
                ", postage=" + postage +
                ", status=" + status +
                ", paymentTime=" + paymentTime +
                ", sendTime=" + sendTime +
                ", endTime=" + endTime +
                ", closeTime=" + closeTime +
                ", createTime=" + createTime +
                ", orderItemVoList=" + orderItemVoList +
                ", shippingId=" + shippingId +
                ", shippingVo=" + shippingVo +
                '}';
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public OrderItemVoList getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(OrderItemVoList orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public ShippingVO getShippingVo() {
        return shippingVo;
    }

    public void setShippingVo(ShippingVO shippingVo) {
        this.shippingVo = shippingVo;
    }
}
