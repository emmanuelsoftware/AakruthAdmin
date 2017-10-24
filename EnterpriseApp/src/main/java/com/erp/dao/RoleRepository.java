package com.erp.dao;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.model.Role;
import com.erp.model.User;

@Repository("roleRepository")
interface RoleRepository extends JpaRepository<Role, Integer>,DataTablesRepository<Role, Integer> {

	Role findByRole(String role);
	Role findByUser(User user);
	
}
