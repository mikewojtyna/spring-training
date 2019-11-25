package pro.buildmysoftware.spring.core.data;

import java.util.Optional;

public class MyCustomDocumentRepositoryImpl implements MyCustomDocumentRepository {

	@Override
	public Optional<Document> findAnyByTitle(String title) {
		// return fake document
		return Optional.of(new Document());
	}
}
