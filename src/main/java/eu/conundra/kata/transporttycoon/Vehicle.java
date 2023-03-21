package eu.conundra.kata.transporttycoon;

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
        if(route == null) return false;
        return position == route.distance();
    }

    public boolean isEmpty() {
        return route == null;
    }

    public void pickupPayload() {
        if(atOrigin() && origin.hasPayload()) {
            this.route = origin.pickup();
        }
    }

    public void dropPayload() {
        if (atDestination()) {
            route.dropPayload();
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
        position = Math.min(position + 1, route.distance());
    }

    private void driveBack() {
        position = Math.max(position - 1, 0);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "position=" + position +
            ", route='" + route + '\'' +
            '}';
    }
}
