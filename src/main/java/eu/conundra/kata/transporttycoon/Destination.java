package eu.conundra.kata.transporttycoon;

public record Destination(String name) {
    public static final Destination FACTORY = new Destination("FACTORY");
    public static final Destination PORT = new Destination("PORT");
    public static final Destination A = new Destination("A");
    public static final Destination B = new Destination("B");
}
