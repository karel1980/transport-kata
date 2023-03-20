package eu.conundra.kata.transporttycoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Transporter {
    private final Queue<String> factoryPackages;
    private final List<String> destination = new ArrayList<>();
    private final List<Truck> trucks = List.of(new Truck(), new Truck());


    public Transporter(List<String> factoryPackages) {
        this.factoryPackages = new LinkedList<>(factoryPackages);
    }

    public int solve() {
        var iterations = -1;
        do {
            if (iterations > 100) throw new RuntimeException("Could not find a solution within 100 iterations");
            iterations++;
            dropPackage();
            pickUpPackage();
            move();
        } while (!done());
        return iterations;
    }

    private void dropPackage() {
        for (Truck truck : trucks) {
            if (truck.atDestination()) {
                truck.dropPackage(destination);
            }
        }
    }

    private void pickUpPackage() {
        for (Truck truck : trucks) {
            if(truck.atStart()) truck.pickup(factoryPackages);
        }
    }

    private void move() {
        for (Truck truck : trucks) {
            truck.move();
        }
    }

    private boolean done() {
        boolean factoryIsEmpty = factoryPackages.isEmpty();
        boolean trucksAreEmpty = trucks.stream().allMatch(Truck::isEmpty);
        return factoryIsEmpty && trucksAreEmpty;
    }
}
