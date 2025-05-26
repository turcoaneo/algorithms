package org.algorithms.test.copilot.patterns.creational;

// 1️⃣ Interface for the product types
interface Shape {
    void draw();
}

// 2️⃣ Concrete implementations
class Circle implements Shape {
    public void draw() { System.out.println("Drawing a Circle"); }
}

class Rectangle implements Shape {
    public void draw() { System.out.println("Drawing a Rectangle"); }
}

// 3️⃣ Factory to create objects dynamically
class ShapeFactory {
    public static Shape getShape(String type) {
        if (type.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (type.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        }
        throw new IllegalArgumentException("Unknown shape type: " + type);
    }
}

// 4️⃣ Client code using the factory
public class FactoryPatternDemo {
    public static void main(String[] args) {
        Shape shape1 = ShapeFactory.getShape("circle");
        shape1.draw(); // "Drawing a Circle"

        Shape shape2 = ShapeFactory.getShape("rectangle");
        shape2.draw(); // "Drawing a Rectangle"
    }
}
