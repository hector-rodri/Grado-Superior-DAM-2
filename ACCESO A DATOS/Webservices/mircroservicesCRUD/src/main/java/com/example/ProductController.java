package com.example;

import org.springframework.web.bind.annotation.*;//Import all libraries
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController//Annotation to indicate that this class is a REST controller
@RequestMapping("/products")//Annotation to specify the base URL for all endpoints in this controller
public class ProductController {//Controller to handle HTTP requests related to products

    private ProductService productService;//Service to handle business logic related to products

    public ProductController(ProductService productService) {//Constructor to initialize the service
        this.productService = productService;
    }

    @PostMapping//Annotation to specify that this method will handle POST requests to the specified URL
    public ResponseEntity<?> create(@RequestBody Product product) {//Annotation to specify that the product will be sent in the request body
        Product saved = productService.create(product);//Call the service to create a new product

        if (saved == null) {//If the product could not be created, return a bad request response
            return new ResponseEntity<>("Product could not be created", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(saved, HttpStatus.CREATED);//If the product was created successfully, return the created product and a created response
    }

    @GetMapping//Annotation to specify that this method will handle GET requests to the specified URL
    public ResponseEntity<?> getAll() {//Call the service to get all products
        List<Product> products = productService.getAll();//If no products were found, return a not found response

        if (products.isEmpty()) {//If no products were found, return a not found response
            return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);//If products were found, return the list of products and an OK response
    }

    @GetMapping("/{id}")//Annotation to specify that this method will handle GET requests to the specified URL with an ID parameter
    public ResponseEntity<?> getOne(@PathVariable Integer id) {//Annotation to specify that the ID will be sent as a path variable
        Product product = productService.getById(id);//Call the service to get a product by ID

        if (product == null) {//If the product was not found, return a not found response
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);//If the product was found, return the product and an OK response
    }

    @PutMapping("/{id}")//Annotation to specify that this method will handle PUT requests to the specified URL with an ID parameter
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Product data) {//Annotation to specify that the ID will be sent as a path variable and the updated product data will be sent in the request body
        Product updated = productService.update(id, data);//Call the service to update a product by ID

        if (updated == null) {//If the product was not found or could not be updated, return a not found response
            return new ResponseEntity<>("Product could not be updated", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updated, HttpStatus.OK);//If the product was updated successfully, return the updated product and an OK response
    }

    @DeleteMapping("/{id}")//Annotation to specify that this method will handle DELETE requests to the specified URL
    public ResponseEntity<?> delete(@PathVariable Integer id) {//Annotation to specify that the ID will be sent as a path variable
        boolean deleted = productService.delete(id);//Call the service to delete a product by ID

        if (!deleted) {//If the product was not found or could not be deleted, return a not found response
            return new ResponseEntity<>("Product could not be deleted", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);//If the product was deleted successfully, return a success message and an OK response
    }
}