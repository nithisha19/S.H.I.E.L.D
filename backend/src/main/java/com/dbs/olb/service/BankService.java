package com.dbs.olb.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.dbs.olb.model.Account;
import com.dbs.olb.model.Admin;
import com.dbs.olb.model.Bank;
import com.dbs.olb.model.Transaction;
import com.dbs.olb.model.User;

public interface BankService {
	List<Account> listAllAccount(int id);
	User findById(long empId);
	 User saveCustomer(User user);

    Optional<Account> findByAdminNameAndPassword(String name,String pwd);

    void updateUserDetails(User user);


	void updateCustomer(long id,long bankId,Account account);
	void deleteAccount( long accountId);
	void deleteUser(long userId);
	
	Bank getAccountsOfBranch(long id);
	
	List<User> getUsers();
	String initiatePendingTransac(int tid);
	List<Transaction> pendingTransactions();
	void dismiss(int tid);
	Admin login(long id, String password);
	Admin saveAdmin(Admin admin);
}
