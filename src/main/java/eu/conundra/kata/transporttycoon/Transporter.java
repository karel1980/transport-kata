package eu.conundra.kata.transporttycoon;

import java.util.List;
import java.util.stream.Collectors;

public class Transporter {
    public int solve(List<Package> packages) {
        State state = new State(packages.stream()
            .map(Package::destination)
            .collect(Collectors.toList()));
        int currentTime = 0;

        while (!state.allPackagesDelivered()) {
            state.performStep();
            currentTime++;

            if (currentTime > 1000) {
                throw new RuntimeException("This is taking too long");
            }
        }

        return currentTime;
    }
}
