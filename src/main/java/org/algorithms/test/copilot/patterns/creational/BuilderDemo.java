package org.algorithms.test.copilot.patterns.creational;

// Product: Pizza
class Pizza {
    private String size;
    private boolean extraCheese;
    private boolean pepperoni;
    private boolean mushrooms;

    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.extraCheese = builder.extraCheese;
        this.pepperoni = builder.pepperoni;
        this.mushrooms = builder.mushrooms;
    }

    @Override
    public String toString() {
        return "Pizza [" + size + ", Cheese: " + extraCheese + ", Pepperoni: " + pepperoni + ", Mushrooms: " + mushrooms + "]";
    }

    // Builder Class
    static class PizzaBuilder {
        private String size;
        private boolean extraCheese;
        private boolean pepperoni;
        private boolean mushrooms;

        public PizzaBuilder(String size) { this.size = size; }

        public PizzaBuilder addCheese() { this.extraCheese = true; return this; }
        public PizzaBuilder addPepperoni() { this.pepperoni = true; return this; }
        public PizzaBuilder addMushrooms() { this.mushrooms = true; return this; }

        public Pizza build() { return new Pizza(this); }
    }
}

// Usage
public class BuilderDemo {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.PizzaBuilder("Large")
                .addCheese()
                .addPepperoni()
                .build();

        System.out.println(pizza);
    }
}
