package com.vitaOrganic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitaOrganic.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
public Optional<Users> findByEmail(String email);
public Optional<Users> findByEmailAndFirstName(String email,String FirstName);
}
