package eu.conundra.kata.transporttycoon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Warehouse {
    private Queue<String> packages;

    public void addPackages(String... packages) {
        this.packages = new LinkedList<>(List.of(packages));
    }

    public boolean isEmpty() {
        return packages.isEmpty();
    }

    public String pickup() {
        return packages.remove();
    }

    public boolean hasPackage() {
        return !packages.isEmpty();
    }
}
