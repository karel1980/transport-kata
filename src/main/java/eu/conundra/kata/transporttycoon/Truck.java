package eu.conundra.kata.transporttycoon;

public class Truck {
    private Destination destination;
    private int timeToDestination;

    public Truck(Destination destination, int timeToDestination) {
        this.destination = destination;
        this.timeToDestination = timeToDestination;
    }

    static Truck idleTruck(Destination startLocation) {
        return new Truck(startLocation, 0);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Truck{");
        sb.append("destination=").append(destination);
        sb.append(", timeToDestination=").append(timeToDestination);
        sb.append('}');
        return sb.toString();
    }
}
