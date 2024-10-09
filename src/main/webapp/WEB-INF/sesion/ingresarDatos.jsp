<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Continuar registro</title>
    <link href='https://fonts.googleapis.com/css?family=Source+Sans+3' rel='stylesheet'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/css/ingresarDatos.css">
    <title>Registro de Usuario</title>
</head>
<body>
<main class="container-fluid p-0">
    <form class="rectangle-1 text-wrap" id="form-registrar" action="${pageContext.request.contextPath}/ingresardatos" method="POST">

        <!-- Imagen del registro -->
        <img src="${pageContext.request.contextPath}/media/images/Flamin-Go.webp" alt="Flamin-Go" class="image-placeholder-1">
        <h1 class="w-100 tituloComDato"> Completar datos </h1>

        <!-- Icono de usuario y boton de elegir imagen -->
        <figure>
            <i class="fa-solid fa-circle-user" id="icono-usuario2"></i>
            <div>
                <button type="button" class="btn btn-primary btn-block buttonElegirImagen" id="buttonElegirImagen">Elegir imagen</button>
                <input type="file" id="inputImagen" accept="image/*" style="display: none;">
            </div>
        </figure>

        <!-- Contraseña -->
        <div class="form-floating mb-3">
            <input type="password" class="form-control" id="inputPassword" name="contraseña" placeholder="Contraseña" required>
            <label for="inputPassword">Contraseña</label>
        </div>

        <!-- Repetir contraseña -->
        <div class="form-floating mb-3">
            <input type="password" class="form-control" id="inputRepeatPassword" name="repetirContraseña" placeholder="Repetir contraseña" required>
            <label for="inputRepeatPassword">Repetir contraseña</label>
        </div>

        <!-- Nombre y Apellido en una sola línea -->
        <div class="row g-2">
            <div class="col-md-6 form-floating">
                <input type="text" class="form-control" id="inputNombre" name="nombre" placeholder="Nombre" required>
                <label for="inputNombre">Nombre</label>
            </div>
            <div class="col-md-6 form-floating">
                <input type="text" class="form-control" id="inputApellido" name="apellido" placeholder="Apellido" required>
                <label for="inputApellido">Apellido</label>
            </div>
        </div>

        <!-- Date Picker -->
        <div class="col-md-6 form-floating date-picker-1">
            <input type="date" class="form-control" id="inputFecha" name="fecha" placeholder="Fecha" required>
            <label for="inputFecha">Fecha</label>
        </div>

        <!-- Sitio web -->
        <div class="form-floating">
            <input type="text" class="form-control text-input-sitioWeb" id="inputSitioWeb" name="sitioWeb" placeholder="Sitio web">
            <label for="inputSitioWeb">Sitio web</label>
        </div>

        <!-- Compañía -->
        <div class="form-floating">
            <input type="text" class="form-control text-input-sitioWeb" id="inputCompania" name="compania" placeholder="Compañía">
            <label for="inputCompania">Compañía</label>
        </div>

        <div id="error-mensajes" style="color: red;">
        </div>

        <div class="row g-2">
            <div class="col-md-6">
                <button type="button" id="button-cancelar"> Cancelar</button>
            </div>

            <div class="col-md-6">
                <button type="button" id="button-registrar"> Registrar</button>
            </div>
        </div>
    </form>
