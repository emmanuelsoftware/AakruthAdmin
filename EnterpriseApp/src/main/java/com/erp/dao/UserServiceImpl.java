package com.erp.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.model.Role;
import com.erp.model.User;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info(userName + " trying to logging in");
		User user = userRepository.findByEmail(userName);
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return buildUserForAuthentication(user, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		boolean active = false;
		if (user.getSta() == 'L') {
			if (user.getPswd() != "Aakruth@123") {
				active = true;
				logger.info(user.getEmail() + " logged in succesfully");
			} else {

			}
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPswd(), active, true,
				true, true, authorities);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean saveUser(User user) {
		try {
			userRepository.save(user);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public DataTablesOutput<Role> findAll(DataTablesInput input) {
		return roleRepository.findAll(input);
	}

	@Override
	public User findOne(Integer usrId) {
		return userRepository.findOne(usrId);
	}

	@Override
	public boolean delete(Integer usrId) {
		try {
			userRepository.delete(usrId);
		} catch (Exception ex) {
			return false;
		}
		return true;

	}

	@Override
	public boolean reset(Integer usrId) {
		try {
			User user = userRepository.findOne(usrId);
			user.setPswd("Aakruth@123");
			userRepository.save(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
