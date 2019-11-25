package pro.buildmysoftware.spring.core.di;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

	private MessageRepository repo;

	public MessageService(MessageRepository repo) {
		this.repo = repo;
	}

	public Optional<Message> findAndTranslate(MessageKey key,
						  Language language) {
		return repo.findById(key.getUUID())
			.map(m -> m.translateTo(language));
	}
}
