package com.aakruth.dao;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aakruth.model.UsrTbl;

@Repository("userRepository")
interface UserRepository extends JpaRepository<UsrTbl, Integer>,DataTablesRepository<UsrTbl, Integer> {
	 UsrTbl findByEmail(String email);
	 UsrTbl findOne(Integer usrId);
}