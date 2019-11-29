package pro.buildmysoftware.spring.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceIsSecuredIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ProfileRepository profileRepository;

	// @formatter:off
	@DisplayName("given no user, when GET on /secured, then unauthorized")
	// @formatter:on
	@Test
	void test0() throws Exception {
		// when
		mockMvc.perform(get("/secured"))

			// then
			.andExpect(status().isUnauthorized());
	}

	// @formatter:off
	@DisplayName("given USER role, when GET on /secured, then OK")
	// @formatter:on
	@Test
	@WithMockUser(roles = "USER")
	void test1() throws Exception {
		// when
		mockMvc.perform(get("/secured"))

			// then
			.andExpect(status().isOk());
	}

	// @formatter:off
	@DisplayName("given valid HTTP basic, when GET on /secured, then OK")
	// @formatter:on
	@Test
	void test2() throws Exception {
		// when
		mockMvc.perform(get("/secured")
			// we can use headers directly
			// we encoded "goobar:password" in Base64
			// .header("Authorization", "Basic
			// Z29vYmFyOnBhc3N3b3Jk"))

			// or with httpBasic post processor
			.with(httpBasic("goobar", "password")))

			// then
			.andExpect(status().isOk());
	}

	// @formatter:off
	@DisplayName("given goobar profile, then goobar can edit it")
	// @formatter:on
	@Test
	@WithMockUser(username = "goobar")
	void testPermissionOk() throws Exception {
		// given
		Profile goobarProfile = new Profile();
		goobarProfile.setOwner("goobar");
		goobarProfile.setName("Mike");
		Profile savedGoobarProfile = profileRepository
			.save(goobarProfile);
		String changeProfileCommandJson =
			// @formatter:off
			String.format(
			"{ \"id\": \"%s\", \"newName\": \"foobar\" }",
				savedGoobarProfile.getId()
			);
			// @formatter:on

		// when
		mockMvc.perform(post("/secured")
			.contentType(MediaType.APPLICATION_JSON)
			.content(changeProfileCommandJson))

			// then
			.andExpect(status().isOk());
	}

	// @formatter:off
	@DisplayName("given goobar profile, then goobar can edit it")
	// @formatter:on
	@Test
	@WithMockUser(username = "hoobar")
	void testPermissionAuthorized() throws Exception {
		// given
		Profile goobarProfile = new Profile();
		goobarProfile.setOwner("goobar");
		goobarProfile.setName("Mike");
		Profile savedGoobarProfile = profileRepository
			.save(goobarProfile);
		String changeProfileCommandJson =
			// @formatter:off
			String.format(
			"{ \"id\": \"%s\", \"newName\": \"foobar\" }",
				savedGoobarProfile.getId()
			);
			// @formatter:on

		// when
		mockMvc.perform(post("/secured")
			.contentType(MediaType.APPLICATION_JSON)
			.content(changeProfileCommandJson))

			// then
			.andExpect(status().isForbidden());
	}
}
