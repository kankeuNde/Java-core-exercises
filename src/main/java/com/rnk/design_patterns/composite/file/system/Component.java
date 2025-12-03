package com.rnk.design_patterns.composite.file.system;

public abstract class Component {
    protected String name;
    protected int level = 0;

    public Component(String name){
        this.name=name;
    }

    protected String tabs(){
        String tabs = "";
        for(int i=0;i<this.level;i++){
            tabs += "\t";
        }
        return tabs;
    }

    public abstract void print();
}
