package eu.conundra.kata.transporttycoon;

public class Truck {
    private Destination destination;
    private int timeToDestination;

    public Truck(Destination destination, int timeToDestination) {
        this.destination = destination;
        this.timeToDestination = timeToDestination;
    }

    static Truck idleTruck() {
        return new Truck(null, 0);
    }

    public Destination destination() {
        return destination;
    }

    public int timeToDestination() {
        return timeToDestination;
    }

    public void setState(Destination destination, int timeToDestination) {
        this.destination = destination;
        this.timeToDestination = timeToDestination;
    }

    public void step() {
        timeToDestination--;
    }

    boolean isIdle() {
        return timeToDestination() == 0;
    }
}
