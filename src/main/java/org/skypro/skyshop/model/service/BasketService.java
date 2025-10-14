package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException(id);
        }
        productBasket.addProdToBasket(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItems = productBasket.getProdBasket()
                .entrySet()
                .stream()
                .map(el -> new BasketItem(storageService.getProductById(el.getKey()).orElseThrow(), el.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new UserBasket(basketItems);
    }

}
