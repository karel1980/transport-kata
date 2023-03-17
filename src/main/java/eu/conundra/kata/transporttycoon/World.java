package eu.conundra.kata.transporttycoon;

import java.util.List;

public class World {
    private List<Destination> places;
    private List<Route> routes;

    public static World WORLD = new World();

    private World() {
        this.places = List.of(Destination.values());
        this.routes = List.of(
            new Route(Destination.FACTORY, Destination.PORT, 1),
            new Route(Destination.PORT, Destination.A, 4),
            new Route(Destination.FACTORY, Destination.B, 5)
        );
    }

    public List<Route> routesStartAt(Destination destination) {
        return routes.stream()
            .filter(route -> route.source().equals(destination))
            .toList();
    }
}
