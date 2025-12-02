package com.rnk.design_patterns.adapter;

import com.rnk.design_patterns.adapter.hdmi.Hdmi;
import com.rnk.design_patterns.adapter.vga.Vga;

public class VgaHdmiAdapter implements Vga {

    private Hdmi hdmi;

    public void setHdmi(Hdmi hdmi) {
        this.hdmi = hdmi;
    }

    @Override
    public void print(String message) {
        System.out.println("############ VGA HDMI Adapter #############");
        hdmi.plot(message.getBytes());
        System.out.println("############ VGA HDMI Adapter #############");
    }
}
