package org.toptal.test.copilot.patterns.behavioral;

// State Interface
interface RestaurantState {
    void handleRequest();
}

// Concrete States
class OpenState implements RestaurantState {
    public void handleRequest() {
        System.out.println("The restaurant is OPEN. Guests are welcome!");
    }
}

class BusyState implements RestaurantState {
    public void handleRequest() {
        System.out.println("The restaurant is BUSY. Please expect longer wait times.");
    }
}

class ClosedState implements RestaurantState {
    public void handleRequest() {
        System.out.println("The restaurant is CLOSED. No new guests allowed.");
    }
}

// Context: Restaurant
class RestaurantWithState {
    private RestaurantState state;

    public void setState(RestaurantState state) {
        this.state = state;
    }

    public void serveGuests() {
        state.handleRequest();
    }
}

// Usage
public class StatePatternDemo {
    public static void main(String[] args) {
        RestaurantWithState restaurant = new RestaurantWithState();

        // Transitioning through different states
        restaurant.setState(new OpenState());
        restaurant.serveGuests();

        restaurant.setState(new BusyState());
        restaurant.serveGuests();

        restaurant.setState(new ClosedState());
        restaurant.serveGuests();
    }
}
