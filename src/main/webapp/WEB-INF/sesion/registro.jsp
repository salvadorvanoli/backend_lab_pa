<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'> <!-- Importamos la letra -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/css/registrar.css">
</head>
<body>
    <main class="container-fluid p-0 d-flex">
        <article class="rectangle-1 text-wrap">
            <!-- Imagen del registro -->
            <img src="${pageContext.request.contextPath}/media/images/Flamin-Go.webp" alt="Flamin-Go" class="image-placeholder-1">
            <h1 class="w-100 textoRegistrar">Registrar</h1>
    
            <!-- Email -->
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" required>
                <label for="floatingInput">Correo electrónico</label>
                <div class="invalid-feedback" id="emailWarning"></div> <!-- Para el mensaje de advertencia -->
            </div>

            <!-- Nickname -->
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingInputNickname" placeholder="Nickname" required>
                <label for="floatingInputNickname">Nickname</label>
                <div class="invalid-feedback" id="nicknameWarning"></div> <!-- Para el mensaje de advertencia -->
            </div>
            
            <p id="IniciarSesion" class="text-primary text-decoration-underline" style="cursor: pointer;">
                ¿Ya tienes una cuenta? Click aquí para iniciar sesión.
            </p>            
    
            <div class="row g-2">
                <div class="col-md-6">
                    <button type="button" class="button-cancelar">Cancelar</button>    
                </div>    
    
                <div class="col-md-6">
                    <button type="button" class="button-registrar">Registrar</button>    
                </div> 
            </div>
        </article>    
    </main>

    <!-- JavaScript -->
    <script>
        document.addEventListener('DOMContentLoaded', function() { 
            const buttonRegistrar = document.querySelector('.button-registrar');
            const emailInput = document.getElementById('floatingInput');
            const nicknameInput = document.getElementById('floatingInputNickname');
            const emailWarning = document.getElementById('emailWarning');
            const nicknameWarning = document.getElementById('nicknameWarning');

            // Función para validar el formato de correo electrónico
            function esCorreoValido(email) {
                const regex = /^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$/;
                return regex.test(email);
            }

            // Verificar email
            emailInput.addEventListener('input', function() {
                const email = emailInput.value.trim();
                const usuariosRegistrados = JSON.parse(localStorage.getItem('usuarios')) || [];

                const emailExiste = usuariosRegistrados.some(user => user.email === email);

                if (!esCorreoValido(email)) {
                    emailWarning.textContent = 'Por favor, introduce un correo electrónico válido.';
                    emailInput.classList.add('is-invalid');
                } else if (emailExiste) {
                    emailWarning.textContent = 'Este correo ya está registrado.';
                    emailInput.classList.add('is-invalid');
                } else {
                    emailWarning.textContent = '';
                    emailInput.classList.remove('is-invalid');
                }
            });

            // Verificar nickname
            nicknameInput.addEventListener('input', function() {
                const nickname = nicknameInput.value.trim();
                const usuariosRegistrados = JSON.parse(localStorage.getItem('usuarios')) || [];

                const nicknameExiste = usuariosRegistrados.some(user => user.nickname === nickname);

                if (nicknameExiste) {
                    nicknameWarning.textContent = 'Este nickname ya está registrado.';
                    nicknameInput.classList.add('is-invalid');
                } else {
                    nicknameWarning.textContent = '';
                    nicknameInput.classList.remove('is-invalid');
                }
            });

            buttonRegistrar.onclick = function() {
                const email = emailInput.value.trim();
                const nickname = nicknameInput.value.trim();

                if (!email || !nickname) {
                    alert('Por favor, completa todos los campos.');
                    return;
                }

                if (!esCorreoValido(email)) {
                    alert('Por favor, introduce un correo electrónico válido.');
                    emailInput.classList.add('is-invalid');
                    return;
                }

                const usuariosRegistrados = JSON.parse(localStorage.getItem('usuarios')) || [];

                const usuarioExistente = usuariosRegistrados.find(user => user.email === email || user.nickname === nickname);

                if (usuarioExistente) {
                    alert('Ya existe un usuario registrado con ese email o nickname.');
                } else {
                    const credencialesTemp = {
                        email: email,
                        nickname: nickname
                    };
                    localStorage.setItem('credencialesTemp', JSON.stringify(credencialesTemp));

                    alert('Credenciales temporales guardadas. Procede al siguiente paso.');
                    window.location.href = 'ingresarDatos.html';
                }
            };
        });

        document.addEventListener('DOMContentLoaded', function() {
            const IniciarSesion = document.getElementById('IniciarSesion');

            IniciarSesion.onclick = function() {
                window.location.href = 'iniciarSesion.html';
            };
        });
    </script>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
