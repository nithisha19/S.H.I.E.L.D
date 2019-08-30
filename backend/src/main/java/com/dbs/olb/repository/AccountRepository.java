package com.dbs.olb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.olb.model.Account;
import com.dbs.olb.model.User;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

}
