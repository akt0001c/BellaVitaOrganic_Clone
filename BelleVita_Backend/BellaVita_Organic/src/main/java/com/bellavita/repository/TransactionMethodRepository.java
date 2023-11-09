package com.bellavita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bellavita.entity.TransactionMethod;

@Repository
public interface TransactionMethodRepository extends JpaRepository<TransactionMethod, Integer> {

}
