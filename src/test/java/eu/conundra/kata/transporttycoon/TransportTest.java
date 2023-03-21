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
        assertThat(new Simulation().solve("A")).isEqualTo(5);
    }

    @Test
    void testAA() {
        assertThat(new Simulation().solve("A", "A")).isEqualTo(1 + (4 + 4) + 4);
    }

    @Test
    void testABB() {
        assertThat(new Simulation().solve("A", "B", "B")).isEqualTo(7);
    }

    @Test
    void testAABABBAB() {
        assertThat(new Simulation().solve("A", "A", "B", "A", "B", "B", "A", "B")).isEqualTo(29);
    }
}
