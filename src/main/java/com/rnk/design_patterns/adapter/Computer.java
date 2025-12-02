package com.rnk.design_patterns.adapter;

import com.rnk.design_patterns.adapter.vga.Vga;

public class Computer {

    Vga vga;

    public void setVga(Vga vga) {
        this.vga = vga;
    }

    public void view(String message){
        System.out.println("--------------Computer View--------------");
        vga.print(message);
        System.out.println("--------------Computer View--------------");
    }
}
