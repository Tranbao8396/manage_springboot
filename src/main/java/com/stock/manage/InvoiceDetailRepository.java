package com.stock.manage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {
    @Query("SELECT i FROM InvoiceDetail i WHERE i.invoice_id = ?1")
    public List<InvoiceDetail> getInvoiceDetailByInvoiceId(int invoiceId);

    @Query("SELECT i FROM InvoiceDetail i WHERE i.invoice_id = ?1 GROUP BY i.item")
    public List<InvoiceDetail> getInvoiceDetailGroupByInvoiceId(int invoiceId);

    @Query("SELECT SUM(i.quantity) FROM InvoiceDetail i WHERE i.invoice_id = ?1 AND i.item = ?2")
    public Integer getInvoiceDetailQuantity(int invoiceId, int itemId);

    @Query("SELECT SUM(i.price) FROM InvoiceDetail i WHERE i.invoice_id = ?1")
    public Float getTotalPrice(int invoiceId);

    @Query("SELECT SUM(i.price) FROM InvoiceDetail i")
    public Float getTotalPriceAll();
}
