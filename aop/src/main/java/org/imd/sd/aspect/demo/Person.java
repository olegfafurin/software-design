package org.imd.sd.aspect.demo;


public class Person {

    public final String name;
    private final Integer age;

    @Override
    public String toString() {
        return "Person {name: " + name + "; age: " + age.toString() + "}";
    }


    public Person(String name, Integer age) {
        this.age = age;
        this.name = name;
    }

    public int getAge(){
        return age;
    }
}
