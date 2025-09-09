package com.example.inventory.repository;


import com.example.inventory.model.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTransactionRepository extends
        JpaRepository<StockTransaction, Long>{
}
