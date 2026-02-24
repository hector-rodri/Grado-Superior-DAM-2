package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        Product saved = productRepository.save(product);
        if (saved == null || saved.getId() == null) {
            throw new RuntimeException("Product could not be saved.");
        }
        return saved;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(Integer id) {
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(Integer id, Product data) {
        return productRepository.findById(id).map(p -> {
            p.setName(data.getName());
            p.setDescription(data.getDescription());
            p.setPrice(data.getPrice());
            p.setQuantity(data.getQuantity());
            return productRepository.save(p);
        });
    }

    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}