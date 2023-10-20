package com.vitaOrganic.servicesImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vitaOrganic.entity.Transactions;
import com.vitaOrganic.services.TransactionsService;

@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Override
	public Transactions changeStatus(Integer transactionId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transactions getTransactionById(Integer transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> getTransactionsForOneUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> getAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> getTransactionsForPeriod(String email, LocalDate d1, LocalDate d2) {
		// TODO Auto-generated method stub
		return null;
	}

}
