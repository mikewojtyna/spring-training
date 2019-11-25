package pro.buildmysoftware.spring.core.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.UUID;

public interface DocumentRepository extends CrudRepository<Document, UUID>,
	MyCustomDocumentRepository {

	Collection<Document> findByTitle(String title);

	@Query("SELECT d FROM Document d WHERE d.title = :title")
	Collection<Document> findByTitleManual(String title);
}
