package eu.conundra.kata.transporttycoon;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<Truck> trucks;
    private List<Destination> producedPackages;
    private int notArrived;

    public State(List<Truck> trucks, List<Destination> producedPackages) {
        this.trucks = new ArrayList<>(trucks);
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
        trucks.removeIf(Truck::isIdle);
    }

    private void loadAllTrucks() {
        for (Truck truck : trucks) {
            if (truck.isIdle()) {
                if (truck.destination() == Destination.FACTORY) {
                    Destination nextProducedPackage = nextProducedPackage();
                    truck.setState(nextProducedPackage, 5);
                } else {
                    truck.setState(Destination.FACTORY, 5);
                }
            }
        }
    }

    private void step() {
        notArrived -= trucks.stream().filter(t -> t.timeToDestination() == 1).count();
        trucks.forEach(Truck::step);
    }

    private Destination nextProducedPackage() {
        if (producedPackages.isEmpty()) {
            return null;
        }
        return producedPackages.remove(0);
    }

    public boolean allPackagesDelivered() {
        return producedPackages.isEmpty() && allTrucksIdle();
    }

    private boolean allTrucksIdle() {
        return trucks.stream().allMatch(Truck::isIdle);
    }

    public void report(int currentTime) {
        System.out.printf("at %d%n", currentTime);
        trucks.forEach(System.out::println);
    }
}
