package pro.buildmysoftware.ddd.bike.rent.api;

import pro.buildmysoftware.ddd.bike.common.DomainEvent;

public interface EventPublisher {

	void publishEvent(DomainEvent domainEvent);
}
