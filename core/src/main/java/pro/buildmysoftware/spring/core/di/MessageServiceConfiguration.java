package pro.buildmysoftware.spring.core.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.UUID;

@Configuration
public class MessageServiceConfiguration {

	@Bean(name = "inMem")
	public MessageService messageService() {
		return new MessageService(inMemory());
	}

	@Bean
	public MessageRepository messageRepo() {
		return defaultImpl();
	}

	@Bean
	public MessageService defaultMsgService(MessageRepository repo) {
		return new MessageService(repo);
	}

	private MessageRepository defaultImpl() {
		return null;
	}

	private MessageRepository inMemory() {
		return new MessageRepository() {
			@Override
			public <S extends Message> S save(S s) {
				return null;
			}

			@Override
			public <S extends Message> Iterable<S> saveAll(Iterable<S> iterable) {
				return null;
			}

			@Override
			public Optional<Message> findById(UUID uuid) {
				return Optional.empty();
			}

			@Override
			public boolean existsById(UUID uuid) {
				return false;
			}

			@Override
			public Iterable<Message> findAll() {
				return null;
			}

			@Override
			public Iterable<Message> findAllById(Iterable<UUID> iterable) {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}

			@Override
			public void deleteById(UUID uuid) {

			}

			@Override
			public void delete(Message message) {

			}

			@Override
			public void deleteAll(Iterable<? extends Message> iterable) {

			}

			@Override
			public void deleteAll() {

			}
		};
	}
}
