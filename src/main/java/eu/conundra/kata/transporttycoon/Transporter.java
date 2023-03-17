package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Truck.idleTruck;

import java.util.List;

public class Transporter {
    private final List<Destination> goodToTransport;

    public Transporter(List<Destination> goodToTransport) {
        this.goodToTransport = goodToTransport;
    }

    public int solve() {
        State state = new State(
            List.of(idleTruck(), idleTruck()),
            goodToTransport
        );
        int currentTime = 0;

        while (!state.allPackagesDelivered()) {

            state.performStep();

            currentTime++;
        }

        return currentTime;
    }

}
