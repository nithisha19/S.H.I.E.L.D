package com.dbs.olb.controller;


import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.olb.model.Account;
import com.dbs.olb.model.Admin;
import com.dbs.olb.model.Json;
import com.dbs.olb.model.ParamKlass;
import com.dbs.olb.model.Transaction;
import com.dbs.olb.model.User;
import com.dbs.olb.service.BankService;
import com.dbs.olb.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
@CrossOrigin
@RestController
//@Api(value="BankControllerAPI",produces=MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path="/olb/bank")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
//	public List<Account> listAllAccounts(){
//		return this.bankService.listAll();
//	}

	@GetMapping(value = "/users/{empId}")
	public User findById(@PathVariable long empId){
		return this.bankService.findById(empId);
		
	}
	@PostMapping(value = "/saveuser",consumes = MediaType.APPLICATION_JSON_VALUE)
	public User saveCustomer(@RequestBody User user) {
		return this.bankService.saveCustomer(user);
	}
//	@ApiOperation("Gets username and pasword of admin")
	@GetMapping(value = "/admin")
    public Optional<Account> findByAdminNameAndPassword(String name,String pwd){
		return this.bankService.findByAdminNameAndPassword(name, pwd);
    	
    }
	@PostMapping(value = "/updateUserDetails",consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateDetails(@RequestBody User user) {
    	this.bankService.updateUserDetails(user);
    }

	@PostMapping(value = "/{userId}/{bankId}/addAccount",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCustomer(@PathVariable long userId,@PathVariable int bankId,@RequestBody Account account) {
		Calendar calendar = Calendar.getInstance();
        Date opened_date = new Date(calendar.getTime().getTime());
        account.setOpened_date(opened_date);
		 this.bankService.updateCustomer(userId,bankId,account);
		
	}
	@DeleteMapping(value="/{userId}/deleteUser")
	public void deleteUser(@PathVariable long userId) {
		this.bankService.deleteUser(userId);
	}
	@DeleteMapping(value="/{accountId}/deleteAccount")
	public void deleteAccount(@PathVariable 
			long accountId) {
		this.bankService.deleteAccount(accountId);
		
	}
	
//	@ApiOperation("Gets accounts by id")
	 @GetMapping(value = "/accounts/{id}")
	public Set<Account> getAccounts(@PathVariable long id){
		return this.bankService.getAccountsOfBranch(id).getAccounts();
	}
	 
//	@ApiOperation("Gets all customers")
	@GetMapping(value= "/customers")
	public java.util.List<User> getUsersOfBranch(){
		return  this.bankService.getUsers();
	}
	
//	@ApiOperation("Gets all pending transcations")
	@GetMapping(value="/pendingTransactions")
	public java.util.List<Transaction> pendingTransactions(){
		return  this.bankService.pendingTransactions();
	}
	
	@PostMapping(value="/initiatePendingTransfer/{tid}")
	public Json initiatePendingTransfer(@PathVariable int tid) {
		Json j=new Json();
		 j.setStatus( this.bankService.initiatePendingTransac(tid));
		 return j;
	}
	
	@PostMapping(value="/dismiss/{tid}")
	public void dismissTransaction(@PathVariable int tid) {
		 this.bankService.dismiss(tid);
	}
	
	@PostMapping(value="/login")
	public Admin login(@RequestBody ParamKlass payload) {
	     return this.bankService.login(payload.getId(),payload.getPassword());
	}
	@PostMapping(value="/sign")
	public void sign(@RequestBody Admin admin) {
		 this.bankService.saveAdmin(admin);
	}
	
	
}
