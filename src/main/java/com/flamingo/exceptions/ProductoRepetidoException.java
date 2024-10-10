package com.flamingo.exceptions;

// Indica la existencia de un producto repetido dentro de una categoría.

@SuppressWarnings("serial")
public class ProductoRepetidoException extends Exception {

    public ProductoRepetidoException(String string) {
        super(string);
    }

}
