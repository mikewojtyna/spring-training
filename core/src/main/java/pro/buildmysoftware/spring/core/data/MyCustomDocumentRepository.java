package pro.buildmysoftware.spring.core.data;

import java.util.Optional;

public interface MyCustomDocumentRepository {

	Optional<Document> findAnyByTitle(String title);
}
