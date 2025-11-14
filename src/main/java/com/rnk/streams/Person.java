package com.rnk.streams;

public class Person {
    private String name;
    private int age;

    //contructor

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //Getter
    public int getAge(){return age;}
    public String getName(){return name;}

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
