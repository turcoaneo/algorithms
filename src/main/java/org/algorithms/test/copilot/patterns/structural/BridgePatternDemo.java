package org.algorithms.test.copilot.patterns.structural;

// 1️⃣ Implementor Interface (Defines Device Behavior)
interface Device {
    void powerOn();
    void powerOff();
}

// 2️⃣ Concrete Implementations of Device
class TV implements Device {
    public void powerOn() { System.out.println("📺 TV is ON"); }
    public void powerOff() { System.out.println("📺 TV is OFF"); }
}

class Radio implements Device {
    public void powerOn() { System.out.println("📻 Radio is ON"); }
    public void powerOff() { System.out.println("📻 Radio is OFF"); }
}

// 3️⃣ Abstraction Class (Remote Control)
abstract class RemoteControl {
    protected Device device; // Bridge to a device

    public RemoteControl(Device device) { this.device = device; }

    abstract void turnOn();
    abstract void turnOff();
}

// 4️⃣ Concrete Remote Implementation
class BasicRemote extends RemoteControl {
    public BasicRemote(Device device) { super(device); }

    public void turnOn() { device.powerOn(); }
    public void turnOff() { device.powerOff(); }
}

// 5️⃣ Client Code
public class BridgePatternDemo {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remote = new BasicRemote(tv);

        remote.turnOn();  // ✅ "📺 TV is ON"
        remote.turnOff(); // ✅ "📺 TV is OFF"

        Device radio = new Radio();
        remote = new BasicRemote(radio);

        remote.turnOn();  // ✅ "📻 Radio is ON"
        remote.turnOff(); // ✅ "📻 Radio is OFF"
    }
}
