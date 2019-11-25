package pro.buildmysoftware.spring.core.di;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {

}
