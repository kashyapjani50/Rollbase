package Rollbase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Rollbase.Entity.MyUser;
import Rollbase.Repository.MyUserRepository;

@RestController
public class RegistrationController {

	@Autowired
	private MyUserRepository myUserRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register/new")
	public MyUser createUser(@RequestBody MyUser user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return myUserRepository.save(user);
	}
	
	
	
	
}
