package com.erp.dao;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.erp.model.Role;
import com.erp.model.User;

public interface UserService {

	User findUserByEmail(String email);
	boolean saveUser(User user);
	DataTablesOutput<Role> findAll(DataTablesInput input);
	User findOne(Integer usrId);
	boolean delete(Integer usrId);
	boolean reset(Integer usrId);
}
