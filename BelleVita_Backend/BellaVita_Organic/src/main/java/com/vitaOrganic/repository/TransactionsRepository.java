package com.vitaOrganic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitaOrganic.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

  @Query("select trans from Transactions trans where trans.user= ?1 and trans.transactionDate between ?2 and ?3")
  public List<Transactions> findByTimestampBetween(Integer id, LocalDate tstamp1,LocalDate tstamp2);
}
