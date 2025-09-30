package org.algorithms.test.copilot.tutorial;

abstract class Shape {
    public abstract double getArea();

    public static void describe() {
        System.out.println("This is a shape.");
    }
}

class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    // override getArea()
	@Override
	public double getArea() {
        double area = Math.PI * radius * radius;
        System.out.println("This is a Circle. " + area);
        return area;
	}
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // override getArea()	
	@Override
	public double getArea() {
        double area = width * height;
        System.out.println("This is a Rectangle. " + area);
        return area;
    }
}

public class ShapeTest {
    public static void main(String[] args) {
        Shape s1 = new Circle(3);
        Shape s2 = new Rectangle(4, 5);

        System.out.println(s1.getArea()); // This is a Circle.
        System.out.println(s2.getArea()); // This is a Rectangle.

        Shape.describe(); // This is a shape.
        //noinspection AccessStaticViaInstance on purpose
        s1.describe();    // This is a shape.
    }
}