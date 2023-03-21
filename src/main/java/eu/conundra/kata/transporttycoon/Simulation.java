package eu.conundra.kata.transporttycoon;

import java.util.List;
import java.util.stream.Stream;

public class Simulation {
    private final Warehouse factory;
    private final Warehouse warehouseB;
    private final List<Vehicle> vehicles;

    public Simulation() {
        factory = new Warehouse();
        warehouseB = new Warehouse();

        vehicles = List.of(new Vehicle(factory), new Vehicle(factory));
    }

    private Route toRoute(String routeName) {
        return new Route(new Leg(factory, warehouseB, 5));
    }

    public int solve(String... packages) {
        factory.addPackages(Stream.of(packages).map(this::toRoute).toList());
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
            vehicle.dropPackage();
        }
    }

    private boolean done() {
        boolean trucksAreEmpty = vehicles.stream().allMatch(Vehicle::isEmpty);
        return factory.isEmpty() && trucksAreEmpty;
    }
}
