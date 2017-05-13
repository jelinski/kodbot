package pl.yeloon.magisterium.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import pl.yeloon.magisterium.security.CustomUserDetails;

public class SecurityUtils {

	private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
	
	public static boolean isLoggedIn(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		if (!(auth instanceof AnonymousAuthenticationToken)) { 
			return true;
		}
		return false;
	}
	
	public static Integer getLoggedInUserId(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		if (!(auth instanceof AnonymousAuthenticationToken)) { 
			return ((CustomUserDetails) auth.getPrincipal()).getId();
		}
		else{
			return null;
		}
	}
}
