package com.dbs.olb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.olb.model.Bank;
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}
