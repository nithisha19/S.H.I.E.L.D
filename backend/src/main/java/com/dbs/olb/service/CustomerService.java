package com.dbs.olb.service;

import java.sql.Date;
import java.util.List;



import com.dbs.olb.model.Account;
import com.dbs.olb.model.Json;
import com.dbs.olb.model.Transaction;
import com.dbs.olb.model.User;

public interface CustomerService {
	List<Account> getAccounts(int id);
	
	List<Transaction> getStatements(long id);
	
	List<Transaction> getStatementsRange(long id,Date from,Date to);

	Json initiateTransfer(long fromId, long toId, double transferAmount);
	User logIn(long id,String password);
	User getAccountsOfUser(long id);

	List<Transaction> miniStatement(long id);

}
