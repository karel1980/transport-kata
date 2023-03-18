package eu.conundra.kata.transporttycoon;

import java.util.List;
import java.util.stream.Stream;

public record Package(
    Destination destination
) {
    static List<Package> createPackages(String spec) {
        return Stream.of(spec.split(""))
            .filter(s -> !s.isEmpty())
            .map(Destination::new)
            .map(Package::new)
            .toList();
    }
}
