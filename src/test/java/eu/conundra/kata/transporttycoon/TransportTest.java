package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.A;
import static eu.conundra.kata.transporttycoon.Destination.B;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class TransportTest {
    @Test
    void testA() {
        List<Destination> given = List.of(A);

        Transporter transporter = new Transporter(given);
        assertThat(transporter.solve()).isEqualTo(5);
    }

    @Test
    void testB() {
        List<Destination> given = List.of(B);

        Transporter transporter = new Transporter(given);
        assertThat(transporter.solve()).isEqualTo(5);
    }

    @Test
    void testBB() {
        List<Destination> given = List.of(B, B);

        Transporter transporter = new Transporter(given);
        assertThat(transporter.solve()).isEqualTo(5);
    }

    @Test
    void testBBB() {
        List<Destination> given = List.of(B, B, B);

        Transporter transporter = new Transporter(given);
        assertThat(transporter.solve()).isEqualTo(15);
    }

    @Test
    void testAB() {
        List<Destination> given = List.of(A, B);

        Transporter transporter = new Transporter(given);
        assertThat(transporter.solve()).isEqualTo(5);
    }

    @Test
    void testABB() {
        List<Destination> given = List.of(A, B, B);

        Transporter transporter = new Transporter(given);
        assertThat(transporter.solve()).isEqualTo(7);
    }
}
