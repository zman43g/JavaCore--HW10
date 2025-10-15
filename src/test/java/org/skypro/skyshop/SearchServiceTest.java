package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    public Collection<Searchable> getAllProducts() {
        Product product = new SimpleProduct("Сыр", UUID.randomUUID(), 23);
        Product product2 = new SimpleProduct("Сок", UUID.randomUUID(), 30);
        Article article = new Article("О пользе крепкого сна", "Крепкий сон полезен для здоровья.", UUID.randomUUID());
        Map<UUID, Product> productMap = new HashMap<>();
        Map<UUID, Article> articleMap = new HashMap<>();
        productMap.put(product.getId(), product);
        productMap.put(product2.getId(), product2);
        articleMap.put(article.getId(), article);
        Collection<Searchable> allProductsAndArticles = new ArrayList<>();
        allProductsAndArticles.addAll(productMap.values());
        allProductsAndArticles.addAll(articleMap.values());
        return (allProductsAndArticles);
    }

    @Test
    public void storageServiceIsEmpty() {
        when(storageService.uniteSearchable()).thenReturn(Collections.emptyList());
        List<SearchResult> results = searchService.search("abc");
        Assertions.assertTrue(results.isEmpty());

    }

    @ParameterizedTest
    @ValueSource(strings = {"Планшет", "Утюг", "Ветка"})
    public void storageServiceHasNoEqualObject(String query) {
        Collection<Searchable> uselessObj = getAllProducts();
        when(storageService.uniteSearchable()).thenReturn(uselessObj);
        List<SearchResult> results = searchService.search(query);
        Assertions.assertTrue(results.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Сок", "сыр", "сон"})
    public void storageServiceHasEqualObject(String query) {
        Collection<Searchable> Obj = getAllProducts();
        when(storageService.uniteSearchable()).thenReturn(Obj);
        List<SearchResult> results = searchService.search(query);
        Assertions.assertFalse(results.isEmpty());
    }

}
