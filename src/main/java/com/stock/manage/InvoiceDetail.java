package com.stock.manage;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int invoice_id;
    private int item;
    private Integer voucher;
    private int quantity;
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Invoices invoices;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item", referencedColumnName = "book_id", insertable = false, updatable = false)
    private Products products;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "voucher", referencedColumnName = "id", insertable = false, updatable = false)
    private Voucher vouchers;

    public Products getProducts() {
        return products;
    }
    public void setProducts(Products products) {
        this.products = products;
    }

    public Invoices getInvoices() {
        return invoices;
    }
    public void setInvoices(Invoices invoices) {
        this.invoices = invoices;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getInvoice_id() {
        return invoice_id;
    }
    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }
    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getVoucher() {
        return voucher;
    }
    public void setVoucher(Integer voucher) {
        this.voucher = voucher;
    }
}
