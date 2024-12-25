package com.stock.manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT p FROM Products p WHERE p.book_id = ?1")
    public Products getProductbyBookId(int book_id);
}
