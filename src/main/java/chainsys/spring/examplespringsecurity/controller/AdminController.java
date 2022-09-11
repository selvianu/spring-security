package chainsys.spring.examplespringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/home")
	public String adminHome() {
		return "Admin home";
	}

	@GetMapping("/dashboard")
	public String adminDashboard() {
		return "Admin Dashboard";
	}

}
	