package eu.conundra.kata.transporttycoon;

import static eu.conundra.kata.transporttycoon.Destination.A;
import static eu.conundra.kata.transporttycoon.Destination.B;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

class LocationTest {

    @Test
    void pickupDeliver_isFifo() {
        Location location = new Location(Destination.FACTORY, List.of());
        Package packageA = new Package(A);
        Package packageB = new Package(B);

        location.deliver(packageA);
        location.deliver(packageB);

        assertThat(location.pickupNext())
            .isEqualTo(packageA);
        assertThat(location.pickupNext())
            .isEqualTo(packageB);
    }

    @Test
    void pickup_throwsWhenEmpty() {
        Location location = new Location(Destination.FACTORY, List.of());

        Throwable thrown = catchThrowable(location::pickupNext);

        assertThat(thrown)
            .isInstanceOf(NoSuchElementException.class);
    }
}