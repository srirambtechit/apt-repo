package com.aptikraft.spring.users.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.aptikraft.spring.dao.UserDAO;
import com.aptikraft.spring.model.UserDO;
import com.aptikraft.spring.model.UserRoleDO;

public class MyUserDetailsService implements UserDetailsService {

	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		UserDO userDO = userDAO.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(userDO.getUserRoleDOs());

		return buildUserForAuthentication(userDO, authorities);

	}

	// Converts UserDO to org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(UserDO userDO, List<GrantedAuthority> authorities) {
		return new User(userDO.getUsername(), userDO.getPassword(), userDO.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<UserRoleDO> userRoleDOs) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRoleDO userRoleDO : userRoleDOs) {
			setAuths.add(new SimpleGrantedAuthority(userRoleDO.getRole()));
		}

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

		return result;
	}

}
