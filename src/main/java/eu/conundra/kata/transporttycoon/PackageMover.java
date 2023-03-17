package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.FACTORY;

import java.util.List;

public class PackageMover {
    private List<Route> routes;
    private Destination targetLocation;
    private int timeToDestination;

    public PackageMover(Destination startLocation) {
        this(startLocation, 0);
    }

    public PackageMover(Destination startLocation, int timeToDestination) {
        this.routes = World.WORLD.routesStartAt(FACTORY);
        this.targetLocation = startLocation;
        this.timeToDestination = timeToDestination;
    }

    public static PackageMover createTruck() {
        return new PackageMover(FACTORY);
    }

    static PackageMover idleTruck(Destination startLocation) {
        return new PackageMover(startLocation, 0);
    }

    public Destination destination() {
        return targetLocation;
    }

    public int timeToDestination() {
        return timeToDestination;
    }

    public void setState(Destination destination, int timeToDestination) {
        this.targetLocation = destination;
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
        sb.append("destination=").append(targetLocation);
        sb.append(", timeToDestination=").append(timeToDestination);
        sb.append('}');
        return sb.toString();
    }
}
