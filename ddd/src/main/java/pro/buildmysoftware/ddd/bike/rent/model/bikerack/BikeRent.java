package pro.buildmysoftware.ddd.bike.rent.model.bikerack;

import pro.buildmysoftware.ddd.bike.common.DomainEvent;

public class BikeRent extends DomainEvent {

	private Bike bike;

	public BikeRent(Bike bike) {
		this.bike = bike;
	}

	public Bike getBike() {
		return bike;
	}
}
