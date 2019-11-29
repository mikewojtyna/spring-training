package pro.buildmysoftware.spring.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class ProfilePermissionEvaluator implements PermissionEvaluator {

	private ProfileRepository profileRepository;

	public ProfilePermissionEvaluator(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object o,
				     Object o1) {
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
				     Serializable objectId, String objectType,
				     Object permission) {
		if ((authentication == null) || (objectType == null) || !(permission instanceof String)) {
			return false;
		}
		return profileRepository.findById((UUID) objectId)
			.map(profile -> profile.getOwner()
				.equals(authentication.getName()))
			.orElse(false);
	}
}
