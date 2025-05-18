package org.toptal.test.copilot.patterns.structural;

// 1️⃣ Complex subsystem classes
class Projector { void turnOn() { System.out.println("Projector ON"); } }
class SoundSystem { void setSurroundSound() { System.out.println("Surround Sound ON"); } }
class DVDPlayer { void playMovie() { System.out.println("Playing Movie"); } }
class Lights { void dimLights() { System.out.println("Lights Dimmed"); } }

// 2️⃣ Façade class simplifying operations
class HomeTheaterFacade {
    private Projector projector;
    private SoundSystem soundSystem;
    private DVDPlayer dvdPlayer;
    private Lights lights;

    public HomeTheaterFacade() {
        this.projector = new Projector();
        this.soundSystem = new SoundSystem();
        this.dvdPlayer = new DVDPlayer();
        this.lights = new Lights();
    }

    public void watchMovie() {
        System.out.println("Starting Movie...");
        projector.turnOn();
        soundSystem.setSurroundSound();
        dvdPlayer.playMovie();
        lights.dimLights();
        System.out.println("Enjoy your movie! 🎬");
    }
}

// 3️⃣ Client Code
public class FacadePatternDemo {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        homeTheater.watchMovie(); // ✅ Simplified movie setup
    }
}
