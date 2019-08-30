package com.dbs.olb.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Table
@Data
public class Account {


	@Id
	
//	@Min(value = 100000)
//	@Max(value = 999999)
	private long account_no;
	private String account_type;
	@Min(value = 5000)
	private double account_balance;
	private Date opened_date;	
	
	

	@EqualsAndHashCode.Exclude
	@ManyToOne
    @JoinColumn(name = "bank_id" , nullable = true)
	
	private Bank bank;
	
	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}