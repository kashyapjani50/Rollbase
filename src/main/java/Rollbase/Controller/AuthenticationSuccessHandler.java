package Rollbase.Controller;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import java.io.IOException;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException 
    {
    	boolean isAdmin =  authentication.getAuthorities().stream()
    						.anyMatch(grantedAuthority  -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    	
    	boolean isCompany =  authentication.getAuthorities().stream()
				.anyMatch(grantedAuthority  -> grantedAuthority.getAuthority().equals("ROLE_COMPANY"));
    	
    	if (isAdmin)
    	{
    		setDefaultTargetUrl("/admin/home");
    	}
    	else if (isCompany)
    	{
    		setDefaultTargetUrl("/company/home");
    	}
    	else
    	{
    		setDefaultTargetUrl("/user/home");
    	}
    	
    	setDefaultTargetUrl(getDefaultTargetUrl());
       super.onAuthenticationSuccess(request,response,authentication);
            
    }
}
