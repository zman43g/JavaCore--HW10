package org.skypro.skyshop.model.basket;


import java.util.List;

public class UserBasket {
    private final List<BasketItem> userBasket;
    private final int total;

    public UserBasket(List<BasketItem> userBasket) {
        this.userBasket = userBasket;
        this.total = calculate(userBasket);
    }

    public List<BasketItem> getUserBasket() {
        return userBasket;
    }

    public int getTotal() {
        return total;
    }

    private static int calculate(List<BasketItem> userBasket) {
        return userBasket.stream()
                .mapToInt(item -> item.getItem().getPrice() * item.getQuantity())
                .sum();
    }

}

