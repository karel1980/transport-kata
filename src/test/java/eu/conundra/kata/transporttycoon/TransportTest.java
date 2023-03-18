package eu.conundra.kata.transporttycoon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TransportTest {

    Transporter transporter = new Transporter();

    @Test
    void testA() {
        int actual = solve("A");

        assertThat(actual)
            .isEqualTo(5);
    }

    @Test
    void testB() {
        int actual = solve("B");

        assertThat(actual)
            .isEqualTo(5);
    }

    @Test
    void testBB() {
        int actual = solve("BB");

        assertThat(actual)
            .isEqualTo(5);
    }

    @Test
    void testBBB() {
        int actual = solve("BBB");

        assertThat(actual)
            .isEqualTo(15);
    }

    @Test
    void testAB() {
        int actual = solve("AB");

        assertThat(actual)
            .isEqualTo(5);
    }

    @Test
    void testABB() {
        int actual = solve("ABB");

        assertThat(actual)
            .isEqualTo(7);
    }

    @Test
    void testAABABBAB() {
        int actual = solve("AABABBAB");

        assertThat(actual)
            .isEqualTo(29);
    }

    @Test
    void testABBBABAAABBB() {
        int actual = solve("ABBBABAAABBB");

        assertThat(actual)
            .isEqualTo(41);
    }

    private int solve(String spec) {
        return transporter.solve(Package.createPackages(spec));
    }
}
