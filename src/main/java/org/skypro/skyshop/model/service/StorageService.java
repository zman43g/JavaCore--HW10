package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;


    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        insertData();
    }

    public Map<UUID, Product> getProductMap() {
        return productMap;
    }

    public Map<UUID, Article> getArticleMap() {
        return articleMap;
    }

    private void insertData() {

        Article[] articles = new Article[5];
        articles[0] = new Article("О вреде соли", "Соль очень вредна в больших количествах!", UUID.randomUUID());
        articles[1] = new Article("О ПДД", "Необходимо соблюдать ПДД!", UUID.randomUUID());
        articles[2] = new Article("О пользе крепкого сна", "Крепкий сон полезен для здоровья.", UUID.randomUUID());
        articleMap.put(UUID.randomUUID(), articles[0]);
        articleMap.put(UUID.randomUUID(), articles[1]);
        articleMap.put(UUID.randomUUID(), articles[2]);

        Product[] product = new Product[5];
        product[0] = new SimpleProduct("Сахар", UUID.randomUUID(), 80);
        product[1] = new SimpleProduct("Сыр", UUID.randomUUID(), 600);
        product[2] = new FixPriceProduct("Молоко", UUID.randomUUID(), 70);
        product[3] = new SimpleProduct("Соль", UUID.randomUUID(), 20);
        product[4] = new DiscountedProduct("Овсянка", UUID.randomUUID(), 200, 40);

        productMap.put(UUID.randomUUID(), product[0]);
        productMap.put(UUID.randomUUID(), product[1]);
        productMap.put(UUID.randomUUID(), product[2]);
        productMap.put(UUID.randomUUID(), product[3]);
        productMap.put(UUID.randomUUID(), product[4]);

    }

    public Collection<Searchable> getAllProducts() {
        return (new ArrayList<>(productMap.values()));
    }

    public Collection<Searchable> getAllArticles() {
        return (new ArrayList<>(articleMap.values()));
    }

    public Collection<Searchable> uniteSearchable() {
        Collection<Searchable> allProductsAndArticles = new ArrayList<>();
        {
            allProductsAndArticles.addAll(productMap.values());
            allProductsAndArticles.addAll(articleMap.values());
            return allProductsAndArticles;
        }
    }
}
