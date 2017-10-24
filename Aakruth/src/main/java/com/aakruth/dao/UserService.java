package com.aakruth.dao;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.aakruth.model.RoleTbl;
import com.aakruth.model.UsrTbl;

public interface UserService {

	UsrTbl findUserByEmail(String email);
	boolean saveUser(UsrTbl user);
	DataTablesOutput<RoleTbl> findAll(DataTablesInput input);
	UsrTbl findOne(Integer usrId);
	boolean delete(Integer usrId);
	boolean reset(Integer usrId);
}
