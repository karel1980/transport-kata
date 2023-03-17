package eu.conundra.kata.transporttycoon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class StateTest {

    @Test
    void performStep() {
        List<Truck> trucks = List.of(new Truck(null, 0));
        List<Destination> producedPackages = List.of(Destination.B);
        State state = new State(trucks, producedPackages);

        state.performStep();

        assertThat(trucks)
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(new Truck(Destination.B, 4));
    }

    @Test
    void twoTimesPerformStep() {
        List<Truck> trucks = List.of(new Truck(null, 0));
        List<Destination> producedPackages = List.of(Destination.B);
        State state = new State(trucks, producedPackages);

        state.performmob nextStep();
        state.performStep();

        assertThat(trucks)
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(new Truck(Destination.B, 3));
    }

    @Test
    void allPackagesDelivered() {
        List<Truck> trucks = List.of(new Truck(null, 0));
        List<Destination> producedPackages = List.of();
        State state = new State(trucks, producedPackages);

        boolean allPackagesDelivered = state.allPackagesDelivered();

        assertThat(allPackagesDelivered).isTrue();
    }

    @Test
    void notAllPackagesDelivered_becausePackageStillAtFactory() {
        List<Truck> trucks = List.of(new Truck(null, 0));
        List<Destination> producedPackages = List.of(Destination.B);
        State state = new State(trucks, producedPackages);

        boolean allPackagesDelivered = state.allPackagesDelivered();

        assertThat(allPackagesDelivered).isFalse();
    }
    
    @Test
    void notAllPackagesDelivered_becausePackageStillUnderWay() {
        List<Truck> trucks = List.of(new Truck(Destination.B, 1));
        List<Destination> producedPackages = List.of();
        State state = new State(trucks, producedPackages);

        boolean allPackagesDelivered = state.allPackagesDelivered();

        assertThat(allPackagesDelivered).isFalse();
    }
}
