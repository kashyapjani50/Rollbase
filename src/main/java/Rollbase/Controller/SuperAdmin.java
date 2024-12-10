package Rollbase.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/superadmin")
public class SuperAdmin {

	
		@GetMapping("/home")
		public String AdminWelcome()
		{
			return "admin_home";
		}
	

	
}
