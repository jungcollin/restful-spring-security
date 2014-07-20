package com.github.virgo47.respsec.mvc;

import java.util.Map;

import javax.annotation.PostConstruct;

import com.github.virgo47.respsec.AuthenticationService;
import com.github.virgo47.respsec.main.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller with REST API. Access to login is generally permitted,
 * stuff in /secure/ sub-context is protected by config. Some security
 * annotations are thrown in just to make a point.
 */
@RestController
public class MainRestController {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private AuthenticationService authenticationService;

	@PostConstruct
	public void init() {
		System.out.println(" *** MainRestController.init with: " + applicationContext);
	}

	@RequestMapping(value = "/login", produces = "text/plain")
	public String login() {
		System.out.println(" *** MainRestController.login");
		return "There is nothing special about login here, just use Authorizaton: Basic, or provide secure token.\n" +
			"For testing purposes you can use headers X-Username and X-Password instad of HTTP Basic Access Authentication.\n" +
			"THIS APPLIES TO ANY REQUEST protected by Spring Security (see filter-mapping).\n\n" +
			"Realize, please, that Authorization request (or the one with testing X-headers) must be POST, otherwise they are ignored.";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		System.out.println(" *** MainRestController.logout");
		return "Logout invalidates token on server-side. It must come as a POST request with valid X-Auth-Token, URL is configured for MyAuthenticationFilter.";
	}

	@RequestMapping("/test")
	public String test() {
		System.out.println(" *** MainRestController.test");
		// Spring Security dependency is unwanted in controller, use
		return "SecurityContext: " + SecurityContextHolder.getContext();
	}

	@Secured("hasRole('ADMIN')")
	@RequestMapping("/admin")
	public String admin() {
		System.out.println(" *** MainRestController.admin");
		return "Cool, you're admin!";
	}

	@RequestMapping("/secure/service1")
	public String service1() {
		System.out.println(" *** MainRestController.service1");
		return "Any authorized user should have access.";
	}

	@Secured("hasRole('SPECIAL')")
	@RequestMapping("/secure/special")
	public String special() {
		System.out.println(" *** MainRestController.special");
		return "SPECIAL users should have access.";
	}

	@Secured("hasRole('ADMIN')")
	@RequestMapping("/secure/allusers")
	public Map<String, UserContext> allUsers() {
		System.out.println(" *** MainRestController.allUsers");
		return authenticationService.getValidUsers(); // DEBUG ONLY!
	}
}