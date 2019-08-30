    
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
public class User {
	
	@Id
	private long user_id;
	private String password;
	private String first_name;
	private String last_name;
	private String middle_name;
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date dob;
	private String address;
	private String gender;
	private String ssn_no;
	private String email;
	private long mobile_no;
	
	@JsonIgnore
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Account> accounts= new HashSet<>();
	
	public void addAccount(Account account) {
		this.accounts.add(account);
		account.setUser(this);
	}	
}