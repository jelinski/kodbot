package pl.yeloon.magisterium.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.yeloon.magisterium.repository.UserDAO;

@Service("customUserDetailsService")
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		pl.yeloon.magisterium.model.User domainUser = userDAO.getUser(login);

		if (domainUser == null) {
			throw new UsernameNotFoundException("Nie znaleziono uzytkownika o loginie: " + login);
		}

		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(Role.ROLE_USER.toString()));

		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		CustomUserDetails customUserDetails = new CustomUserDetails(domainUser.getEmail(), domainUser.getPassword(), domainUser.getEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked, roles);

		customUserDetails.setFirstName(domainUser.getFirstName());
		customUserDetails.setLastName(domainUser.getLastName());
		customUserDetails.setId(domainUser.getId());
		return customUserDetails;
	}

}