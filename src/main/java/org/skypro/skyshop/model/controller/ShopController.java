package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping(path = "/products")
    public Collection<Searchable> getAllProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping(path = "/articles")
    public Collection<Searchable> getAllArticles() {
        return storageService.getAllArticles();
    }

    @GetMapping(path = "/search")
    public Collection<SearchResult> pattern(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping(path = "shop/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProduct(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping(path = "shop/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }


}
