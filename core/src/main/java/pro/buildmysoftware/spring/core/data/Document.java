package pro.buildmysoftware.spring.core.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String title;
}
