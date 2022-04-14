package io.cucumber.skeleton;

public class Belly {
    private int size;
    private int eaten;

    public void eat(int cukes) {
        eaten = cukes;
    }

    public void wait(int hours) {
        size = eaten;
    }

    public int getSize() {
        return size;
    }
}
