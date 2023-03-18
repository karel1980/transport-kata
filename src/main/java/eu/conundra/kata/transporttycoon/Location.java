package eu.conundra.kata.transporttycoon;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

public class Location {

    private Destination name;
    private Queue<Package> packages;

    public Location(Destination name, List<Package> packages) {
        this.name = Objects.requireNonNull(name);
        this.packages = new ArrayDeque<>(packages);
    }

    public boolean hasPackages() {
        return packages.isEmpty();
    }

    public Package pickupNext() {
        return Optional.ofNullable(packages.poll())
            .orElseThrow();
    }

    public void deliver(Package pkg) {
        packages.add(pkg);
    }
}
