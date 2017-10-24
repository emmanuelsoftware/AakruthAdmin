package com.aakruth.dao;

import com.aakruth.model.RoleTbl;
import com.aakruth.model.UsrTbl;

public interface RoleService {

	RoleTbl findByUsrTbl(UsrTbl user);
	void save(RoleTbl role);
	void delete(RoleTbl role);
}
