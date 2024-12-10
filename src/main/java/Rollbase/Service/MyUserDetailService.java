package Rollbase.Service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Rollbase.Entity.MyUser;
import Rollbase.Repository.MyUserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private MyUserRepository myUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		java.util.Optional<MyUser> user  = myUserRepository.findByUsername(username);
		if(user.isPresent())
		{
			var userObject = user.get();
			return User.builder()
				.username(userObject.getUsername())
				.password(userObject.getPassword())
				.roles(getRoles(userObject))
				.build();
		}else
		{
			throw new  UsernameNotFoundException(username);
		}
		
		
	}

	private String[] getRoles(MyUser user) {
		if(user.getRole() == null)
		{
			return new String[]{"USER"};
		}
		return user.getRole().split(",");
	}

}
