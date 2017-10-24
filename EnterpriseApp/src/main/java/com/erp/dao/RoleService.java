package com.erp.dao;

import com.erp.model.Role;
import com.erp.model.User;

public interface RoleService {

	Role findByUser(User user);
	void save(Role role);
	void delete(Role role);
}
