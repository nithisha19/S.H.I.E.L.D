package com.dbs.olb.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table
@Data
public class Transaction {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int transaction_id;
	private long from_account;
	private double from_account_balance;
	private long to_account;
	private double to_account_balance;
	private double amount;
	private String  status;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Timestamp transaction_date;
	
	
	public Transaction() {
		
	}
	
	public Transaction(long from_account, double from_account_balance, long to_account, double to_account_balance,
			double amount, Timestamp transaction_date,String status) {
		super();
		this.from_account = from_account;
		this.from_account_balance = from_account_balance;
		this.to_account = to_account;
		this.to_account_balance = to_account_balance;
		this.amount = amount;
		this.transaction_date = transaction_date;
		this.status = status;
	}
	

	

}
