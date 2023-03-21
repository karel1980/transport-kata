package eu.conundra.kata.transporttycoon;

import java.util.Queue;

public class Vehicle {
    private final Warehouse origin;
    private int position = 0;
    private Route route;

    public Vehicle(Warehouse origin) {
        this.origin = origin;
    }

    public boolean atOrigin() {
        return position == 0;
    }

    public boolean atDestination() {
        return position == 5;
    }

    public boolean isEmpty() {
        return route == null;
    }

    public void pickup() {
        if(atOrigin() && origin.hasPackage()) {
            this.route = origin.pickup();
        }
    }

    public void dropPackage(Queue<String> destination) {
        if (atDestination()) {
//            destination.add(route);
            route.dropPackage();
            route = null;
        }
    }

    public void move() {
        if (route != null) {
            driveForward();
        } else {
            driveBack();
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
            ", payload='" + route + '\'' +
            '}';
    }
}
