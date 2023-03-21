package eu.conundra.kata.transporttycoon;

import java.util.List;
import java.util.Optional;

public class Route {
    private final String name;
    private final List<Leg> legs;

    public Route(String name, Leg... legs) {
        this.name = name;
        this.legs = List.of(legs);
    }

    public void dropPayload() {
        currentLeg().ifPresent(leg -> {
            leg.getDestination().add(this);
            leg.dropPayload();
        });
    }

    public int distance() {
        return currentLeg().map(Leg::distance).orElse(0);
    }

    private Optional<Leg> currentLeg() {
        return legs.stream().filter(Leg::unfinished).findFirst();
    }

    public boolean isDone() {
        return legs.stream().allMatch(Leg::isDone);
    }

    @Override
    public String toString() {
        return "Route " + name;
    }
}
