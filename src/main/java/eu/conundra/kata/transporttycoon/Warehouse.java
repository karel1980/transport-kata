package eu.conundra.kata.transporttycoon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Warehouse {
    private Queue<Route> packages;

    public void addPackages(List<Route> routes) {
        this.packages = new LinkedList<>(routes);
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
