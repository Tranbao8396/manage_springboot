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
@Table(name = "imports")
public class Import implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int sup_id;
    private String book_name;
    private int quantity;
    private float import_price;
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sup_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BookSupplier bookSupplier;

    public BookSupplier getBookSupplier() {
        return bookSupplier;
    }
    public void setBookSupplier(BookSupplier bookSupplier) {
        this.bookSupplier = bookSupplier;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSup_id() {
        return sup_id;
    }
    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }
    public String getBook_name() {
        return book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public float getImport_price() {
        return import_price;
    }
    public void setImport_price(float import_price) {
        this.import_price = import_price;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
}
