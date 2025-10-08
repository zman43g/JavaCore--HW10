package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserBasket {
    private final List<BasketItem> userBasket;
    private final int total;

    public UserBasket(List<BasketItem> userBasket) {
        this.userBasket = userBasket;
        this.total = calculate(userBasket);
    }

    private static int calculate(List<BasketItem> userBasket) {
        return userBasket.stream()
                .mapToInt(item -> item.getItem().getPrice() * item.getQuantity())
                .sum();
    }

}

