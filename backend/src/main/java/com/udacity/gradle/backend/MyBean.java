package com.udacity.gradle.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String joke;

    public String getData() {
        return joke;
    }

    public void setData(String data) {
        joke = data;
    }
}