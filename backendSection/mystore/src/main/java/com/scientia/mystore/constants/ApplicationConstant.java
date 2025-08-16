package com.scientia.mystore.constants;

public class ApplicationConstant {
    private ApplicationConstant(){
        throw new AssertionError("Cannot be instantiated");
    }

    public static final String JWT_SECRET_KEY = "MY_KEY";
    public static final String JWT_SECRET_DEFAULT_VALUE = "jxEqechweojcnzYMsjbxmbnqjru23fjafldjTqepmxUn4";
    public static final String JWT_HEADER = "Authorization";


    public static final String  ORDER_STATUS_CONFIRMED = "CONFIRMED";
    public static final String  ORDER_STATUS_CREATED = "CREATED";
    public static final String  ORDER_STATUS_CANCELLED = "CANCELLED";

}
