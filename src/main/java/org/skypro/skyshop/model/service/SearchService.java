package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String query) {

        List<SearchResult> collect = storageService.uniteSearchable().stream()
                .filter(s -> s.searchTerm().toLowerCase().contains(query.toLowerCase()))
                .map(SearchResult::fromSearchable).collect(Collectors.toList());
        return collect;
    }

}

