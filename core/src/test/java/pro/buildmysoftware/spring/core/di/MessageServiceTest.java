package pro.buildmysoftware.spring.core.di;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class MessageServiceTest {

	// @formatter:off
	@DisplayName("given any repo, then translate")
	// @formatter:on
	@Test
	@Disabled("this is just a showcase how we could write tests without " + "spring framework, not a working example")
	void test() throws Exception {
		// given
		MessageKey key = key("hello.msg");
		MessageRepository repo = inMemRepo();
		MessageService messageService = new MessageService(repo);
		Message expectedMsg = message("Hi!");

		// when
		Optional<Message> translatedToEnglishMsg = messageService
			.findAndTranslate(key, Language.EN);

		// then
		assertThat(translatedToEnglishMsg).isEqualTo(expectedMsg);
	}

	private Message message(String s) {
		return null;
	}

	private MessageKey key(String key) {
		return new MessageKey(key);
	}

	private MessageRepository inMemRepo() {
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