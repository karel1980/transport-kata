package eu.conundra.kata.transporttycoon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TransportTest {
    @Test
    void testB() {
        assertThat(new Simulation().solve("B")).isEqualTo(5);
    }

    @Test
    void testBB() {
        assertThat(new Simulation().solve("B", "B")).isEqualTo(5);
    }

    @Test
    void testBBB() {
        assertThat(new Simulation().solve("B", "B", "B")).isEqualTo(15);
    }

    @Test
    void testA() {
        assertThat(new Simulation().solve("Q")).isEqualTo(5);
    }

    @Test
    void testAA() {
        assertThat(new Simulation().solve("A", "A")).isEqualTo(5);
    }
}
