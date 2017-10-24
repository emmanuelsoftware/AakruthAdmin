package com.erp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.model.Role;
import com.erp.model.User;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RoleRepository roleRepository;
	@Override
	public Role findByUser(User user) {
		return roleRepository.findByUser(user);
	}
	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}
	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		
	}

}
