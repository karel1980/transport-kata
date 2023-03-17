package eu.conundra.kata.transporttycoon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class StateTest {
    @Test
    void performStep() {
        List<Truck> trucks = List.of(new Truck(null, 0));
        State state = new State(trucks);

        state.performStep();

        assertThat(trucks)
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(new Truck(Destination.B, 4));
    }
}
