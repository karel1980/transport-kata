package eu.conundra.kata.transporttycoon;

import java.util.List;
import java.util.Optional;

public class Route {
    private final List<Leg> legs;

    public Route(Leg... legs) {
        this.legs = List.of(legs);
    }

    public void dropPackage() {
        currentLeg().ifPresent(Leg::dropPackage);
    }

    public int time() {
        return currentLeg().map(Leg::time).orElse(0);
    }

    private Optional<Leg> currentLeg() {
        return legs.stream().filter(Leg::unfinished).findFirst();
    }
}
