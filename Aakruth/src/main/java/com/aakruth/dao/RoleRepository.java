package com.aakruth.dao;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aakruth.model.RoleTbl;
import com.aakruth.model.UsrTbl;

@Repository("roleRepository")
interface RoleRepository extends JpaRepository<RoleTbl, Integer>,DataTablesRepository<RoleTbl, Integer> {

	RoleTbl findByRole(String role);
	RoleTbl findByUsrTbl(UsrTbl user);
	
}
