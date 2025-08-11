package com.scientia.mystore.constants;

public class ApplicationConstant {
    private ApplicationConstant(){
        throw new AssertionError("Cannot be instantiated");
    }

    public static final String JWT_SECRET_KEY = "MY_KEY";
    public static final String JWT_SECRET_DEFAULT_VALUE = "jxEqechweojcnzYMsjbxmbnqjru23fjafldjTqepmxUn4";
}
