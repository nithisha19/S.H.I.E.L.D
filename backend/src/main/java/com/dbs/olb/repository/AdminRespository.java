package com.dbs.olb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.olb.model.Admin;
@Repository
public interface AdminRespository extends JpaRepository<Admin,Long>{

}
