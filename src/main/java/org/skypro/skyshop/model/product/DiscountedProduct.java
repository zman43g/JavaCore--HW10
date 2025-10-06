package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;

    public DiscountedProduct(String productName, UUID id, int basePrice, int discount) {
        super(productName,id);
        if (basePrice < 1) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        } else {
            this.basePrice = basePrice;
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100 %");
        } else {
            this.discount = discount;
        }
    }

    @Override
    public int getPrice() {
        return (basePrice - basePrice * discount / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public int getDiscount() {
        return discount;
    }

}
