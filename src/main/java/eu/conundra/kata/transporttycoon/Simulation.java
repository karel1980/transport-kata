package eu.conundra.kata.transporttycoon;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Simulation {
    private final Warehouse factory;
    private final Warehouse port;
    private final Warehouse warehouseA;
    private final Warehouse warehouseB;
    private final List<Vehicle> vehicles;
    private List<Route> routes;

    public Simulation() {
        factory = new Warehouse();
        port = new Warehouse();
        warehouseA = new Warehouse();
        warehouseB = new Warehouse();
        vehicles = List.of(new Vehicle(factory), new Vehicle(factory), new Vehicle(port));
    }

    public int solve(String... packages) {
        routes = Stream.of(packages).map(this::toRoute).toList();
        factory.addAll(routes);
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

    private Route toRoute(String routeName) {
        return switch (routeName) {
            case "A" -> new Route("A", new Leg(1, port), new Leg(4, warehouseA));
            case "B" -> new Route("B", new Leg(5, warehouseB));
            default -> throw new IllegalArgumentException(routeName);
        };
    }

    private boolean parcelsInFlight() {
        return !done();
    }

    private boolean done() {
        boolean trucksAreEmpty = vehicles.stream().allMatch(Vehicle::isEmpty);
        return factory.isEmpty() && trucksAreEmpty && routes.stream().allMatch(Route::isDone);
    }

    private void execute(Consumer<Vehicle> consumer) {
        vehicles.forEach(consumer);
    }
}
