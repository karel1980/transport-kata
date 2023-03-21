package eu.conundra.kata.transporttycoon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Transporter {
    private final Queue<String> destination = new LinkedList<>();
    private final List<Truck> trucks = List.of(new Truck(), new Truck());


    public int solve(String... packages) {
        Queue<String> factoryPackages = new LinkedList<>(List.of(packages));
        var iterations = -1;
        do {
            if (iterations > 100) {
                throw new RuntimeException("Could not find a solution within 100 iterations");
            }
            iterations++;
            dropPackage();
            pickUpPackage(factoryPackages);
            move();
        } while (!done(factoryPackages));
        return iterations;
    }

    private void dropPackage() {
        for (Truck truck : trucks) {
            if (truck.atDestination()) {
                truck.dropPackage(destination);
            }
        }
    }

    private void pickUpPackage(Queue<String> factoryPackages) {
        for (Truck truck : trucks) {
            if(truck.atStart()) truck.pickup(factoryPackages);
        }
    }

    private void move() {
        for (Truck truck : trucks) {
            truck.move();
        }
    }

    private boolean done(Queue<String> factoryPackages) {
        boolean factoryIsEmpty = factoryPackages.isEmpty();
        boolean trucksAreEmpty = trucks.stream().allMatch(Truck::isEmpty);
        return factoryIsEmpty && trucksAreEmpty;
    }
}
