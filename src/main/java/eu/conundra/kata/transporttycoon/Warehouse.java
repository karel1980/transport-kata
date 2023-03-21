package eu.conundra.kata.transporttycoon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Warehouse {
    private final Queue<Route> routes = new LinkedList<>();

    public void addAll(List<Route> routes) {
        this.routes.addAll(routes);
    }

    public void add(Route route) {
        routes.add(route);
    }

    public boolean isEmpty() {
        return routes.isEmpty();
    }

    public Route pickup() {
        return routes.remove();
    }

    public boolean hasPayload() {
        return !routes.isEmpty();
    }
}
