package pro.buildmysoftware.ddd.bike.rent.api;

import pro.buildmysoftware.ddd.bike.rent.model.bikerack.BikeRack;
import pro.buildmysoftware.ddd.bike.rent.model.bikerack.BikeRackId;

import java.util.Optional;

public interface BikeRackRepository {

	Optional<BikeRack> load(BikeRackId id);

	void save(BikeRack bike);
}
