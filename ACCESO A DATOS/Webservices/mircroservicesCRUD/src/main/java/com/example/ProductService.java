package com.example;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product update(Integer id, Product productValue) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return null;
        }

        product.setName(productValue.getName());
        product.setDescription(productValue.getDescription());
        product.setPrice(productValue.getPrice());
        product.setQuantity(productValue.getQuantity());

        return productRepository.save(product);
    }

    public boolean delete(Integer id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return false;
        }

        productRepository.delete(product);
        return true;
    }
}