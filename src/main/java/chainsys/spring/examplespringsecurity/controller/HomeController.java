package chainsys.spring.examplespringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HomeController {
	//any body can access home page
	@GetMapping("/home")
	public String home() {
		return "homePage";
	}
	//only the authenticated user to access dashboard
	@GetMapping("/dashboard")
	public String dashboard()
	{
		return "Dashboard";
		
	}
	
	
}
