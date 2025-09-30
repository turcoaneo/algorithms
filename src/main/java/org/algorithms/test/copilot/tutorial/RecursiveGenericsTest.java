package org.algorithms.test.copilot.tutorial;

class Animal<T extends Animal<T>> {
    public void mateWith(T other) {
        System.out.println(this.hashCode() + " mates with " + other.hashCode());
    }

    public void info() {
        System.out.println(this.getClass().getSimpleName());
    }
}

class Lion extends Animal<Lion> {
    // nothing extra needed
}

class Tiger extends Animal<Tiger> {
    // nothing extra needed
}

public class RecursiveGenericsTest {
    public static void main(String[] args) {
        Lion leo = new Lion();
        Lion nala = new Lion();
        nala.info();
        leo.mateWith(nala); // should print Lion mates with Lion

        Tiger tony = new Tiger(); // what happens here?
        tony.info();
        tony.mateWith(tony); // hermaphrodites
    }
}