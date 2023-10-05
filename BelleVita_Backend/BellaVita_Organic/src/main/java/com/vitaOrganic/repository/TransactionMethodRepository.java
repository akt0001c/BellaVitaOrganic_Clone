package com.vitaOrganic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitaOrganic.entity.TransactionMethod;

@Repository
public interface TransactionMethodRepository extends JpaRepository<TransactionMethod, Integer> {

}
