package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.B;
import static eu.conundra.kata.transporttycoon.Destination.FACTORY;
import static eu.conundra.kata.transporttycoon.PackageMover.createTruck;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class StateTest {

    @Test
    void performStep() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Destination> producedPackages = List.of(B);
        State state = new State(packageMovers, producedPackages);

        state.performStep();

        assertThat(packageMovers)
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(new PackageMover(B, 4));
    }

    @Test
    void twoTimesPerformStep() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Destination> producedPackages = List.of(B);
        State state = new State(packageMovers, producedPackages);

        state.performStep();
        state.performStep();

        assertThat(packageMovers)
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(new PackageMover(B, 3));
    }

    @Test
    void allPackagesDelivered() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Destination> producedPackages = List.of();
        State state = new State(packageMovers, producedPackages);

        boolean allPackagesDelivered = state.allPackagesDelivered();

        assertThat(allPackagesDelivered).isTrue();
    }

    @Test
    void notAllPackagesDelivered_becausePackageStillAtFactory() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Destination> producedPackages = List.of(B);
        State state = new State(packageMovers, producedPackages);

        boolean allPackagesDelivered = state.allPackagesDelivered();

        assertThat(allPackagesDelivered).isFalse();
    }

    @Test
    void notAllPackagesDelivered_becausePackageStillUnderWay() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Destination> producedPackages = List.of(Destination.A);
        State state = new State(packageMovers, producedPackages);

        boolean allPackagesDelivered = state.allPackagesDelivered();

        assertThat(allPackagesDelivered).isFalse();
    }
}
