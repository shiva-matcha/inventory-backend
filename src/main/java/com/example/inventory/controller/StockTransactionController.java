package com.example.inventory.controller;


import com.example.inventory.model.StockTransaction;
import com.example.inventory.model.TransactionType;
import com.example.inventory.service.StockTransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class StockTransactionController {

    private final StockTransactionService transactionService;

    public StockTransactionController(StockTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public StockTransaction createTransaction(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam TransactionType type
    ) {
        return transactionService.createTransaction(productId, quantity, type);
    }
}
