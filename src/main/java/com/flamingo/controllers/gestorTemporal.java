package com.flamingo.controllers;
import com.flamingo.exceptions.UsuarioNoEncontrado;
import java.util.ArrayList;
import java.util.List;

import com.flamingo.models.Usuario;

public class gestorTemporal {
    private List<Usuario> usuarios;
    private static gestorTemporal instancia;
    
    public static gestorTemporal getInstance()
	{
		if(instancia == null)
			instancia = new gestorTemporal();
		return instancia;
	}

    public gestorTemporal() {
        usuarios = new ArrayList<>();
        inicializarUsuarios(); // Inicializa la lista con usuarios precargados
    }
    
 // Método para agregar un nuevo usuario
    public void agregarUsuario(String nombre, String apellido, String nickname, String tipo, 
                               String email, String fecha, String foto, String web, 
                               String empresa, String id, String contrasena) {

        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario(
            nombre, 
            apellido, 
            nickname, 
            tipo, 
            email, 
            fecha, 
            foto, 
            web, 
            empresa, 
            id, 
            contrasena, 
            new ArrayList<>(),
            new ArrayList<>() 
        );

        // Agregar el nuevo usuario a la lista de usuarios
        usuarios.add(nuevoUsuario);
    }
    // Método para inicializar algunos usuarios
    public void inicializarUsuarios() {
        usuarios.add(new Usuario("Juan", "Pérez", "juanp", "cliente", "juan@example.com", 
                                  "2022-01-01", "foto1.jpg", "www.juan.com", "Empresa Juan", 
                                  "1", "contrasena123", new ArrayList<>(), new ArrayList<>()));
        usuarios.add(new Usuario("María", "Gómez", "mariag", "administrador", "maria@example.com", 
                                  "2022-02-01", "foto2.jpg", "www.maria.com", "Empresa María", 
                                  "2", "contrasena456", new ArrayList<>(), new ArrayList<>()));
        usuarios.add(new Usuario("Carlos", "Sánchez", "carloss", "cliente", "carlos@example.com", 
                                  "2022-03-01", "foto3.jpg", "www.carlos.com", "Empresa Carlos", 
                                  "3", "contrasena789", new ArrayList<>(), new ArrayList<>()));
    }


    public Usuario buscarUsuario(String email) throws UsuarioNoEncontrado {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) || usuario.getNickname().equals(email)) {
                return usuario; // Retorna el usuario si se encuentra
            }
        }
        throw new UsuarioNoEncontrado(email);
    }

    // Método para obtener la lista de usuarios
    public List<Usuario> obtenerUsuarios() {
        return usuarios; // Retorna la lista de usuarios
    }

}
