package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.A;
import static eu.conundra.kata.transporttycoon.Destination.B;
import static eu.conundra.kata.transporttycoon.Destination.FACTORY;
import static eu.conundra.kata.transporttycoon.Destination.PORT;
import static eu.conundra.kata.transporttycoon.PackageMover.idleShip;
import static eu.conundra.kata.transporttycoon.PackageMover.idleTruck;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<PackageMover> packageMovers;
    private List<Package> packagesAtFactory;
    private final int totalPackages;
    private int packagesAtPort = 0;
    private int packagesAtA = 0;
    private int packagesAtB = 0;
    private boolean debug = false;

    public State(List<Package> packagesAtFactory) {
        this(List.of(idleTruck(FACTORY), idleTruck(FACTORY), idleShip(PORT)), packagesAtFactory);
    }

    public State(List<PackageMover> packageMovers, List<Package> packagesAtFactory) {
        this.packageMovers = new ArrayList<>(packageMovers);
        this.packagesAtFactory = new ArrayList<>(packagesAtFactory);
        this.totalPackages = packagesAtFactory.size();
    }

    public void performStep() {
        loadAllTrucks();
        step();
        unloadPackages();

        if (debug) {
            report();
        }
    }

    private void loadAllTrucks() {
        for (PackageMover packageMover : packageMovers) {
            if (packageMover.isIdle()) {
                if (packageMover.destination() == packageMover.loadLocation()) {
                    if (packageMover.loadLocation() == FACTORY) {
                        // A truck is loaded by taking a package from the factory
                        Package nextProducedPackage = takeNextPackageFromFactory();
                        if (nextProducedPackage != null) {
                            // hardcoded information about the world
                            if (nextProducedPackage.destination() == A) {
                                packageMover.setState(PORT, 1);
                            } else {
                                packageMover.setState(B, 5);
                            }
                        }
                    } else {
                        // A ship is loaded by taking a package from the port
                        if (packagesAtPort > 0) {
                            // this is weird, we're hardcoding knowledge about the world (load at port == ship)
                            packageMover.setState(A, 4);
                            packagesAtPort--;
                        }
                    }
                } else {
                    // drive back to load location
                    packageMover.driveBackToLoadLocation();
                }
            }
        }
    }

    private void step() {
        packageMovers.stream()
            .filter(mover -> !mover.isIdle())
            .forEach(PackageMover::step);
    }

    private void unloadPackages() {
        packageMovers.stream()
            .filter(PackageMover::isIdle)
            .forEach(arrivedMover -> {
                if (arrivedMover.canUnload(A)) {
                    packagesAtA++;
                }
                if (arrivedMover.canUnload(B)) {
                    packagesAtB++;
                }
                if (arrivedMover.canUnload(PORT)) {
                    packagesAtPort++;
                }
            });
    }

    private Package takeNextPackageFromFactory() {
        if (packagesAtFactory.isEmpty()) {
            return null;
        }
        return packagesAtFactory.remove(0);
    }

    public boolean allPackagesDelivered() {
        return packagesAtA + packagesAtB == totalPackages;
    }

    public void report() {
        System.out.printf("Current state:%n");
        packageMovers.forEach(System.out::println);

        System.out.printf("#packages at port    :%d%n", packagesAtPort);
        System.out.printf("#packages at A       :%d%n", packagesAtA);
        System.out.printf("#packages at B       :%d%n", packagesAtB);
        System.out.printf("#packages at factory :%d%n", packagesAtFactory.size());
    }

    public int packagesAtPort() {
        return packagesAtPort;
    }
}
