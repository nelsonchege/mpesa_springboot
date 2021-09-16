package com.example.mpesa_springboot.Repositories;

import com.example.mpesa_springboot.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions ,String> {

}
