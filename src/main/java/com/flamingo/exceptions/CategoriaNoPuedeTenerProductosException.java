package com.flamingo.exceptions;

// Indica la inexistencia de una categoría en el sistema.

@SuppressWarnings("serial")
public class CategoriaNoPuedeTenerProductosException extends Exception {

    public CategoriaNoPuedeTenerProductosException(String string) {
        super(string);
    }

}
