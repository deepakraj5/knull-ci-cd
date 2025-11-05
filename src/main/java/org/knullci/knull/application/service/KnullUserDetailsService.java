package org.knullci.knull.application.service;

import org.knullci.knull.domain.model.KnullUserDetails;
import org.knullci.knull.persistence.entity.User;
import org.knullci.knull.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class KnullUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public KnullUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return new KnullUserDetails(user);
	}

}
