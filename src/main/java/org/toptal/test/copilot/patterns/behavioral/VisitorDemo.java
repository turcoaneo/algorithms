package org.toptal.test.copilot.patterns.behavioral;

// Visitor Interface
interface EtiquetteVisitor {
    void visit(FineDining fineDining);
    void visit(FastFood fastFood);
    void visit(JapaneseRestaurant japaneseRestaurant);
}

// Concrete Visitor (Etiquette Rules)
class EtiquetteGuide implements EtiquetteVisitor {
    public void visit(FineDining fineDining) {
        System.out.println("At " + fineDining.getName() + ", use formal attire and keep your voice low.");
    }

    public void visit(FastFood fastFood) {
        System.out.println("At " + fastFood.getName() + ", order at the counter and be casual.");
    }

    public void visit(JapaneseRestaurant japaneseRestaurant) {
        System.out.println("At " + japaneseRestaurant.getName() + ", use chopsticks and respect dining traditions.");
    }
}

// Element Interface (Restaurants)
interface Restaurant {
    void accept(EtiquetteVisitor visitor);
    String getName();
}

// Concrete Restaurant Classes
class FineDining implements Restaurant {
    private String name;
    public FineDining(String name) { this.name = name; }
    public String getName() { return name; }
    public void accept(EtiquetteVisitor visitor) { visitor.visit(this); }
}

class FastFood implements Restaurant {
    private String name;
    public FastFood(String name) { this.name = name; }
    public String getName() { return name; }
    public void accept(EtiquetteVisitor visitor) { visitor.visit(this); }
}

class JapaneseRestaurant implements Restaurant {
    private String name;
    public JapaneseRestaurant(String name) { this.name = name; }
    public String getName() { return name; }
    public void accept(EtiquetteVisitor visitor) { visitor.visit(this); }
}

// Usage
public class VisitorDemo {
    public static void main(String[] args) {
        Restaurant fineDining = new FineDining("Le Grand Restaurant");
        Restaurant fastFood = new FastFood("Burger Palace");
        Restaurant japanese = new JapaneseRestaurant("Sakura Sushi");

        EtiquetteVisitor visitor = new EtiquetteGuide();
        fineDining.accept(visitor);
        fastFood.accept(visitor);
        japanese.accept(visitor);
    }
}
