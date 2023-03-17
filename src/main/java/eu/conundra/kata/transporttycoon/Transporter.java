package eu.conundra.kata.transporttycoon;

import java.util.List;

public class Transporter {
    private final List<String> goodToTransport;

    public Transporter(List<String> goodToTransport) {
        this.goodToTransport = goodToTransport;
    }

    public int solve() {
        int currentTime = 0;

        while (notAllGoodsDelivered()) {

            performNextStep();

            currentTime++;
        }

        return currentTime;
    }

    private void performNextStep() {

    }

    private boolean notAllGoodsDelivered() {
        return false;
    }
}
