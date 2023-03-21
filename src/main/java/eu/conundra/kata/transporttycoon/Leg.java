package eu.conundra.kata.transporttycoon;

public class Leg {
    private final int distance;
    private boolean finished;

    public Leg(int distance) {
        this.distance = distance;
        this.finished = false;
    }

    public boolean unfinished() {
        return !finished;
    }

    public void dropPackage() {
        finished = true;
    }

    public int distance() {
        return this.distance;
    }
}
