package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.FACTORY;
import static eu.conundra.kata.transporttycoon.Destination.PORT;

import java.util.List;

public class PackageMover {
    private List<Route> routes;
    private Destination loadLocation;
    private Destination targetLocation;
    private int timeToDestination;

    public PackageMover(Destination startLocation) {
        this(startLocation, 0);
    }

    public PackageMover(Destination startLocation, int timeToDestination) {
        this(startLocation, startLocation, timeToDestination);
    }

    public PackageMover(Destination startLocation, Destination loadLocation, int timeToDestination) {
        this.routes = World.WORLD.routesStartAt(FACTORY);
        this.targetLocation = startLocation;
        this.loadLocation = loadLocation;
        this.timeToDestination = timeToDestination;
    }

    public static PackageMover createTruck() {
        return new PackageMover(FACTORY);
    }

    public static PackageMover createShip() {
        return new PackageMover(PORT);
    }

    static PackageMover idleTruck(Destination startLocation) {
        return new PackageMover(startLocation, 0);
    }

    public Destination loadLocation() {
        return loadLocation;
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

    public boolean canUnload(Destination location) {
        return isIdle() && location == targetLocation && location != loadLocation;
    }

    void driveBackToLoadLocation() {
        setState(FACTORY, 5);
    }
}
