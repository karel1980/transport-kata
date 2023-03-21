package eu.conundra.kata.transporttycoon;

public class Leg {
    private final int distance;
    private Warehouse destination;
    private boolean done;

    public Leg(int distance, Warehouse destination) {
        this.distance = distance;
        this.destination = destination;
    }

    public boolean unfinished() {
        return !done;
    }

    public void dropPackage() {
        done = true;
    }

    public int distance() {
        return this.distance;
    }

    public boolean isDone() {
        return done;
    }

    public Warehouse getDestination() {
        return destination;
    }
}
