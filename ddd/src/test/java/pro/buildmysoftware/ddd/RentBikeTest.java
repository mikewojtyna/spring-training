package pro.buildmysoftware.ddd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.ddd.bike.rent.model.bikerack.Bike;
import pro.buildmysoftware.ddd.bike.rent.model.bikerack.BikeRack;
import pro.buildmysoftware.ddd.bike.rent.model.bikerack.BikeRent;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RentBikeTest {

	// @formatter:off
	@DisplayName(
		"rent single bike"
	)
	// @formatter:on
	@Test
	void rent0() throws Exception {
		// given
		BikeRack bikeRack = anyBikeRack();

		// when
		Optional<BikeRent> event = bikeRack.rent();

		// then
		assertThat(event).isPresent();
	}

	// @formatter:off
	@DisplayName(
		"given bike rack with single bike, " +
		"when rent bike from this rack, " +
		"then bike is rent and no more bikes are available in rack"
	)
	// @formatter:on
	@Test
	void rent1() throws Exception {
		// given
		Bike bike = anyBike();
		BikeRack bikeRack = bikeRackWithSingleBike(bike);

		// when
		Optional<BikeRent> event = bikeRack.rent();
		Optional<BikeRent> secondRentTry = bikeRack.rent();

		// then
		assertThat(event.get().getBike()).isEqualTo(bike);
		assertThat(secondRentTry).isEmpty();
	}

	private Bike anyBike() {
		return new Bike();
	}

	private BikeRack bikeRackWithSingleBike(Bike bike) {
		return new BikeRack(bike);
	}

	private BikeRack anyBikeRack() {
		return new BikeRack(new Bike());
	}
}
