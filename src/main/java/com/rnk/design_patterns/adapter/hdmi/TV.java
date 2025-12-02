package com.rnk.design_patterns.adapter.hdmi;

public class TV implements Hdmi {
    @Override
    public void plot(byte[] data) {
        System.out.println("############ TV plotting #############");
        System.out.println(new String(data));
        System.out.println("############ TV plotting #############");
    }
}
