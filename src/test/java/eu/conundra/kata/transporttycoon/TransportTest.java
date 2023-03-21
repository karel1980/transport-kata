package eu.conundra.kata.transporttycoon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class TransportTest {
    @Test
    void testA() {
        List<String> packages = List.of("A");

        Transporter transporter = new Transporter(packages);
        assertThat(transporter.solve()).isEqualTo(5);
    }

    @Test
    void testB() {
        List<String> packages = List.of("B");

        Transporter transporter = new Transporter(packages);
        assertThat(transporter.solve()).isEqualTo(5);
    }

    @Test
    void testBB() {
        List<String> packages = List.of("B", "B");

        Transporter transporter = new Transporter(packages);
        assertThat(transporter.solve()).isEqualTo(5);
    }

    @Test
    void testBBB() {
        List<String> packages = List.of("B", "B", "B");

        Transporter transporter = new Transporter(packages);
        assertThat(transporter.solve()).isEqualTo(15);
    }
}
