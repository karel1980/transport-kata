package eu.conundra.kata.transporttycoon;

public record Route(Destination source, Destination target, int length) {

    boolean matches(Destination from, Destination to) {
        return (source() == from && target() == to) || (source() == to && target() == from);
    }
}
