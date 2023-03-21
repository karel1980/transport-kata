package eu.conundra.kata.transporttycoon;

import java.util.List;

public class Route {
    private final List<Leg> legs;

    public Route(Leg... legs) {
        this.legs = List.of(legs);
    }

    public void dropPackage() {
        legs.stream().filter(Leg::unfinished).findFirst().ifPresent(Leg::dropPackage);
    }
}
