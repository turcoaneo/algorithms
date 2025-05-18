package org.toptal.test.copilot.patterns.behavioral;

// 1️⃣ Base Handler Interface
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String issue);
}

// 2️⃣ Concrete Handlers
class Level1Support extends SupportHandler {
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("basic")) {
            System.out.println("✅ Level 1 Support: Handling basic issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue); // Forward request
        }
    }
}

class Level2Support extends SupportHandler {
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("technical")) {
            System.out.println("✅ Level 2 Support: Handling technical issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue); // Forward request
        }
    }
}

class Level3Support extends SupportHandler {
    public void handleRequest(String issue) {
        System.out.println("✅ Level 3 Support: Handling complex issue.");
    }
}

// 3️⃣ Client Code
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler level3 = new Level3Support();

        level1.setNextHandler(level2);
        level2.setNextHandler(level3);

        level1.handleRequest("basic");      // ✅ Handled by Level 1
        level1.handleRequest("technical");  // ✅ Forwarded to Level 2
        level1.handleRequest("complex");    // ✅ Forwarded to Level 3
    }
}