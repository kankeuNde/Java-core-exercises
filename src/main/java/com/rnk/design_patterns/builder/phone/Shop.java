package com.rnk.design_patterns.builder.phone;

public class Shop {
    public static void main(String[] args) {
        PhoneBuilder builder = new PhoneBuilder();
        builder.setBattery(12);
        builder.setCamera(123);
        builder.setOs("Android");
        builder.setProcessor("Intel");
        builder.setScreenSize(5.5);

        Phone phone = builder.getPhone();
        System.out.println(phone);
    }
}
