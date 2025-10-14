package org.skypro.skyshop.model.exceptions;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(UUID id) {
        super("Товара c id: " + id + " нет");
    }
}
