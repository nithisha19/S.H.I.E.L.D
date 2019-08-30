package com.dbs.olb.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ParamKlass {
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date fromDate;
	 
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date toDate;
	 
	long id;
	String password;
	
	
	public long getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
}
