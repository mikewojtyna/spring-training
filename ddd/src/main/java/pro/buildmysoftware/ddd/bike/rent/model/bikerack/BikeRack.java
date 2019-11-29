package pro.buildmysoftware.ddd.bike.rent.model.bikerack;

import pro.buildmysoftware.ddd.bike.common.AggregateRoot;

import java.util.Optional;

@AggregateRoot
public class BikeRack {

	private Bike bike;

	public BikeRack(Bike bike) {
		this.bike = bike;
	}

	public Optional<BikeRent> rent() {
		if (bike == null) {
			return Optional.empty();
		}
		Optional<BikeRent> event = Optional.of(new BikeRent(bike));
		bike = null;
		return event;
	}
}
