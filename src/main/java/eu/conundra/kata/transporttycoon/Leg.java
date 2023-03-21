package eu.conundra.kata.transporttycoon;

public class Leg {
    private final Warehouse origin;
    private final Warehouse destination;
    private final int time;
    private boolean finished;

    public Leg(Warehouse origin, Warehouse destination, int time) {
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.finished = false;
    }

    public boolean unfinished() {
        return !finished;
    }

    public void dropPackage() {

    }
}
