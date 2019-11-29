package pro.buildmysoftware.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
}
