package org.algorithms.test.copilot.tutorial;

interface Flyable {
    default void move() {
        System.out.println("Flying through the sky.");
    }
}

interface Swimmable {
    default void move() {
        System.out.println("Swimming through the water.");
    }
}

class Duck implements Flyable, Swimmable {
	@Override
    public void move() {
        Flyable.super.move();
        Swimmable.super.move();
        System.out.println("Flying through the water and swimming in the clouds.");
    }
}

public class InterfaceTest {
    public static void main(String[] args) {
        Duck d = new Duck();
        d.move(); // Flying, Swimming, plus Flying through the water and swimming in the clouds.
    }
}