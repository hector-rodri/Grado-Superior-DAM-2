package com.example;

import org.springframework.data.jpa.repository.JpaRepository;//Import all libraries
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {//Repository to handle database operations related to products

}
