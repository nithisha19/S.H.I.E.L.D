package com.dbs.olb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table
@Data
public class Bank {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bank_id;
	final String IFSC_CODE="aaa000";
	private String Street;
	private String city;
	private int pincode;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Account> accounts= new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Admin> admins= new HashSet<>();
	
	public void addAccount(Account account) {
		//this.accounts.add(account);
		account.setBank(this);
	}
	
	public void addAdmins(Admin admin) {
		this.admins.add(admin);
	}

}
