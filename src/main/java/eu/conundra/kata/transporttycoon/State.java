package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.A;
import static eu.conundra.kata.transporttycoon.Destination.B;
import static eu.conundra.kata.transporttycoon.Destination.FACTORY;
import static eu.conundra.kata.transporttycoon.Destination.PORT;
import static eu.conundra.kata.transporttycoon.PackageMover.createShip;
import static eu.conundra.kata.transporttycoon.PackageMover.createTruck;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class State {
    private final List<PackageMover> packageMovers;
    private final Map<Destination, Location> locations;

    public State(List<Package> packagesAtFactory) {
        this(List.of(createTruck(), createTruck(), createShip()), packagesAtFactory);
    }

    public State(List<PackageMover> packageMovers, List<Package> packagesAtFactory) {
        this.packageMovers = new ArrayList<>(packageMovers);

        this.locations = Stream.of(
                new Location(FACTORY, new ArrayList<>(packagesAtFactory)),
                new Location(PORT, new ArrayList<>()),
                new Location(A, new ArrayList<>()),
                new Location(B, new ArrayList<>())
            )
            .collect(toMap(Location::name, identity()));
    }

    public void performStep() {
        loadPackages();
        step();
        unloadPackages();
    }

    private void loadPackages() {
        for (PackageMover packageMover : packageMovers) {
            if (packageMover.isIdle()) {
                if (packageMover.isAtLoadLocation()) {
                    Location location = locations.get(packageMover.loadLocation());
                    if (location.hasPackages()) {
                        Package pkg = location.pickupNext();
                        Destination nextStop = determineNextStop(location.name(), pkg.destination());
                        packageMover.load(pkg, nextStop);
                    }
                } else {
                    packageMover.driveBackToLoadLocation();
                }
            }
        }
    }

    private Destination determineNextStop(Destination currentLocation, Destination destination) {
        if (destination.equals(Destination.A)) {
            return currentLocation == FACTORY ? PORT : A;
        }
        if (destination.equals(Destination.B)) {
            return B;
        }

        throw new IllegalArgumentException("Not a valid package destination:" + destination);
    }

    private void step() {
        packageMovers.stream()
            .filter(mover -> !mover.isIdle())
            .forEach(PackageMover::step);
    }

    private void unloadPackages() {
        packageMovers.stream()
            .filter(PackageMover::containsPackage)
            .filter(PackageMover::isIdle)
            .forEach(mover -> {
                Package pkg = mover.unload();
                locations.get(mover.destination()).deliver(pkg);
            });
    }

    public boolean allPackagesDelivered() {
        long nonDelivered = locations.get(FACTORY).countPackages() + locations.get(PORT).countPackages() + packageMovers.stream()
            .filter(PackageMover::containsPackage)
            .count();

        return nonDelivered == 0;
    }

    public void report() {
        System.out.printf("Current state:%n");
        packageMovers.forEach(System.out::println);

        System.out.printf("#packages at factory :%d%n", locations.get(FACTORY).countPackages());
        System.out.printf("#packages at port    :%d%n", locations.get(PORT).countPackages());
        System.out.printf("#packages at A       :%d%n", locations.get(A).countPackages());
        System.out.printf("#packages at B       :%d%n", locations.get(B).countPackages());
    }

    public int packagesAtPort() {
        return locations.get(PORT).countPackages();
    }
}
