package com.aakruth.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakruth.model.RoleTbl;
import com.aakruth.model.UsrTbl;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RoleRepository roleRepository;
	@Override
	public RoleTbl findByUsrTbl(UsrTbl user) {
		return roleRepository.findByUsrTbl(user);
	}
	@Override
	public void save(RoleTbl role) {
		roleRepository.save(role);
	}
	@Override
	public void delete(RoleTbl role) {
		// TODO Auto-generated method stub
		
	}

}
