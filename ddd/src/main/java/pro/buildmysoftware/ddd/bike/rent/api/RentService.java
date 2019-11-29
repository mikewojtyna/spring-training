package pro.buildmysoftware.ddd.bike.rent.api;

import pro.buildmysoftware.ddd.bike.rent.model.bikerack.BikeRackId;
import pro.buildmysoftware.ddd.bike.rent.model.bikerack.BikeRent;
import pro.buildmysoftware.ddd.bike.rent.model.bikerack.Client;

import java.util.Optional;

public class RentService {

	private BikeRackRepository repository;
	private EventPublisher eventPublisher;

	public RentService(BikeRackRepository bikeRepository,
			   EventPublisher eventPublisher) {
		repository = bikeRepository;
		this.eventPublisher = eventPublisher;
	}

	public void rentBike(BikeRackId bikeId, Client client) {
		// 1. load aggregate
		repository.load(bikeId).ifPresent(bikeRack -> {
			// 2. invoke command on aggregate
			Optional<BikeRent> event = bikeRack.rent();
			// 3. update aggregate state
			repository.save(bikeRack);
			// 4. publish events to inform other systems about
			// the effect
			event.ifPresent(e -> eventPublisher.publishEvent(e));
		});
	}
}
