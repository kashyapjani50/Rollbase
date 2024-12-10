package Rollbase.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {
	
	@GetMapping("/home")
	public String NormalWelcome()
	{
		return "home";
	}
	
	@GetMapping("/user/home")
	public String admin()
	{
		return "user_home";
	}
	
	@GetMapping("/login")
	public String loginhandler()
	{
		return "custom_login";
	}


}
