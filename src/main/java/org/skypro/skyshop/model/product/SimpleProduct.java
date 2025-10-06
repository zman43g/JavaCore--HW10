package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String productName, UUID id, int price) {
        super(productName, id);
        if (price < 1) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        } else {
            this.price = price;
        }
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public int getDiscount() {
        return 0;
    }
}
