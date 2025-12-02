package com.rnk.design_patterns.adapter.vga;

public class Projector implements Vga {
    @Override
    public void print(String message) {
        System.out.println("******** Projector *******");
        System.out.println(message);
        System.out.println("******** Projector *******");
    }
}
