package eu.conundra.kata.transporttycoon;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<Truck> trucks;
    private List<Destination> producedPackages;

    public State(List<Truck> trucks, List<Destination> producedPackages) {
        this.trucks = new ArrayList<>(trucks);
        this.producedPackages = new ArrayList<>(producedPackages);
    }

    public void performStep() {
        loadAllTrucks();
        step();
    }

    private void loadAllTrucks() {
        for (Truck truck : trucks) {
            if (truck.isIdle()) {
                Destination nextProducedPackage = nextProducedPackage();
                if (nextProducedPackage != null) {
                    truck.setState(nextProducedPackage, 5);
                }
            }
        }
    }

    private void step() {
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
}
