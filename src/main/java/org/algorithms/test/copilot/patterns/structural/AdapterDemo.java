package org.algorithms.test.copilot.patterns.structural;

// Legacy Class: Basic Ski Goggles with Dynamic Lens Switching
class BasicGoggles {
    public void removeLens() {
        System.out.println("Removing old lens.");
    }

    public void useLens(String lensType) {
        System.out.println("Applying " + lensType + " lens.");
    }
}

// Adaptive Lens Interface
interface AdaptiveLens {
    void applyLens(BasicGoggles goggles);
}

// Concrete Lens Adapters with Realistic Behavior
class SunnyLensAdapter implements AdaptiveLens {
    public void applyLens(BasicGoggles goggles) {
        goggles.removeLens();
        goggles.useLens("polarized (for sunny conditions)");
    }
}

class SnowyLensAdapter implements AdaptiveLens {
    public void applyLens(BasicGoggles goggles) {
        goggles.removeLens();
        goggles.useLens("high-contrast (for snowy terrain)");
    }
}

class FoggyLensAdapter implements AdaptiveLens {
    public void applyLens(BasicGoggles goggles) {
        goggles.removeLens();
        goggles.useLens("anti-fog (for misty conditions)");
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        BasicGoggles goggles = new BasicGoggles();

        System.out.println("\nSunny Day:");
        AdaptiveLens sunnyLens = new SunnyLensAdapter();
        sunnyLens.applyLens(goggles);

        System.out.println("\nSnowy Day:");
        AdaptiveLens snowyLens = new SnowyLensAdapter();
        snowyLens.applyLens(goggles);

        System.out.println("\nFoggy Day:");
        AdaptiveLens foggyLens = new FoggyLensAdapter();
        foggyLens.applyLens(goggles);
    }
}