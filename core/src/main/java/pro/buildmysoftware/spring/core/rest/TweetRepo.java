package pro.buildmysoftware.spring.core.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TweetRepo extends PagingAndSortingRepository<Tweet, UUID>,
	JpaRepository<Tweet, UUID> {

}
