package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.FACTORY;
import static eu.conundra.kata.transporttycoon.Destination.PORT;

public class PackageMover {
    private final Destination loadLocation;
    private Destination destination;
    private int timeToDestination;
    private Package loadedPackage;

    public PackageMover(Destination loadLocation) {
        this(loadLocation, loadLocation, 0);
    }

    public PackageMover(Destination initialDestination, Destination loadLocation, int timeToDestination) {
        this(initialDestination, loadLocation, timeToDestination, null);
    }

    public PackageMover(Destination destination, Destination loadLocation, int timeToDestination, Package loadedPackage) {
        this.destination = destination;
        this.loadLocation = loadLocation;
        this.timeToDestination = timeToDestination;
        this.loadedPackage = loadedPackage;
    }

    public static PackageMover createTruck() {
        return new PackageMover(FACTORY);
    }

    public static PackageMover createShip() {
        return new PackageMover(PORT);
    }

    public Destination loadLocation() {
        return loadLocation;
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

    boolean containsPackage() {
        return loadedPackage != null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PackageMover{");
        sb.append("destination=").append(destination);
        sb.append(", timeToDestination=").append(timeToDestination);
        sb.append('}');
        return sb.toString();
    }

    void driveBackToLoadLocation() {
        setState(loadLocation, World.WORLD.distanceBetween(destination, loadLocation));
    }

    boolean isAtLoadLocation() {
        return destination() == loadLocation();
    }

    public void load(Package pkg, Destination nextStop) {
        this.loadedPackage = pkg;
        setState(nextStop, World.WORLD.distanceBetween(loadLocation, nextStop));
    }

    public Package unload() {
        if (loadedPackage == null) {
            throw new IllegalStateException("Cannot unload when there is no loaded package");
        }
        try {
            return loadedPackage;
        } finally {
            loadedPackage = null;
        }
    }
}
