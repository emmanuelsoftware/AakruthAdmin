package com.erp.dao;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.model.User;

@Repository("userRepository")
interface UserRepository extends JpaRepository<User, Integer>,DataTablesRepository<User, Integer> {
	 User findByEmail(String email);
	 User findOne(Integer usrId);
}