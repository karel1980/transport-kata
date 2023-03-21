package eu.conundra.kata.transporttycoon;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Simulation {
    private final Warehouse factory;
    private final Warehouse port;
    private final List<Vehicle> vehicles;

    public Simulation() {
        factory = new Warehouse();
        port = new Warehouse();
        vehicles = List.of(new Vehicle(factory), new Vehicle(factory), new Vehicle(port));
    }

    private Route toRoute(String routeName) {
        return new Route(new Leg(5));
    }

    public int solve(String... packages) {
        factory.addPackages(Stream.of(packages).map(this::toRoute).toList());
        var iterations = -1;
        do {
            if (iterations > 100) throw new RuntimeException("Could not find a solution within 100 iterations");
            iterations++;
            execute(Vehicle::dropPackage);
            execute(Vehicle::pickup);
            execute(Vehicle::move);
        } while (parcelsInFlight());
        return iterations;
    }

    private boolean parcelsInFlight() {
        return !done();
    }

    private boolean done() {
        boolean trucksAreEmpty = vehicles.stream().allMatch(Vehicle::isEmpty);
        return factory.isEmpty() && trucksAreEmpty;
    }

    private void execute(Consumer<Vehicle> consumer) {
        vehicles.forEach(consumer);
    }
}
