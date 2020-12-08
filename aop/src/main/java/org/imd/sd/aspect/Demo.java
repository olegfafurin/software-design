package org.imd.sd.aspect;

import org.imd.sd.aspect.demo.Person;
import org.imd.sd.aspect.demo.inner.Point;

public class Demo {
    public static void main(String[] args) {
        Point point = new Point();
        point.setX(10);
        int acc = 0;
        for (int i = 0; i < 100; i++) {
            point.setX(i * i + acc);
            acc = point.getX();
            if (i % 2 == 0)
                point.incY();
        }
        Person person = new Person("Albert", 20);
        int age = person.getAge();
        String personRepr=  person.toString();
    }
}
