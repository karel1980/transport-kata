package eu.conundra.kata.transporttycoon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Simulation {
    private final Queue<String> destination = new LinkedList<>();
    private final List<Vehicle> vehicles;
    private Route routeB;
    private Warehouse factory;

    public Simulation()
    {
        factory = new Warehouse();
        Warehouse b = new Warehouse();
        routeB = new Route(new Leg(factory, b, 5));

        vehicles = List.of(new Vehicle(factory), new Vehicle(factory));
    }

    public int solve(String... packages) {
        factory.addPackages(packages);
        var iterations = -1;
        do {
            if (iterations > 100) {
                throw new RuntimeException("Could not find a solution within 100 iterations");
            }
            iterations++;
            dropPackage();
            pickUpPackage();
            move();
        } while (!done());
        return iterations;
    }

    private void pickUpPackage() {
        for (Vehicle vehicle : vehicles) {
            vehicle.pickup();
        }
    }

    private void move() {
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
    }

    private void dropPackage() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.atDestination()) {
                vehicle.dropPackage(destination);
            }
        }
    }

    private boolean done() {
        boolean trucksAreEmpty = vehicles.stream().allMatch(Vehicle::isEmpty);
        return factory.isEmpty() && trucksAreEmpty;
    }
}
