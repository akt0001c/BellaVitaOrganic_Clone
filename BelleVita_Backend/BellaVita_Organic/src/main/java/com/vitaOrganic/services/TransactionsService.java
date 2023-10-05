package com.vitaOrganic.services;

import java.util.List;

import com.vitaOrganic.entity.Transactions;

public interface TransactionsService {
public Transactions changeStatus(Integer transactionId ,String status);
public Transactions getTransactionById(Integer transactionId);
public List<Transactions> getTransactionsForOneUser(String email);
public List<Transactions> getAllTransactions();
}
