package eu.conundra.kata.transporttycoon;

import java.util.List;

public class Transporter {
    public int solve(List<Package> packages) {
        State state = new State(packages);
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
