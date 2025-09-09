package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {

        this.repo = repo;
    }

    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return repo.save(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        p.setId(id);
        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // ⚠️ New Endpoint for Low Stock Products
    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts() {
        //int threshold = 3; // You can make this configurable later
        return repo.findByQuantityLessThan(5);
    }

}