</main>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const buttonRegistrar = document.getElementById('button-registrar');
        const buttonCancelar = document.getElementById('button-cancelar');
        const sitioWebInput = document.getElementById('inputSitioWeb');
        const companiaInput = document.getElementById('inputCompania');
        const nombreInput = document.getElementById('inputNombre');
        const apellidoInput = document.getElementById('inputApellido');
        const passwordInput = document.getElementById('inputPassword');
        const repeatPasswordInput = document.getElementById('inputRepeatPassword');
        const fechaInput = document.getElementById('inputFecha');

        const errores = {};

        function validarCampoTexto(input, minLength, fieldName) {
            if (input.value.trim().length < minLength) {
                errores[fieldName] = `${fieldName} debe tener al menos ${minLength} caracteres.`;
                input.classList.add('is-invalid');
            } else {
                delete errores[fieldName];
                input.classList.remove('is-invalid');
            }
            mostrarErrores();
        }

        function validarContraseñas() {
            const contraseña = passwordInput.value;
            const repetirContraseña = repeatPasswordInput.value;

            if (contraseña.length < 8) {
                errores['contraseña'] = 'La contraseña debe tener al menos 8 caracteres.';
                passwordInput.classList.add('is-invalid');
            } else {
                delete errores['contraseña'];
                passwordInput.classList.remove('is-invalid');
            }

            if (contraseña !== repetirContraseña) {
                errores['repetirContraseña'] = 'Las contraseñas no coinciden.';
                repeatPasswordInput.classList.add('is-invalid');
            } else {
                delete errores['repetirContraseña'];
                repeatPasswordInput.classList.remove('is-invalid');
            }

            mostrarErrores();
        }

        function validarFecha() {
            const fechaActual = new Date().toISOString().split('T')[0];

            if (fechaInput.value > fechaActual) {
                errores['fecha'] = 'La fecha no puede ser posterior a la actual.';
                fechaInput.classList.add('is-invalid');
            } else if (fechaInput.value === "" || fechaInput.value === null) {
                errores['fecha'] = 'Se debe ingresar una fecha';
                fechaInput.classList.add('is-invalid');
            } else {
                delete errores['fecha'];
                fechaInput.classList.remove('is-invalid');
            }
            mostrarErrores();
        }

        function validarSitioWeb() {
            const sitioWeb = sitioWebInput.value.trim();
            if (!sitioWeb || !validarUrl(sitioWeb)) {
                errores['sitioWeb'] = 'La URL del sitio web no es válida. Debe tener un formato correcto.';
                sitioWebInput.classList.add('is-invalid');
            } else {
                delete errores['sitioWeb'];
                sitioWebInput.classList.remove('is-invalid');
            }
            mostrarErrores();
        }

        function validarNombreSinNumeros(nombre) {
            const regex = /\d/; // Verifica si hay dígitos en la cadena
            return !regex.test(nombre); // Devuelve true si no hay dígitos
        }

        function validarNombre() {
            const nombre = nombreInput.value.trim();
            if (!validarNombreSinNumeros(nombre)) {
                errores['nombre'] = 'El nombre no puede contener números.';
                nombreInput.classList.add('is-invalid');
            } else {
                delete errores['nombre'];
                nombreInput.classList.remove('is-invalid');
            }
            mostrarErrores();
        }

        function mostrarErrores() {
            const errorDiv = document.getElementById('error-mensajes');
            errorDiv.innerHTML = ''; // Limpiar mensajes anteriores
            for (let key in errores) {
                const errorMensaje = document.createElement('p');
                errorMensaje.classList.add('text-danger'); // Clase de Bootstrap para texto rojo
                errorMensaje.textContent = errores[key];
                errorDiv.appendChild(errorMensaje);
            }
        }

        // Validaciones en tiempo real
        nombreInput.addEventListener('input', function() {
            validarCampoTexto(nombreInput, 3, 'Nombre');
            validarNombre(); // Llamada a la función de validación de números
        });

        apellidoInput.addEventListener('input', function() {
            validarCampoTexto(apellidoInput, 3, 'Apellido');
        });

        passwordInput.addEventListener('input', validarContraseñas);
        repeatPasswordInput.addEventListener('input', validarContraseñas);

        fechaInput.addEventListener('change', validarFecha);
        sitioWebInput.addEventListener('input', validarSitioWeb);
        companiaInput.addEventListener('input', function() {
            validarCampoTexto(companiaInput, 1, 'Compañía');
        });

        buttonRegistrar.addEventListener('click', function() {
            validarCampoTexto(nombreInput, 3, 'Nombre');
            validarCampoTexto(apellidoInput, 3, 'Apellido');
            validarContraseñas();
            validarFecha();
            validarSitioWeb();
            validarCampoTexto(companiaInput, 1, 'Compañía');
            
            // Si no hay errores, puedes proceder a enviar el formulario
            if (Object.keys(errores).length === 0) {
                document.getElementById('form-registrar').submit();
            }
        });

        buttonCancelar.addEventListener('click', function() {
            // Lógica para cancelar el registro
            window.location.href = '/ruta_a_la_pagina_principal';
        });
    });

    function validarUrl(url) {
        const regex = /^(https?:\/\/)?(www\.)?[a-zA-Z0-9-]+\.[a-zA-Z]{2,}(:[0-9]+)?(\/.*)?$/;
        return regex.test(url);
    }
</script>
    <script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
