package eu.conundra.kata.transporttycoon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Warehouse {
    private final Queue<Route> packages = new LinkedList<>();

    public void addPackages(List<Route> routes) {
        packages.addAll(routes);
    }

    public boolean isEmpty() {
        return packages.isEmpty();
    }

    public Route pickup() {
        return packages.remove();
    }

    public boolean hasPackage() {
        return !packages.isEmpty();
    }
}
