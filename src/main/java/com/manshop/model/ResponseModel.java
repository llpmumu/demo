
package com.manshop.model;
public class ResponseModel<T> {

    public static final int SUCCESS_CODE = 200;
    public static final int FAILED_CODE = 205;

    private int code;
    private String msg;
    private T data;


    public ResponseModel(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static ResponseModel getCommonSuccessResponseModel(Object data){
        return new ResponseModel(SUCCESS_CODE,"success",data);
    }

    public static ResponseModel getCommonFailedResponseModel(Object data){
        return new ResponseModel(FAILED_CODE,"failed",data);
    }


}
