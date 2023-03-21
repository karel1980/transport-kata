package eu.conundra.kata.transporttycoon;

public class Leg {
    private final int time;
    private boolean finished;

    public Leg(int time) {
        this.time = time;
        this.finished = false;
    }

    public boolean unfinished() {
        return !finished;
    }

    public void dropPackage() {
        finished = true;
    }

    public int time() {
        return this.time;
    }
}
