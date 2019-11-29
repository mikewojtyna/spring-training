package pro.buildmysoftware.spring.security;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
public class SecuredService {

	private ProfileRepository profileRepository;

	public SecuredService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Secured("ROLE_USER")
	public String myProfile(Principal principal) {
		return principal.getName();
	}

	@PreAuthorize("hasPermission(#profileId, 'Profile', 'edit')")
	public void changeProfileName(UUID profileId, String newName) {
		profileRepository.findById(profileId).ifPresent(profile -> {
			profile.setName(newName);
			profileRepository.save(profile);
		});
	}
}
