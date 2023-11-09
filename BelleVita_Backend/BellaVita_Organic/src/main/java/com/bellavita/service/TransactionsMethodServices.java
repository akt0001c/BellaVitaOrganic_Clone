package com.bellavita.service;

import java.util.List;

import com.bellavita.entity.TransactionMethod;

public interface TransactionsMethodServices {
public TransactionMethod addTransactionMethod(TransactionMethod ob);
public TransactionMethod removeTransctionMethod(Integer id);
public List<TransactionMethod> getAllTransactionMethods();
public TransactionMethod changeStatus(Integer id);
}
