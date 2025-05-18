package org.toptal.test.copilot.patterns.creational;

// 1️⃣ Common Interfaces
interface Button { void render(); }
interface Checkbox { void toggle(); }

// 2️⃣ Concrete Implementations for Windows
class WindowsButton implements Button {
    public void render() { System.out.println("Rendering Windows Button"); }
}
class WindowsCheckbox implements Checkbox {
    public void toggle() { System.out.println("Toggling Windows Checkbox"); }
}

// 3️⃣ Concrete Implementations for MacOS
class MacOSButton implements Button {
    public void render() { System.out.println("Rendering MacOS Button"); }
}
class MacOSCheckbox implements Checkbox {
    public void toggle() { System.out.println("Toggling MacOS Checkbox"); }
}

// 4️⃣ Abstract Factory Interface
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// 5️⃣ Concrete Factories for Each Platform
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

        btn.render(); // ✅ "Rendering Windows Button"
        chk.toggle(); // ✅ "Toggling Windows Checkbox"
    }
}
