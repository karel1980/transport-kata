package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.FACTORY;
import static eu.conundra.kata.transporttycoon.Destination.PORT;
import static eu.conundra.kata.transporttycoon.PackageMover.idleShip;
import static eu.conundra.kata.transporttycoon.PackageMover.idleTruck;

import java.util.List;

public class Transporter {
    private final List<Destination> goodToTransport;

    public Transporter(List<Destination> goodToTransport) {
        this.goodToTransport = goodToTransport;
    }

    public int solve() {
        State state = new State(
            List.of(idleTruck(FACTORY), idleTruck(FACTORY), idleShip(PORT)),
            goodToTransport
        );
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
