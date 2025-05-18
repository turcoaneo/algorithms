package org.algorithms.test.copilot.patterns.structural;

// Component Interface
interface Burger {
    String getDescription();
}

// Concrete Component
class BasicBurger implements Burger {
    public String getDescription() {
        return "Burger";
    }
}

// Decorator Abstract Class
abstract class BurgerDecorator implements Burger {
    protected Burger burger;

    public BurgerDecorator(Burger burger) {
        this.burger = burger;
    }

    public String getDescription() {
        return burger.getDescription();
    }
}

// Concrete Decorators
class PaperWrapper extends BurgerDecorator {
    public PaperWrapper(Burger burger) { super(burger); }
    public String getDescription() {
        return burger.getDescription() + " wrapped in paper";
    }
}

class BoxPackage extends BurgerDecorator {
    public BoxPackage(Burger burger) { super(burger); }
    public String getDescription() {
        return burger.getDescription() + " placed in a box";
    }
}

class RibbonDecorator extends BurgerDecorator {
    public RibbonDecorator(Burger burger) { super(burger); }
    public String getDescription() {
        return burger.getDescription() + " with a ribbon";
    }
}

class FlyerDecorator extends BurgerDecorator {
    public FlyerDecorator(Burger burger) { super(burger); }
    public String getDescription() {
        return burger.getDescription() + " with a promotional flyer";
    }
}

// Usage
public class DecoratorDemo {
    public static void main(String[] args) {
        Burger burger = new BasicBurger();
        burger = new PaperWrapper(burger);
        burger = new BoxPackage(burger);
        burger = new RibbonDecorator(burger);
        burger = new FlyerDecorator(burger);

        System.out.println(burger.getDescription());
    }
}
