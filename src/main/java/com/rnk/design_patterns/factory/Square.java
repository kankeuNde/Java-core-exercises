package com.rnk.design_patterns.factory;

public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method");
    }
}
