package com.bellavita.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bellavita.entity.TransactionMethod;
import com.bellavita.exceptions.TransactionMethodNotFoundException;
import com.bellavita.repository.TransactionMethodRepository;
import com.bellavita.service.TransactionsMethodServices;

@Service
public class TransactionsMethodServiceImpl implements TransactionsMethodServices {
	
	private TransactionMethodRepository tmrepo;

	@Autowired
	public void setTmrepo(TransactionMethodRepository tmrepo) {
		this.tmrepo = tmrepo;
	}

	/**
	 * It is used to add a new TransactionMethod into the database
	 * @author Ankit choubey
	 * @param TransactionMethod ob
	 * @return TransactionMethod
	 */
	@Override
	public TransactionMethod addTransactionMethod(TransactionMethod ob) {
		ob.setIsActive(true);
		
		return tmrepo.save(ob);
	}

	/**
	 * It is used to remove a TransactionMethod from the database using its id
	 * @author Ankit choubey
	 * @exception TransactionMethodNotFoundException
	 * @param TransactionMethod ob
	 * @return TransactionMethod
	 */
	@Override
	public TransactionMethod removeTransctionMethod(Integer id) {
		  TransactionMethod tmethod = tmrepo.findById(id).orElseThrow(()->new TransactionMethodNotFoundException("Transaction Methods not found or invalid transaction method used"));
		  tmrepo.delete(tmethod);
		return tmethod;
	}

	/**
	 * It is used to get all TransactionMethods from the database 
	 * @author Ankit choubey
	 * @exception TransactionMethodNotFoundException
	 * @return TransactionMethod
	 */
	@Override
	public List<TransactionMethod> getAllTransactionMethods() {
		List<TransactionMethod> res= tmrepo.findAll();
		   if(res.isEmpty())
			    throw new TransactionMethodNotFoundException("Transaction Methods not avaliable");
		return res;
	}
	
	
	/**
	 * It is used to change the  TransactionMethod status  from true to false or vice versa  using its id
	 * @author Ankit choubey
	 * @exception TransactionMethodNotFoundException
	 * @param Integer transactionMethodId 
	 * @return TransactionMethod
	 */
	@Override
	public TransactionMethod changeStatus(Integer id) {
		TransactionMethod tmethod = tmrepo.findById(id).orElseThrow(()->new TransactionMethodNotFoundException("Transaction Methods not found or invalid transaction method used"));
		if(tmethod.getIsActive()==true)
			   tmethod.setIsActive(false);
		else
            tmethod.setIsActive(true);
		return tmrepo.save(tmethod);
	}

}
