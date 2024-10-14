package com.flamingo.controllers;
 
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Locale;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.flamingo.models.Producto;
import com.flamingo.exceptions.OrdenDeCompraNoExisteException;
import com.flamingo.models.Categoria;
import com.flamingo.models.Comentario;


@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private List<Producto> listaDeProductos = obtenerListaDeProductos();
    private List<Categoria> listaDeCategorias = obtenerListaDeCategorias();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.listaDeProductos = obtenerListaDeProductos();
            this.listaDeCategorias = obtenerListaDeCategorias();

            // Asegurarse de que las listas no sean nulas
            if (listaDeProductos == null) {
                listaDeProductos = new ArrayList<>();
            }
            if (listaDeCategorias == null) {
                listaDeCategorias = new ArrayList<>();
            }

            request.setAttribute("productos", this.listaDeProductos);
            request.setAttribute("categorias", this.listaDeCategorias);
            
            // Serializar las listas a JSON
            String categoriasJSON = ListaCategoriasToJson(listaDeCategorias);
            String productosJSON = ListaPproductosToJson(listaDeProductos);

            request.setAttribute("productosJSON", productosJSON);
            request.setAttribute("categoriasJSON", categoriasJSON);

            // Redirigir a la página JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalogo/catalogo.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud.");
        }
    }


    private List<Producto> obtenerListaDeProductos() {
        List<Producto> productos = new ArrayList<>();

        // Crear categorías
        Categoria categoriaCereales = new Categoria("Cereales", true, null);
        Categoria categoriaDulce = new Categoria("Dulce", true, categoriaCereales);
        Categoria categoriaComida = new Categoria("Comida", true, null);
        Categoria categoriaSaludable = new Categoria("Saludable", true, null);
        Categoria categoriaBebida = new Categoria("Bebida", true, null);
        Categoria categoriaAlcohólica = new Categoria("Alcohólica", true, categoriaBebida);
        Categoria categoriaGalletas = new Categoria("Galletas", true, categoriaDulce);
        Categoria categoriaPasta = new Categoria("Pasta", true, categoriaSaludable);
        Categoria categoriaChocolate = new Categoria("Chocolate", true, categoriaDulce);
        
        // Crear comentarios para los productos
        List<Comentario> comentariosZucaritas = new ArrayList<>();
        comentariosZucaritas.add(new Comentario("1", "No me gusta esto 0 proteína", null, null, null));
        comentariosZucaritas.add(new Comentario("8", "Mmm q ñicooo", null, null, null));

        List<Comentario> comentariosCerealDulce = new ArrayList<>();
        List<Comentario> comentariosOreo = new ArrayList<>();
        List<Comentario> comentariosPastaIntegral = new ArrayList<>();
        List<Comentario> comentariosChocolateAmargo = new ArrayList<>();
        List<Comentario> comentariosCerealSaludable = new ArrayList<>();
        List<Comentario> comentariosCervezaArtesanal = new ArrayList<>();

        // Crear imágenes para los productos
        List<String> imagenesZucaritas = new ArrayList<>();
        imagenesZucaritas.add("media/images/Zucaritas.webp");

        List<String> imagenesCerealDulce = new ArrayList<>();
        imagenesCerealDulce.add("media/images/cereal1.webp");

        List<String> imagenesOreo = new ArrayList<>();
        imagenesOreo.add("media/images/oreo.webp");
        imagenesOreo.add("media/images/oreo1.webp");

        List<String> imagenesPastaIntegral = new ArrayList<>();
        imagenesPastaIntegral.add("media/images/pasta.webp");

        List<String> imagenesChocolateAmargo = new ArrayList<>();
        imagenesChocolateAmargo.add("media/images/chocolate.webp");
        imagenesChocolateAmargo.add("media/images/chocolate1.webp");

        List<String> imagenesCerealSaludable = new ArrayList<>();
        imagenesCerealSaludable.add("media/images/cereal.webp");

        List<String> imagenesCervezaArtesanal = new ArrayList<>();
        imagenesCervezaArtesanal.add("media/images/cerveza_artesanal.webp");

        // Crear productos
        Producto zucaritas = new Producto(
            "Zucaritas",
            "Cereal dulce y crujiente con mucha azúcar, ideal para empezar el día con energía.",
            "Mucha azúcar, Para desayunar, Poco saludable",
            777,
            300.0f,
            imagenesZucaritas,
            List.of(categoriaComida, categoriaDulce),
            null, // Proveedor
            3,
            2,
            "DD Market",
            comentariosZucaritas
        );

        Producto cerealDulce = new Producto(
            "Cereal Dulce",
            "Cereal con sabor dulce para los amantes del azúcar. Ideal para el desayuno.",
            "Dulce al paladar, Ideal para desayuno, Contiene gluten",
            778,
            150.0f,
            imagenesCerealDulce,
            List.of(categoriaComida, categoriaDulce),
            null, // Proveedor
            1,
            100,
            "Tienda A",
            comentariosCerealDulce
        );

        Producto oreo = new Producto(
            "Oreo",
            "Galletas de chocolate con un relleno cremoso, perfectas para acompañar con leche.",
            "Sabor chocolate intenso, Relleno cremoso, Perfecto para postre",
            779,
            200.0f,
            imagenesOreo,
            List.of(categoriaComida, categoriaDulce, categoriaGalletas),
            null, // Proveedor
            5,
            1000,
            "Tienda B",
            comentariosOreo
        );

        Producto pastaIntegral = new Producto(
            "Pasta Integral",
            "Pasta hecha con harina integral, ideal para una alimentación saludable.",
            "Alta en fibra, Baja en calorías, Ideal para dietas",
            780,
            180.0f,
            imagenesPastaIntegral,
            List.of(categoriaComida, categoriaSaludable, categoriaPasta),
            null, // Proveedor
            3,
            0,
            "Tienda C",
            comentariosPastaIntegral
        );

        Producto chocolateAmargo = new Producto(
            "Chocolate Amargo",
            "Chocolate con alto contenido de cacao, perfecto para quienes prefieren un sabor intenso.",
            "Alto en cacao, Poco azúcar, Apto para veganos",
            781,
            250.0f,
            imagenesChocolateAmargo,
            List.of(categoriaComida, categoriaDulce, categoriaChocolate),
            null, // Proveedor
            5,
            209,
            "Tienda D",
            comentariosChocolateAmargo
        );

        Producto cerealSaludable = new Producto(
            "Cereal Saludable",
            "Cereal bajo en azúcar y alto en fibra, ideal para quienes cuidan su alimentación.",
            "Bajo en azúcar, Alto en fibra, Ideal para dietas",
            782,
            220.0f,
            imagenesCerealSaludable,
            List.of(categoriaComida, categoriaSaludable),
            null, // Proveedor
            4,
            476,
            "Tienda E",
            comentariosCerealSaludable
        );

        Producto cervezaArtesanal = new Producto(
            "Cerveza Artesanal",
            "Cerveza artesanal elaborada con ingredientes naturales, sabor intenso y cuerpo robusto.",
            "500ml de cerveza artesanal, 5% de alcohol por volumen, Sabor fuerte y robusto",
            888,
            450.0f,
            imagenesCervezaArtesanal,
            List.of(categoriaBebida, categoriaAlcohólica),
            null, // Proveedor
            4,
            1101,
            "Bebidas y Más",
            comentariosCervezaArtesanal
        );

        // Agregar todos los productos a la lista
        productos.add(zucaritas);
        productos.add(cerealDulce);
        productos.add(oreo);
        productos.add(pastaIntegral);
        productos.add(chocolateAmargo);
        productos.add(cerealSaludable);
        productos.add(cervezaArtesanal);

        return productos;
    }

    private List<Categoria> obtenerListaDeCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        // Crear la categoría "Comida"
        Categoria comida = new Categoria("Comida", true, null);
        HashMap<String, Categoria> hijasComida = new HashMap<>();

        // Crear subcategoría "Dulce"
        Categoria dulce = new Categoria("Dulce", true, comida);
        dulce.setHijos(new HashMap<>()); // Inicializar hijas
        dulce.getHijos().put("Cereales", new Categoria("Cereales", true, dulce));
        dulce.getHijos().put("Galletas", new Categoria("Galletas", true, dulce));
        dulce.getHijos().put("Chocolate", new Categoria("Chocolate", true, dulce));
        
        hijasComida.put("Dulce", dulce);

        // Crear subcategoría "Saludable"
        Categoria saludable = new Categoria("Saludable", true, comida);
        saludable.setHijos(new HashMap<>()); // Inicializar hijas
        saludable.getHijos().put("Cereales Fitness", new Categoria("Cereales Fitness", true, saludable));
        saludable.getHijos().put("Pasta", new Categoria("Pasta", true, saludable));

        hijasComida.put("Saludable", saludable);

        comida.setHijos(hijasComida);
        categorias.add(comida);

        // Crear la categoría "Bebida"
        Categoria bebida = new Categoria("Bebida", true, null);
        HashMap<String, Categoria> hijasBebida = new HashMap<>();
        hijasBebida.put("Alcohólica", new Categoria("Alcohólica", true, bebida));

        bebida.setHijos(hijasBebida);
        categorias.add(bebida);

        return categorias;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			processRequest(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
    }
    
    
    private String ListaCategoriasToJson(List<Categoria> categorias) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        
        for (int i = 0; i < categorias.size(); i++) {
            Categoria cat = categorias.get(i);
            jsonBuilder.append(categoriaToJson(cat)); // Convertir cada categoría a JSON
            
            if (i < categorias.size() - 1) {
                jsonBuilder.append(","); // Agregar coma entre categorías
            }
        }
        
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private String categoriaToJson(Categoria cat) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append(String.format("{\"nombreCat\":\"%s\",\"tieneProductos\":%b,\"hijos\":[",
                cat.getNombreCat(), cat.isTieneProductos()));

        // Serializar hijos
        StringBuilder hijosJsonBuilder = new StringBuilder();
        HashMap<String, Categoria> hijosHash = cat.getHijos();
        if (hijosHash != null && !hijosHash.isEmpty()) {
            int j = 0;
            for (Categoria subcat : hijosHash.values()) {
                if (j > 0) hijosJsonBuilder.append(",");
                hijosJsonBuilder.append(categoriaToJson(subcat)); // Llamada recursiva
                j++;
            }
        }
        jsonBuilder.append(hijosJsonBuilder).append("]}");
        return jsonBuilder.toString();
    }
    
    private String ListaPproductosToJson(List<Producto> productos) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        
        for (int i = 0; i < productos.size(); i++) {
            Producto prod = productos.get(i);
            jsonBuilder.append(productoToJson(prod)); // Convertir cada producto a JSON
            
            if (i < productos.size() - 1) {
                jsonBuilder.append(","); // Agregar coma entre productos
            }
        }
        
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private String productoToJson(Producto prod) {
        String precioFormateado = String.format(Locale.US, "%.2f", prod.getPrecio()); 
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append(String.format(
            "{\"nombreProducto\":\"%s\",\"descripcion\":\"%s\",\"especificacion\":\"%s\"," +
            "\"numReferencia\":%d,\"precio\":%s,\"imagenes\":[",
            prod.getNombreProducto(), prod.getDescripcion(), prod.getEspecificacion(),
            prod.getNumReferencia(), precioFormateado)); // Usar el precio formateado

        // Serializar imágenes
        StringBuilder imagenesJsonBuilder = new StringBuilder();
        List<String> imagenes = prod.getImagenes();
        for (int j = 0; j < imagenes.size(); j++) {
            if (j > 0) imagenesJsonBuilder.append(",");
            imagenesJsonBuilder.append("\"").append(imagenes.get(j)).append("\"");
        }
        jsonBuilder.append(imagenesJsonBuilder)
            .append("],\"categorias\":[],\"proveedor\":\"")
            .append(prod.getProveedor() != null ? prod.getProveedor().toString() : "")
            .append("\",\"estrellas\":").append(prod.getEstrellas())
            .append(",\"nombreTienda\":\"").append(prod.getNombreTienda()).append("\"}");
        
        return jsonBuilder.toString();
    }



}