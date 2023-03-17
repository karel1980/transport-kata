package eu.conundra.kata.transporttycoon;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<PackageMover> packageMovers;
    private List<Destination> producedPackages;
    private int notArrived;

    public State(List<PackageMover> packageMovers, List<Destination> producedPackages) {
        this.packageMovers = new ArrayList<>(packageMovers);
        this.producedPackages = new ArrayList<>(producedPackages);
        this.notArrived = this.producedPackages.size();
    }

    public void performStep() {
        if (producedPackages.isEmpty()) {
            discardIdleTrucks();
        }
        loadAllTrucks();
        step();
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
        notArrived -= packageMovers.stream().filter(t -> t.timeToDestination() == 1).count();
        packageMovers.forEach(PackageMover::step);
    }

    private Destination nextProducedPackage() {
        if (producedPackages.isEmpty()) {
            return null;
        }
        return producedPackages.remove(0);
    }

    public boolean allPackagesDelivered() {
        return notArrived == 0;
    }

    private boolean allTrucksIdle() {
        return packageMovers.stream().allMatch(PackageMover::isIdle);
    }

    public void report(int currentTime) {
        System.out.printf("at %d%n", currentTime);
        packageMovers.forEach(System.out::println);
    }
}
