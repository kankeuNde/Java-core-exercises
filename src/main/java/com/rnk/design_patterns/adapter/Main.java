package com.rnk.design_patterns.adapter;

import com.rnk.design_patterns.adapter.hdmi.TV;
import com.rnk.design_patterns.adapter.vga.Monitor;
import com.rnk.design_patterns.adapter.vga.Projector;

public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.setVga(new Monitor());
        computer.view("Message on Monitor");
        computer.setVga(new Projector());
        computer.view("Message projected");

        VgaHdmiAdapter adapter = new VgaHdmiAdapter();
        adapter.setHdmi(new TV());
        computer.setVga(adapter);
        computer.view("Message on TV");
    }
}
