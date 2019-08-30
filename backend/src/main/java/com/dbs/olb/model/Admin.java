package com.dbs.olb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long admin_id;
	private String password;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "IFSCCODE" , nullable = true)
	private Bank bank;
	
	void setBank(Bank bank) {
		this.bank = bank;
		bank.addAdmins(this);
	}

}
