package com.example;

import org.springframework.stereotype.Service;//Import all libraries
import java.util.List;

@Service//Annotation to indicate that this class is a service
public class ProductService {//Service to handle business logic related to products

    private ProductRepository productRepository;//Repository to handle database operations related to products

    public ProductService(ProductRepository productRepository) {//Constructor to initialize the repository
        this.productRepository = productRepository;
    }

    public Product create(Product product) {//Call the repository to save a new product
        return productRepository.save(product);
    }

    public List<Product> getAll() {//Call the repository to get all products
        return productRepository.findAll();
    }

    public Product getById(Integer id) {//Call the repository to get a product by ID
        return productRepository.findById(id).orElse(null);
    }

    public Product update(Integer id, Product productValue) {//Call the repository to get a product by ID and update its values
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {//If the product was not found, return null
            return null;
        }

        product.setName(productValue.getName());//Update the product's values with the new values
        product.setDescription(productValue.getDescription());
        product.setPrice(productValue.getPrice());
        product.setQuantity(productValue.getQuantity());

        return productRepository.save(product);
    }

    public boolean delete(Integer id) {//Call the repository to get a product by ID and delete it
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {//If the product was not found, return false
            return false;
        }

        productRepository.delete(product);
        return true;
    }
}