package com.rnk.design_patterns.composite.file.system;

public class Test {
    public static void main(String[] args) {
        Folder root  = new Folder("root");
        Folder gradle = (Folder)root.addChild(new Folder("gradle"));
        Folder wrapper = (Folder)gradle.addChild(new Folder("wrapper"));
        wrapper.addChild(new File("gradle-wrapper.jar"));
        wrapper.addChild(new File("gradle-wrapper.properties"));
        Folder src = (Folder)root.addChild(new Folder("src"));
        src.addChild(new Folder("main"));
        src.addChild(new Folder("test"));

        root.print();
    }
}
