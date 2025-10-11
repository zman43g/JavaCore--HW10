package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;


public class BasketItem {
    private final Product item;
    private final Integer quantity;

    public BasketItem(Product item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Product getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
