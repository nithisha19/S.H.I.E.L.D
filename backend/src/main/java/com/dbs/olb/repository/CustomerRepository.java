package com.dbs.olb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.olb.model.User;
@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {
	
}