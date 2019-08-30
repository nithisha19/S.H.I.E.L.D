package com.dbs.olb.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dbs.olb.model.Account;
import com.dbs.olb.model.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer >{

	@Query(value = "select * from Transaction t where t.from_account =?1 OR t.to_account =?1 order by transaction_date desc",nativeQuery = true)
	public List<Transaction> findAllByAccountId(long id);
	
	@Query(value="select  * from transaction order by transaction_date desc limit 10;",nativeQuery = true)
	public List<Transaction> miniStatement(long id);
    
	@Query(value = "select * from Transaction t where (t.from_account =?1 OR t.to_account =?1) AND (t.transaction_date "
			+ "BETWEEN ?2 AND ?3)",nativeQuery = true)
	public List<Transaction>findBetweenRanges(long Id,Date from, Date to);
	
	@Query(value="select * from transaction where status =\"pending\"",nativeQuery = true)
	 public List<Transaction>pendingTransactions();
	
	@Query(value="select sum(amount) from transaction  where transaction_date like concat(?2,'%') and from_account=?1 and status='success' ",nativeQuery = true)
	 public long getAmountByDate(long id,String formatDate);
	
	@Query(value="select count(*) from transaction  where transaction_date like  concat(?2,'%') and from_account=?1",nativeQuery=true)
	public int getCount(long id,String formatDate);
}
