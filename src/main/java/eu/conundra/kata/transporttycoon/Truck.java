package eu.conundra.kata.transporttycoon;

public class Truck {
    private final Destination destination;
    private final int timeToDestination;

    public Truck(Destination destination, int timeToDestination) {
        this.destination = destination;
        this.timeToDestination = timeToDestination;
    }
}
