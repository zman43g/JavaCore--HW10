package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String searchTerm();
    String contentFound();
    String getStringRepresentation();
    String getNameOfSearchable();
    UUID getId();
}
