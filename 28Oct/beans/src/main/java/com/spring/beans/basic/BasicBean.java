package com.spring.beans.basic;

public class BasicBean {

    private String message;

    public BasicBean(String message) {
        this.message = message;
    }

    public void showMessage() {
        System.out.println(message);
    }
}