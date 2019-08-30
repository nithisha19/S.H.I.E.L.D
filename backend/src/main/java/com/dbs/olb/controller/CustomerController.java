package com.dbs.olb.controller;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.olb.model.Account;
import com.dbs.olb.model.Json;
import com.dbs.olb.model.ParamKlass;
import com.dbs.olb.model.Transaction;
import com.dbs.olb.model.User;
import com.dbs.olb.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin
@RestController
//@Api(value="CustomerControllerAPI",produces=MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/olb/customer")
public class CustomerController {
	
	@Autowired
	public CustomerService customerService;
	
//	@ApiOperation("Gets accounts by user_id")
	 @GetMapping(value = "/accounts/{id}")
	public Set<Account> getAccounts(@PathVariable long id){
		return this.customerService.getAccountsOfUser(id).getAccounts();
		
	}
//	@ApiOperation("Gets Statement by account_id")
	 @GetMapping(value = "/{id}/statements")
	public List<Transaction> getStatements(@PathVariable long id){
		return this.customerService.getStatements(id);
	 }	
	@GetMapping(value = "/{id}/miniStatement")
	public List<Transaction> getminiStatement(@PathVariable long id){
		return this.customerService.miniStatement(id);
		
	}
//	@ApiOperation("Gets statements within a range by account_id")
	 @GetMapping(value = "/{id}/statementsrange")
	public List<Transaction> getStatementsRange(@PathVariable long id,@RequestBody ParamKlass paramObj){
		return this.customerService.getStatementsRange(id, paramObj.getFromDate(),paramObj.getToDate());
		
	}
	 @PostMapping(value = "/transfer",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public Json initiateTransfer(@RequestBody Transaction transaction) {
		
		 return this.customerService.initiateTransfer(transaction.getFrom_account(),transaction.getTo_account(),transaction.getAmount());
		
	 }
	 
	@PostMapping(value = "/login")
	public User login(@RequestBody ParamKlass paramObj)
	{
		
		return this.customerService.logIn(paramObj.getId(),paramObj.getPassword());
	}
	
}
