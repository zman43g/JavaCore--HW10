package org.skypro.skyshop.model.exceptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Такого товара нет");
    }
}
