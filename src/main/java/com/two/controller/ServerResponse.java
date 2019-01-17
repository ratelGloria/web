package com.two.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Controller;

@Controller
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse <T> {

    private Integer status;
    private String msg;
    private T data;

    private ServerResponse() {
    }

    private ServerResponse(Integer status) {
        this.status = status;
    }

    private ServerResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private ServerResponse(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    private  ServerResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ServerResponse serverResponseBySuccess(T data,String msg){
        return new ServerResponse(ResponseCode.SUCCESS,msg,data);
    }
    public static <T> ServerResponse serverResponseBySuccess(){
        return new ServerResponse(ResponseCode.SUCCESS);
    }
    public static <T> ServerResponse serverResponseBySuccess(T data){
        return new ServerResponse(ResponseCode.SUCCESS,data);
    }




    public static <T> ServerResponse serverResponseByError(T data,String msg){
        return new ServerResponse(ResponseCode.ERROR,msg,data);
    }
    public static <T> ServerResponse serverResponseByError(){
        return new ServerResponse(ResponseCode.ERROR);
    }
    public static <T> ServerResponse serverResponseByError(T data){
        return new ServerResponse(ResponseCode.ERROR,data);
    }


    public static <T> ServerResponse createServletResponse (Integer status,String msg,T data){

        return new ServerResponse(status,msg,data);

    }


    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
