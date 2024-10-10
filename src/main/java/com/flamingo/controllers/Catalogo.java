package com.flamingo.controllers;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

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

    private List<Producto> listaDeProductos;
    private List<Categoria> listaDeCategorias;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            this.listaDeProductos = obtenerListaDeProductos();
            this.listaDeCategorias = obtenerListaDeCategorias(); 

            request.setAttribute("productos", listaDeProductos);
            request.setAttribute("categorias", listaDeCategorias);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalogo/catalogo.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la excepción en la consola
            // Puedes redirigir a una página de error aquí
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
        imagenesZucaritas.add("img/Zucaritas.webp");

        List<String> imagenesCerealDulce = new ArrayList<>();
        imagenesCerealDulce.add("img/cereal1.webp");

        List<String> imagenesOreo = new ArrayList<>();
        imagenesOreo.add("img/oreo.webp");
        imagenesOreo.add("img/oreo1.webp");

        List<String> imagenesPastaIntegral = new ArrayList<>();
        imagenesPastaIntegral.add("img/pasta.webp");

        List<String> imagenesChocolateAmargo = new ArrayList<>();
        imagenesChocolateAmargo.add("img/chocolate.webp");
        imagenesChocolateAmargo.add("img/chocolate1.webp");

        List<String> imagenesCerealSaludable = new ArrayList<>();
        imagenesCerealSaludable.add("img/cereal.webp");

        List<String> imagenesCervezaArtesanal = new ArrayList<>();
        imagenesCervezaArtesanal.add("img/cerveza_artesanal.webp");

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
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
