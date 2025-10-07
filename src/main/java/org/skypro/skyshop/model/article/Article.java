package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String articleName;
    private final String articleText;
    private final UUID id;

    public Article(String articleName, String articleText, UUID id) {
        this.articleName = articleName;
        this.articleText = articleText;
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getArticleText() {
        return articleText;
    }


    @Override
    public String toString() {
        return articleName + " \n" + articleText;
    }

    @JsonIgnore
    @Override
    public String contentFound() {
        return "ARTICLE";
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return this + " ARTICLE";
    }

    @JsonIgnore
    @Override
    public String getNameOfSearchable() {
        return articleName;
    }

    @JsonIgnore
    @Override
    public String getStringRepresentation() {
        return " Имя " + articleText + " Тип Searchable объекта ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleName, article.articleName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articleName);
    }

    @Override
    public UUID getId() {
        return id;
    }
}