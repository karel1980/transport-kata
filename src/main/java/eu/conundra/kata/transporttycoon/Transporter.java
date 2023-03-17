package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.FACTORY;
import static eu.conundra.kata.transporttycoon.Truck.idleTruck;

import java.util.List;

public class Transporter {
    private final List<Destination> goodToTransport;

    public Transporter(List<Destination> goodToTransport) {
        this.goodToTransport = goodToTransport;
    }

    public int solve() {
        State state = new State(
            List.of(idleTruck(FACTORY), idleTruck(FACTORY)),
            goodToTransport
        );
        int currentTime = 0;

        while (!state.allPackagesDelivered()) {
            state.performStep();
            state.report(currentTime);
            currentTime++;

            if (currentTime > 1000) {
                throw new RuntimeException("This is taking too long");
            }
        }

        return currentTime;
    }
}
