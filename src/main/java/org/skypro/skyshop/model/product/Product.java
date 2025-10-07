package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String productName;
    private final UUID id;

    public Product(String productName, UUID id) {
        if (productName.isBlank()) {
            throw new IllegalArgumentException("Название продукта не должно быть пустым");

        } else {
            this.productName = productName;
        }
        this.id = id;
    }



    public abstract int getPrice();

    public abstract boolean isSpecial();

    public abstract int getDiscount();

    public String getProductName() {
        return productName;
    }

    @Override
    @JsonIgnore
    public String searchTerm() {
        return productName + " PRODUCT ";
    }

    @Override
    @JsonIgnore
    public String getStringRepresentation() {
        return " Имя " + productName + " Тип Searchable объекта PRODUCT ";
    }

    @Override
    @JsonIgnore
    public String contentFound() {
        return "PRODUCT";
    }

    @Override
    public String toString() {
        return productName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }

    @Override
    @JsonIgnore
    public String getNameOfSearchable() {
        return productName;
    }

    @Override
    public UUID getId() {
        return id;
    }

}

