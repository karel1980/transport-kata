package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.A;
import static eu.conundra.kata.transporttycoon.Destination.B;
import static eu.conundra.kata.transporttycoon.Destination.FACTORY;
import static eu.conundra.kata.transporttycoon.Destination.PORT;

import java.util.List;

public class World {
    private List<Route> routes;

    public static World WORLD = new World();

    private World() {
        this.routes = List.of(
            new Route(FACTORY, PORT, 1),
            new Route(PORT, A, 4),
            new Route(FACTORY, B, 5)
        );
    }

    public int distanceBetween(Destination from, Destination to) {
        return routes.stream()
            .filter(r -> r.matches(from, to))
            .map(Route::length)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No route found between %s and %s".formatted(from, to)));
    }

    public List<Route> routesStartAt(Destination destination) {
        return routes.stream()
            .filter(route -> route.source().equals(destination))
            .toList();
    }
}
