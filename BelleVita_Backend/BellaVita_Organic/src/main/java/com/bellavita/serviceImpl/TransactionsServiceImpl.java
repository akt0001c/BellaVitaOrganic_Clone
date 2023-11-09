package com.bellavita.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bellavita.entity.TransactionStatus;
import com.bellavita.entity.Transactions;
import com.bellavita.entity.Users;
import com.bellavita.exceptions.OperationFaliureException;
import com.bellavita.exceptions.TransactionNotFoundException;
import com.bellavita.exceptions.UserNotLoggedInException;
import com.bellavita.repository.TransactionsRepository;
import com.bellavita.repository.UsersRepository;
import com.bellavita.service.TransactionsService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionsServiceImpl implements TransactionsService {
	
	private TransactionsRepository trepo;
	private UsersRepository urepo;

	
	@Autowired
	public void setUrepo(UsersRepository urepo) {
		this.urepo = urepo;
	}

	@Autowired
	public void setTrepo(TransactionsRepository trepo) {
		this.trepo = trepo;
	}
	
	/**
	 * It is used to get transaction details of a particular transaction  using its id
	 * @author Ankit Choubey
	 * @exception TransactionNotFoundException
	 * @param Integer transactionId
	 * @return Transactions
	 */
	@Override
	public Transactions getTransactionById(Integer transactionId) {
		Transactions transaction= trepo.findById(transactionId).orElseThrow(()-> new TransactionNotFoundException("Transaction not found or invalid transaction id given"));
		return transaction;
	}

	/**
	 * It is used to change the status of the transation using its id 
	 * @author Ankit Choubey
	 * @exception OperationFaliureExcepiton , TransactionNotFoundException
	 * @param Integer TransactionId,String status
	 * @return Transactions 
	 */
	@Override
	public Transactions changeStatus(Integer transactionId, String status) {
		Transactions transaction= getTransactionById(transactionId);
		try {
			 transaction.setStatus(TransactionStatus.valueOf(TransactionStatus.class, status));
			 
			 
		}catch(IllegalArgumentException | NullPointerException exp) {
			throw new OperationFaliureException("Operation Faliure");
		}
		return trepo.save(transaction);
	}

	

	/**
	 * It is used to get all the transactions for one user using its email but user should be logged in to use this method because we are getting email from authentication
	 * @author Ankit Choubey
	 * @exception UserNotLoggedInException , TransactionNotFoundException
	 * @param String email
	 * @return List<Transactions> 
	 */
	@Override
	public List<Transactions> getTransactionsForOneUser(String email) {
		Users user= urepo.findByEmail(email).orElseThrow(()->new UserNotLoggedInException("User should longin first"));
		List<Transactions> res= user.getTransactions().stream().toList();
		if(res.isEmpty())
			  throw new TransactionNotFoundException("No Transaction found");
		
		return res;
	}


	/**
	 * It is used to get all the transactions
	 * @author Ankit Choubey
	 * @exception  TransactionNotFoundException
	 * @return Transactions 
	 */
	@Override
	public List<Transactions> getAllTransactions() {
		List<Transactions> res= trepo.findAll();
		if(res.isEmpty())
			  throw new TransactionNotFoundException("NO Transaction Found");
		return res;
	}


	/**
	 * It is used to the list of transactions in between two given dates but user should be logged in.
	 * @author Ankit Choubey
	 * @exception  TransactionNotFoundException,UserNotLoggedInException
	 * @param String userEmail,LocalDate d1,LocalDate d2
	 * @return List<Transactions> 
	 */
	@Override
	public List<Transactions> getTransactionsForPeriod(String email, LocalDate d1, LocalDate d2) {
		Users user= urepo.findByEmail(email).orElseThrow(()->new UserNotLoggedInException("User should longin first"));
		List<Transactions> res= trepo.findByTimestampBetween(user.getUserID(), d1, d2);
		if(res.isEmpty())
			  throw new TransactionNotFoundException("Transaction not found");
		return res;
	}

}
