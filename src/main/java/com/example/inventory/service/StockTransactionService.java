package com.example.inventory.service;

import com.example.inventory.model.Product;
import com.example.inventory.model.StockTransaction;
import com.example.inventory.model.TransactionType;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.StockTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class StockTransactionService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockTransactionRepository stockTransactionRepository;

    @Autowired
    private EmailService emailService;

    public StockTransaction createTransaction(Long productId, int quantity, TransactionType type) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int currentQty = product.getQuantity();
        int updatedQty;
        if(type == TransactionType.IN){
            updatedQty = currentQty + quantity;
        }else if(type == TransactionType.OUT){
            if(currentQty - quantity < 0){
                throw new IllegalArgumentException("Stock is Less than Zero");
            }
            updatedQty = currentQty - quantity;
        }else{
            throw new IllegalArgumentException("Invalid Transaction Type");
        }

        product.setQuantity(updatedQty);
        productRepository.save(product);

        // 🔔 Send low stock alert if below threshold
        if (updatedQty < product.getMinThreshold()) {
            emailService.sendLowStockAlert(product.getName(), updatedQty);
        }

        StockTransaction tx = new StockTransaction();
        tx.setProduct(product);
        tx.setType(type);
        tx.setQuantity(quantity);
        tx.setTransactionDate(LocalDateTime.now());

        return stockTransactionRepository.save(tx);
    }
}
