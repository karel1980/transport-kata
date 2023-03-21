package eu.conundra.kata.transporttycoon;

import java.util.Queue;

public class Truck {
    private int position = 0;
    private String payload = "";

    public boolean atStart() {
        return position == 0;
    }

    public boolean atDestination() {
        return position == 5;
    }

    public boolean isEmpty() {
        return payload.equals("");
    }

    public void pickup(Queue<String> factoryPackages) {
        if (factoryPackages.isEmpty()) return;
        this.payload = factoryPackages.remove();
    }

    public void dropPackage(Queue<String> destination) {
        destination.add(payload);
        payload = "";
    }

    public void move() {
        if (payload.equals("")) {
            driveBack();
        } else {
            driveForward();
        }
    }

    private void driveForward() {
        position = Math.min(position + 1, 5);
    }

    private void driveBack() {
        position = Math.max(position - 1, 0);
    }

    @Override
    public String toString() {
        return "Truck{" +
            "position=" + position +
            ", payload='" + payload + '\'' +
            '}';
    }
}
