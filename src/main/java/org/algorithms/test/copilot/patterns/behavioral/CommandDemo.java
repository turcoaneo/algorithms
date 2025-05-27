package org.algorithms.test.copilot.patterns.behavioral;

import java.util.HashMap;
import java.util.Map;

// Command Interface
interface TransferCommand {
    void execute();
    void rollback();  // Allows reversing if recipient is unavailable
}

// Account Class (For Money Transactions)
class Account {
    private final String name;
    private double balance;
    private final boolean available;

    public Account(String name, double balance, boolean available) {
        this.name = name;
        this.balance = balance;
        this.available = available;
    }

    public String getName() { return name; }
    public boolean isAvailable() { return available; }

    public void withdraw(double amount) { balance -= amount; }
    public void deposit(double amount) { balance += amount; }
}

// Storage Class (For Material Transactions)
class Storage {
    private final Map<String, Integer> inventory = new HashMap<>();

    public void addMaterial(String material, int quantity) {
        inventory.put(material, inventory.getOrDefault(material, 0) + quantity);
    }

    public void removeMaterial(String material, int quantity) {
        inventory.put(material, inventory.getOrDefault(material, 0) - quantity);
    }
}

// Recipient Class (For Material Transactions)
record Recipient(String name, boolean available) {

    public void receiveMaterial(String material, int quantity) {
        System.out.println(name + " received " + quantity + " units of " + material);
    }
}

// Concrete Command: Money Transfer
class MoneyTransferCommand implements TransferCommand {
    private final Account sender;
    private final Account receiver;
    private final double amount;

    public MoneyTransferCommand(Account sender, Account receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public void execute() {
        if (receiver.isAvailable()) {
            sender.withdraw(amount);
            receiver.deposit(amount);
            System.out.println("Money Transfer Successful: $" + amount + " sent from " + sender.getName() + " to " + receiver.getName());
        } else {
            rollback();
        }
    }

    @Override
    public void rollback() {
        System.out.println(" Recipient unavailable. Rolling back transaction...");
    }
}

// Concrete Command: Material Transfer
class MaterialTransferCommand implements TransferCommand {
    private final Storage warehouse;
    private final String material;
    private final int quantity;
    private final Recipient receiver;

    public MaterialTransferCommand(Storage warehouse, Recipient receiver, String material, int quantity) {
        this.warehouse = warehouse;
        this.receiver = receiver;
        this.material = material;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        if (receiver.available()) {
            warehouse.removeMaterial(material, quantity);
            receiver.receiveMaterial(material, quantity);
            System.out.println("Material Transfer Successful: " + quantity + " units of " + material + " sent to " + receiver.name());
        } else {
            rollback();
        }
    }

    @Override
    public void rollback() {
        System.out.println("Recipient unavailable. Rolling back material transfer...");
    }
}

// Main: Transaction Simulation
public class CommandDemo {
    public static void main(String[] args) {
        // Create accounts
        Account sender = new Account("Ovidiu", 1000, true);
        Account receiver = new Account("Robert Lewandowski", 500, false); // Unavailable receiver

        // Create warehouse storage
        Storage warehouse = new Storage();
        warehouse.addMaterial("Ski Gear", 10);

        // Create recipient
        Recipient absentRecipient = new Recipient("Mikaela Shiffrin", false);
        Recipient presentRecipient = new Recipient("Marco Odermatt", true);

        // Money transfer (Receiver is unavailable, so rollback)
        TransferCommand moneyTransfer = new MoneyTransferCommand(sender, receiver, 300);
        moneyTransfer.execute();

        // Material transfer (Recipient is available, so success)
        TransferCommand materialTransfer = new MaterialTransferCommand(warehouse, presentRecipient, "Ski Gear", 5);
        materialTransfer.execute();

        // Material transfer (Recipient is unavailable, so rollback)
        TransferCommand failedMaterialTransfer = new MaterialTransferCommand(warehouse, absentRecipient, "Ski Gear", 3);
        failedMaterialTransfer.execute();
    }
}
