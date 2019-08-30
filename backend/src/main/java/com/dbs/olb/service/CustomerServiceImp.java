package com.dbs.olb.service;
import com.dbs.olb.model.User;
import com.dbs.olb.model.Transaction;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import com.dbs.olb.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dbs.olb.model.Account;
import com.dbs.olb.model.Json;
@Service
public class CustomerServiceImp implements CustomerService{
	
	@Autowired
	private CustomerRepository  customerRepository;
	
	@Autowired
	private TransactionRepository transactionRepository; 
	@Autowired
	private  AccountRepository accountRepository;
	
	@Autowired
	public CustomerServiceImp(CustomerRepository  customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	@Transactional
	public List<Account> getAccounts(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Transaction> getStatements(long id) {
		// TODO Auto-generated method stub
		return this.transactionRepository.findAllByAccountId(id);
	}

	@Override
	@Transactional
	public List<Transaction> getStatementsRange(long Id, Date from, Date to) {
		return this.transactionRepository.findBetweenRanges(Id, from, to);
	}

	@Override
	@Transactional
	public Json initiateTransfer(long fromId, long toId, double transferAmount) {
		// TODO Auto-generated method stub
		Json j=new Json();
		Account from=accountRepository.findById(fromId).get();
		double currentBalance= from.getAccount_balance();
		if(currentBalance-transferAmount<5000)
		{
			j.setStatus("Insufficient Funds. Minimum Balance should be atleast 5000$");
			j.setStatusCode(401);
			return j;
		}
		Optional <Account> to=accountRepository.findById(toId);
		if(to.isPresent()) {
			Account toAccount = to.get();
			System.out.println("one");
			
//	Calendar calendar = Calendar.getInstance();
//    Date sqlDate = new Date(calendar.getTime().getTime());
		//Calendar calendar = Calendar.getInstance();
			//java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
//			  long millis=System.currentTimeMillis();  
//			  java.sql.Date date=new java.sql.Date(millis);
//			  java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			
		Timestamp sqlDate =  new Timestamp(System.currentTimeMillis());
			String formatDate=new SimpleDateFormat("yyyy-MM-dd").format(sqlDate);
			int count = transactionRepository.getCount(fromId, formatDate);
			long value = 0;
			if(count>=1) {
			  value=transactionRepository.getAmountByDate(fromId,formatDate);
			}
			value+=transferAmount;
			//System.out.println(value);
			if(value < 10000 || (count==0 && value==0)) {
			Transaction transaction=new Transaction(from.getAccount_no(),from.getAccount_balance()-transferAmount, toAccount.getAccount_no(), toAccount.getAccount_balance()+transferAmount,transferAmount,sqlDate,"success");
			transactionRepository.save(transaction);
			from.setAccount_balance(currentBalance-transferAmount);
			toAccount.setAccount_balance(transferAmount+toAccount.getAccount_balance());
			//return "Your transaction is successfull";
			
			j.setStatus("Your transaction is successfull");
			j.setStatusCode(200);
			return j;
					
			} else {
				Transaction transaction=new Transaction(from.getAccount_no(),from.getAccount_balance(), toAccount.getAccount_no(), toAccount.getAccount_balance(),transferAmount,sqlDate,"pending");
				transactionRepository.save(transaction);
				
				//return "Your transaction is pending";
				j.setStatus("Your transaction is pending");
				j.setStatusCode(402);
				return j;
			}
			
		}
		else
		{
			
			j.setStatus( "Invalid Recipient Account Number");
			j.setStatusCode(402);
			return j;
		}
		
	}

	@Override
	@Transactional
	public User logIn(long id, String password) {
    Optional<User> userObj = customerRepository.findById(id);
    if(userObj.isPresent()){
    	User user = userObj.get();
    	if(user.getPassword().equals(password)) {
    		return user;
    	}
    }
		return null;
	}

	@Override
	@Transactional
	public User getAccountsOfUser(long id) {
		return this.customerRepository.findById(id).get();
	}

	@Override
	public List<Transaction> miniStatement(long id) {
		// TODO Auto-generated method stub
		return this.transactionRepository.miniStatement(id);
	}
	
	

}
