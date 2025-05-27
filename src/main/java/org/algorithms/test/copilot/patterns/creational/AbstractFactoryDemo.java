package org.algorithms.test.copilot.patterns.creational;

// Common Interfaces
interface Button { void render(); }
interface Checkbox { void toggle(); }

// Concrete Implementations for Windows
class WindowsButton implements Button {
    public void render() { System.out.println("Rendering Windows Button"); }
}
class WindowsCheckbox implements Checkbox {
    public void toggle() { System.out.println("Toggling Windows Checkbox"); }
}

// Concrete Implementations for MacOS
class MacOSButton implements Button {
    public void render() { System.out.println("Rendering MacOS Button"); }
}
class MacOSCheckbox implements Checkbox {
    public void toggle() { System.out.println("Toggling MacOS Checkbox"); }
}

// Abstract Factory Interface
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factories for Each Platform
class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

class MacOSFactory implements GUIFactory {
    public Button createButton() { return new MacOSButton(); }
    public Checkbox createCheckbox() { return new MacOSCheckbox(); }
}

// 6️⃣ Client Code Using Abstract Factory
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GUIFactory factory = new WindowsFactory(); // Dynamically select OS factory
        Button btn = factory.createButton();
        Checkbox chk = factory.createCheckbox();

        btn.render(); // "Rendering Windows Button"
        chk.toggle(); // "Toggling Windows Checkbox"

        GUIFactory factory2 = new MacOSFactory(); // Dynamically select OS factory
        Button btn2 = factory2.createButton();
        Checkbox chk2 = factory2.createCheckbox();

        btn2.render(); // "Rendering Mac Button"
        chk2.toggle(); // "Toggling Mac Checkbox"
    }
}
