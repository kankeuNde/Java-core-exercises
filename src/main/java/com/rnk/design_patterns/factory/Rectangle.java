package com.rnk.design_patterns.factory;

public class Rectangle implements Shape{

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() meythod.");
    }
}
