package com.ironhack.BankingSystem.repository;

import com.ironhack.BankingSystem.model.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Integer> {
}
/// que extens? <Admin,xxx>???