package com.dbs.olb.service;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dbs.olb.model.Account;
import com.dbs.olb.model.Admin;
import com.dbs.olb.model.Bank;
import com.dbs.olb.model.User;
import com.dbs.olb.model.Transaction;
import com.dbs.olb.repository.AccountRepository;
import com.dbs.olb.repository.AdminRespository;
import com.dbs.olb.repository.BankRepository;
import com.dbs.olb.repository.CustomerRepository;
import com.dbs.olb.repository.TransactionRepository;
@Service
public class BankServiceImp implements BankService {


    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transRepository;

    @Autowired
    private AdminRespository adminRepository;
    @Autowired
    public BankServiceImp(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    @Override
    @Transactional
    public User saveCustomer(User user) {
    	Random random = new Random();
    	boolean t=true;
    	//long id=random.nextInt(100000);
    	long id = ThreadLocalRandom.current().nextInt(1000, 9999);
    	while(t)
    	{
    		if(customerRepository.existsById(id) == false)
    		{
    			user.setUser_id(id); t=false;
    		}
    		else {
    			id = ThreadLocalRandom.current().nextInt(100000, 999999);
    			
    		}
    	}	
    	return customerRepository.save(user);
    	}
    @Override
    @Transactional
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    //@Override
    //@Transactional
    //public List<Transaction> listAllTrans(int id) {
    //return 	transRepository.findAllByAccountId(id);
    //	return transRepository.listAll(id);		
    //}

    @Override
    @Transactional
    public
    List < Account > listAllAccount(int id) {
        return null;
    }


    @Override
    @Transactional
    public   User  findById(long empId) {
        return customerRepository.findById(empId).get();
    }

    @Override
    @Transactional
    public Optional < Account > findByAdminNameAndPassword(String name, String pwd) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public void updateUserDetails(User user) {
        // TODO Auto-generated method stub
        customerRepository.saveAndFlush(user);
    }
    @Override
    @Transactional
    public void updateCustomer(long userId, long bankId, Account account) {

        User user = customerRepository.findById(userId).get();
        Random random = new Random();
    	boolean t=true;
    	//long id=random.nextInt(100000);
    	long id = ThreadLocalRandom.current().nextInt(100000, 999999);
    	while(t)
    	{
    		if(customerRepository.existsById(id) == false)
    		{
    			account.setAccount_no(id); t=false;
    		}
    		else {
    			id = ThreadLocalRandom.current().nextInt(100000, 999999);
    			
    		}
    	}	
    	
        user.addAccount(account);
        Bank bank = bankRepository.findById(bankId).get();
        //bank.addAccount(account);
        account.setBank(bank);
        customerRepository.saveAndFlush(user);

    }
    @Override
    @Transactional
    public void deleteAccount(long accountId) {
        // TODO Auto-generated method stub
        this.accountRepository.deleteById(accountId);

    }
    @Override
    public void deleteUser(long userId) {
        // TODO Auto-generated method stub
        this.customerRepository.deleteById(userId);
    }
    @Override
    public Bank getAccountsOfBranch(long id) {
        return bankRepository.findById(id).get();
    }
    @Override
    public List < User > getUsers() {
        return this.customerRepository.findAll();
    }
    @Transactional
    @Override
    public String initiatePendingTransac(int tid) {
        Optional < Transaction > transactionOp = this.transRepository.findById(tid);
        if (transactionOp.isPresent()) {
            Transaction transaction = transactionOp.get();
            return initiateTransfer(transaction.getFrom_account(), transaction.getTo_account(), transaction.getAmount(), transaction);
        }
        return "Error processing your request";

    }

    @Transactional
    String initiateTransfer(long fromId, long toId, double transferAmount, Transaction transaction) {
        // TODO Auto-generated method stub

        Account from = accountRepository.findById(fromId).get();
        double currentBalance = from.getAccount_balance();
        if (currentBalance - transferAmount < 5000) {
            transaction.setStatus("fail");
            transRepository.saveAndFlush(transaction);
            return "Insufficient Funds. Minimum Balance should be atleast 5000$";
        }
        Optional < Account > to = accountRepository.findById(toId);
        if (to.isPresent()) {
            Account toAccount = to.get();

            Calendar calendar = Calendar.getInstance();
            Date ourJavaDateObject = new Date(calendar.getTime().getTime());
            transaction.setStatus("success");
            transaction.setFrom_account_balance(currentBalance - transferAmount);
            transaction.setTo_account_balance(transferAmount + toAccount.getAccount_balance());
            transRepository.saveAndFlush(transaction);
            from.setAccount_balance(currentBalance - transferAmount);
            toAccount.setAccount_balance(transferAmount + toAccount.getAccount_balance());
            return "Your transaction is successfull";
        } else {
            transaction.setStatus("fail");
            transRepository.saveAndFlush(transaction);
            return "Invalid Recipient Account Number";
        }

    }
	@Override
	public List<Transaction> pendingTransactions() {		
		return this.transRepository.pendingTransactions();
	}
    
	@Transactional
	public void dismiss(int tid) {
		 Optional < Transaction > transactionOp = this.transRepository.findById(tid);
	        if (transactionOp.isPresent()) {
	            Transaction transaction = transactionOp.get();
	            transaction.setStatus("Cancelled by Bank.");
	            transRepository.saveAndFlush(transaction);
	        }
		
	}
	
	@Transactional
	@Override
	public Admin login(long id, String password) {
		Optional< Admin> adminObj = adminRepository.findById(id);
		if(adminObj.isPresent()) {
			
			if(adminObj.get().getPassword().equals(password)) {
			
				return adminObj.get();
				}
		}
		return null;
	}
    






}