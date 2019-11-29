package pro.buildmysoftware.spring.security;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/secured")
public class SecuredServiceController {

	private SecuredService securedService;

	public SecuredServiceController(SecuredService securedService) {
		this.securedService = securedService;
	}

	@GetMapping
	public String securedMethod(Principal principal) {
		return securedService.myProfile(principal);
	}

	@PostMapping
	public void changeProfileName(@RequestBody ChangeProfileCommand dto) {
		securedService
			.changeProfileName(UUID.fromString(dto.getId()), dto
				.getNewName());
	}
}
