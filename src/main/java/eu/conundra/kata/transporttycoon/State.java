package eu.conundra.kata.transporttycoon;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<PackageMover> packageMovers;
    private List<Destination> producedPackages;
    private final int totalPackages;
    private int packagesAtPort = 0;
    private int packagesAtA = 0;
    private int packagesAtB = 0;

    public State(List<PackageMover> packageMovers, List<Destination> producedPackages) {
        this.packageMovers = new ArrayList<>(packageMovers);
        this.producedPackages = new ArrayList<>(producedPackages);
        this.totalPackages = producedPackages.size();
    }

    public void performStep() {
        if (producedPackages.isEmpty()) {
            discardIdleTrucks();
        }
        loadAllTrucks();
        step();
        unloadPackages();
    }

    private void discardIdleTrucks() {
        packageMovers.removeIf(PackageMover::isIdle);
    }

    private void loadAllTrucks() {
        for (PackageMover packageMover : packageMovers) {
            if (packageMover.isIdle()) {
                if (packageMover.destination() == Destination.FACTORY) {
                    Destination nextProducedPackage = nextProducedPackage();
                    packageMover.setState(nextProducedPackage, 5);
                } else {
                    packageMover.setState(Destination.FACTORY, 5);
                }
            }
        }
    }

    private void step() {
        packageMovers.forEach(PackageMover::step);
    }

    private void unloadPackages() {
        packageMovers.stream()
            .filter(PackageMover::isIdle)
            .forEach(arrivedMover -> {
                if (arrivedMover.canUnload(Destination.A)) {
                    packagesAtA++;
                }
                if (arrivedMover.canUnload(Destination.B)) {
                    packagesAtB++;
                }
                if (arrivedMover.canUnload(Destination.PORT)) {
                    packagesAtPort++;
                }
            });
    }

    private Destination nextProducedPackage() {
        if (producedPackages.isEmpty()) {
            return null;
        }
        return producedPackages.remove(0);
    }

    public boolean allPackagesDelivered() {
        return packagesAtA + packagesAtB == totalPackages;
    }

    private boolean allTrucksIdle() {
        return packageMovers.stream().allMatch(PackageMover::isIdle);
    }

    public void report(int currentTime) {
        System.out.printf("at %d%n", currentTime);
        packageMovers.forEach(System.out::println);
    }
}
