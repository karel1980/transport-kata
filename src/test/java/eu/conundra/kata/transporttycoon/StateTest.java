package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.B;
import static eu.conundra.kata.transporttycoon.Destination.FACTORY;
import static eu.conundra.kata.transporttycoon.Package.createPackages;
import static eu.conundra.kata.transporttycoon.PackageMover.createShip;
import static eu.conundra.kata.transporttycoon.PackageMover.createTruck;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class StateTest {

    @Test
    void performStep() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Package> producedPackages = createPackages("B");
        State state = new State(packageMovers, producedPackages);

        state.performStep();

        assertThat(packageMovers)
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(new PackageMover(B, FACTORY, 4, producedPackages.get(0)));
    }

    @Test
    void performStep_incrementsPackageCountWhenUnloading() {
        List<PackageMover> packageMovers = List.of(new PackageMover(FACTORY));
        State state = new State(packageMovers, createPackages("AAAA"));

        state.performStep();

        assertThat(state.packagesAtPort())
            .isEqualTo(1);
    }

    @Test
    void performStep_decrementsPackagesAtPort_whenShipLeaves() {
        List<PackageMover> packageMovers = List.of(
            createTruck(),
            createShip()
        );
        State state = new State(packageMovers, createPackages("A"));

        state.performStep();

        assertThat(state.packagesAtPort())
            .isEqualTo(1);

        state.performStep();

        assertThat(state.packagesAtPort())
            .isEqualTo(0);
    }

    @Test
    void twoTimesPerformStep() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Package> packages = createPackages("B");
        State state = new State(packageMovers, packages);

        state.performStep();
        state.performStep();

        assertThat(packageMovers)
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(new PackageMover(B, FACTORY, 3, packages.get(0)));
    }

    @Test
    void allPackagesDelivered() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Package> producedPackages = createPackages("");
        State state = new State(packageMovers, producedPackages);

        boolean allPackagesDelivered = state.allPackagesDelivered();

        assertThat(allPackagesDelivered).isTrue();
    }

    @Test
    void notAllPackagesDelivered_becausePackageStillAtFactory() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Package> producedPackages = createPackages("B");
        State state = new State(packageMovers, producedPackages);

        boolean allPackagesDelivered = state.allPackagesDelivered();

        assertThat(allPackagesDelivered).isFalse();
    }

    @Test
    void notAllPackagesDelivered_becausePackageStillUnderWay() {
        List<PackageMover> packageMovers = List.of(createTruck());
        List<Package> producedPackages = createPackages("A");
        State state = new State(packageMovers, producedPackages);

        boolean allPackagesDelivered = state.allPackagesDelivered();

        assertThat(allPackagesDelivered).isFalse();
    }
}
