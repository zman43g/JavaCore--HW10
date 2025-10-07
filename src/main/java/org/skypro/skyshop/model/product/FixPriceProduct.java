package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private final int fixPrice;

    public FixPriceProduct(String productName, UUID id, int fixPrice) {
        super(productName,id);
        this.fixPrice = fixPrice;
    }

    @Override
    public int getPrice() {
        return fixPrice;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public int getDiscount() {
        return 0;
    }
}
