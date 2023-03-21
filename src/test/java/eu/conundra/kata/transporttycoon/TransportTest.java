package eu.conundra.kata.transporttycoon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TransportTest {
    @Test
    void testB() {
        Transporter transporter = new Transporter();
        assertThat(transporter.solve("B")).isEqualTo(5);
    }

    @Test
    void testBB() {
        Transporter transporter = new Transporter();
        assertThat(transporter.solve("B", "B")).isEqualTo(5);
    }

    @Test
    void testBBB() {
        Transporter transporter = new Transporter();
        assertThat(transporter.solve("B", "B", "B")).isEqualTo(15);
    }

    @Test
    void testA() {
        Transporter transporter = new Transporter();
        assertThat(transporter.solve("Q")).isEqualTo(5);
    }

    @Test
    void testAA() {
        Transporter transporter = new Transporter();
        assertThat(transporter.solve("A", "A")).isEqualTo(5);
    }
}
