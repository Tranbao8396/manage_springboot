package com.stock.manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportRepository extends JpaRepository<Import, Integer> {
    @Query("SELECT SUM(i.import_price) * SUM(i.quantity) FROM Import i")
    public Float getPriceImportAll();

    @Query("SELECT SUM(i.quantity) FROM Import i")
    public int getQuantityImportAll();
}
