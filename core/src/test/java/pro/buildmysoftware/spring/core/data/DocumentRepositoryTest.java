package pro.buildmysoftware.spring.core.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DocumentRepositoryTest {

	@Autowired
	private DocumentRepository documentRepository;

	@BeforeEach
	void beforeEach() {
		documentRepository.deleteAll();
	}

	// @formatter:off
	@DisplayName("add new document")
	// @formatter:on
	@Test
	void test() throws Exception {
		// given
		Document document = anyDocument();

		// when
		documentRepository.save(document);

		// then
		assertThat(documentRepository.findAll()).hasSize(1);
	}

	// @formatter:off
	@DisplayName("when add document with title, then find by title works")
	// @formatter:on
	@Test
	void testTitle() throws Exception {
		// given
		Document firstDocument = documentWithTitle("First");
		documentRepository.save(firstDocument);
		Document secondDocument = documentWithTitle("Second");
		documentRepository.save(secondDocument);

		// when
		Collection<Document> foundDocument = documentRepository
			.findByTitle("First");

		// then
		assertThat(foundDocument).hasSize(1)
			.extracting(d -> d.getTitle()).contains("First");
	}

	// @formatter:off
	@DisplayName(
		"load fake document using custom repository method"
	)
	// @formatter:on
	@Test
	void testCustom() throws Exception {
		// when
		Optional<Document> any = documentRepository
			.findAnyByTitle("any");

		// then
		assertThat(any.get()).isEqualTo(new Document());
	}

	private Document documentWithTitle(String first) {
		Document document = new Document();
		document.setTitle(first);
		return document;
	}

	private Document anyDocument() {
		return new Document();
	}
}