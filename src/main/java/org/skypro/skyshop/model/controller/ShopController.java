package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
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
}
