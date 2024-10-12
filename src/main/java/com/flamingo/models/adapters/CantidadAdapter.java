package com.flamingo.models.adapters;

import java.io.IOException;

import com.flamingo.models.Cantidad;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class CantidadAdapter extends TypeAdapter<Cantidad> {

    @Override
    public void write(JsonWriter out, Cantidad value) throws IOException {
        out.beginObject();
        out.name("cantidad").value(value.getCantidad());
        out.name("nombreProducto").value(value.getProducto().getNombreProducto());
        out.name("descripcion").value(value.getProducto().getDescripcion());
        out.name("numReferencia").value(value.getProducto().getNumReferencia());
        out.name("precio").value(value.getProducto().getPrecio());
        out.endObject();
    }

    @Override
    public Cantidad read(JsonReader in) throws IOException {
        // Implementar la lógica de deserialización si es necesario
        return null;
    }
}
