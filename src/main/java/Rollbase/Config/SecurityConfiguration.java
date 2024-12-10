package Rollbase.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import Rollbase.Controller.AuthenticationSuccessHandler;
import Rollbase.Service.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private MyUserDetailService userDetailService;
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF if necessary
	            .authorizeHttpRequests(registry -> {
	                registry.requestMatchers("/register/**" , "/home/**").permitAll(); // Ensure this is FIRST
	                registry.requestMatchers("/admin/**").hasRole("ADMIN");
	                registry.requestMatchers("/user/**").hasRole("USER");
	                registry.requestMatchers("/company/**").hasRole("COMPANY");
	                registry.requestMatchers("/superadmin/**").hasRole("SUPERADMIN"); // Added matcher for SUPERADMIN
	                
	                registry.anyRequest().authenticated(); // All other requests require authentication
	            })
	            .formLogin(httpSecurityFormLoginConfigurer -> { //httpSecurityFormLoginConfigurer is a object	
	            	httpSecurityFormLoginConfigurer.loginPage("/login")
	            	.successHandler(new AuthenticationSuccessHandler())
	            	.permitAll();	
	            })
	            
	            .build();
	}


	
//	@Bean
//	public  UserDetailsService userDetailsService()
//	{
//		UserDetails normalUser = User.builder()
//				
//				.username("KK")
//				.password("$2a$12$KsnNx4waYYIv/XGcYpQC7u.2EzUDPdYJm7Nr3LJSe9TLmOJHaci8u")
//				.roles("USER")
//				.build();
//				
//		UserDetails adminUser = User.builder()
//				.username("admin")
//				.password("$2a$12$c55hijsieMFwwhrr8jOJjel346sASRBfFeXPThUvLV5ZUZdxV1cxy")
//				.roles("ADMIN","USER")
//				.build();
//		return new InMemoryUserDetailsManager(normalUser , adminUser);
//	}
	
	@Bean
	public  UserDetailsService userDetailsService()
	{
		return userDetailService;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	
	
	@Bean 
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
