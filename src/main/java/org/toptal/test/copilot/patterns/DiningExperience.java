package org.toptal.test.copilot.patterns;

public class DiningExperience {
    public static void main(String[] args) {
        // Setup Diner with Payment Strategy
        Diner diner = new Diner();
        // Create Restaurants
        Restaurant hotel = new HotelRestaurant("Sapphire Suites");
        Restaurant fastFood = new FastFoodRestaurant("Burger Palace");
        Restaurant posh = new PoshRestaurant("Le Grand Gourmet");

        // Setup Visitor
        RestaurantVisitor etiquetteVisitor = new EtiquetteGuide();
        hotel.accept(etiquetteVisitor);
        fastFood.accept(etiquetteVisitor);
        posh.accept(etiquetteVisitor);

        System.out.println("Posh evening");

        // Setup Chain of Responsibility for Ordering wine, main course, dessert
        OrderHandler drinksHandler = new WineOrderHandler();
        OrderHandler mainCourseHandler = new MainCourseOrderHandler();
        OrderHandler dessertHandler = new DessertOrderHandler();
        drinksHandler.setNextHandler(mainCourseHandler);
        mainCourseHandler.setNextHandler(dessertHandler);

        // Delegate orders
        drinksHandler.order(posh, "wine");
        drinksHandler.order(posh, "main course");
        drinksHandler.order(posh, "dessert");

        diner.setPaymentStrategy(new OpenAccountPayment());
        diner.payBill(posh, 300);

        System.out.println("Hotel evening");

        // Setup Chain of Responsibility for Ordering dessert, main course, wine
        drinksHandler = new WineOrderHandler();
        mainCourseHandler = new MainCourseOrderHandler();
        dessertHandler = new DessertOrderHandler();
        dessertHandler.setNextHandler(mainCourseHandler);
        mainCourseHandler.setNextHandler(drinksHandler);

        // Delegate orders
        dessertHandler.order(hotel, "dessert");
        dessertHandler.order(hotel, "main course");
        dessertHandler.order(hotel, "wine");

        diner.setPaymentStrategy(new CardPayment());
        diner.payBill(hotel, 150);

        System.out.println("Fast food evening");

        // Setup Chain of Responsibility for Ordering main course, wine, dessert
        drinksHandler = new BeerOrderHandler();
        mainCourseHandler = new MainCourseOrderHandler();
        dessertHandler = new DessertOrderHandler();
        mainCourseHandler.setNextHandler(drinksHandler);
        drinksHandler.setNextHandler(dessertHandler);

        // Delegate orders
        mainCourseHandler.order(fastFood, "main course");
        mainCourseHandler.order(fastFood, "beer");
        mainCourseHandler.order(fastFood, "dessert");

        diner.setPaymentStrategy(new CashPayment());
        diner.payBill(fastFood, 20);
    }
}

// Visitor Interface
interface RestaurantVisitor {
    void visit(HotelRestaurant hotel);
    void visit(FastFoodRestaurant fastFood);
    void visit(PoshRestaurant posh);
}

// Concrete Visitor (Etiquette Guide)
class EtiquetteGuide implements RestaurantVisitor {
    public void visit(HotelRestaurant hotel) {
        System.out.println("At " + hotel.getName() + ", the staff expects elegant behavior and quiet conversation.");
    }

    public void visit(FastFoodRestaurant fastFood) {
        System.out.println("At " + fastFood.getName() + ", quick self-service and casual eating are expected.");
    }

    public void visit(PoshRestaurant posh) {
        System.out.println("At " + posh.getName() + ", formal dress code and fine dining etiquette apply.");
    }
}

// Restaurant Interface
interface Restaurant {
    void accept(RestaurantVisitor visitor);
    String getName();
}

// Concrete Restaurants
class HotelRestaurant implements Restaurant {
    private final String name;
    public HotelRestaurant(String name) { this.name = name; }
    public String getName() { return name; }
    public void accept(RestaurantVisitor visitor) { visitor.visit(this); }
}

class FastFoodRestaurant implements Restaurant {
    private final String name;
    public FastFoodRestaurant(String name) { this.name = name; }
    public String getName() { return name; }
    public void accept(RestaurantVisitor visitor) { visitor.visit(this); }
}

class PoshRestaurant implements Restaurant {
    private final String name;
    public PoshRestaurant(String name) { this.name = name; }
    public String getName() { return name; }
    public void accept(RestaurantVisitor visitor) { visitor.visit(this); }
}

// Strategy Interface
interface PaymentStrategy {
    void pay(Restaurant restaurant, double amount);
}

// Concrete Strategies
class CashPayment implements PaymentStrategy {
    public void pay(Restaurant restaurant, double amount) {
        System.out.println("Paid $" + amount + " in cash at " + restaurant.getName() + ".");
    }
}

class CardPayment implements PaymentStrategy {
    public void pay(Restaurant restaurant, double amount) {
        System.out.println("Paid $" + amount + " using credit card at " + restaurant.getName() + ".");
    }
}

class OpenAccountPayment implements PaymentStrategy {
    public void pay(Restaurant restaurant, double amount) {
        System.out.println("Charged $" + amount + " to the hotel's open account at " + restaurant.getName() + ".");
    }
}

// Context (Diner's Payment Handler)
class Diner {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void payBill(Restaurant restaurant, double amount) {
        paymentStrategy.pay(restaurant, amount);
    }
}

// Handler Interface
abstract class OrderHandler {
    protected OrderHandler nextHandler;
    public void setNextHandler(OrderHandler nextHandler) { this.nextHandler = nextHandler; }
    public abstract void order(Restaurant restaurant, String item);
}

// Concrete Handlers
class WineOrderHandler extends OrderHandler {
    public void order(Restaurant restaurant, String item) {
        if (item.equalsIgnoreCase("wine")) {
            System.out.println("Ordered wine at " + restaurant.getName() + ".");
        } else if (nextHandler != null) {
            nextHandler.order(restaurant, item);
        }
    }
}

// Concrete Handlers
class BeerOrderHandler extends OrderHandler {
    public void order(Restaurant restaurant, String item) {
        if (item.equalsIgnoreCase("beer")) {
            System.out.println("Ordered beer at " + restaurant.getName() + ".");
        } else if (nextHandler != null) {
            nextHandler.order(restaurant, item);
        }
    }
}

class MainCourseOrderHandler extends OrderHandler {
    public void order(Restaurant restaurant, String item) {
        if (item.equalsIgnoreCase("main course")) {
            System.out.println("Ordered main course at " + restaurant.getName() + ".");
        } else if (nextHandler != null) {
            nextHandler.order(restaurant, item);
        }
    }
}

class DessertOrderHandler extends OrderHandler {
    public void order(Restaurant restaurant, String item) {
        if (item.equalsIgnoreCase("dessert")) {
            System.out.println("Ordered dessert at " + restaurant.getName() + ".");
        } else if (nextHandler != null) {
            nextHandler.order(restaurant, item);
        }
    }
}
