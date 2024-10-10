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
        <div class="rectangle-1 text-wrap">
            <!-- Imagen del registro -->
            <img src="${pageContext.request.contextPath}/media/images/Flamin-Go.webp" alt="Flamin-Go"  width="50px" height="50px">
            <h1 class="w-100 textoRegistrar"> Registrar </h1>

            <!-- Formulario de registro -->
            <form action="${pageContext.request.contextPath}/ingresardatos" method="post">
                <!-- email -->
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" name="email" placeholder="name@example.com" required>
                    <label for="floatingInput">Correo electrónico</label>
                    <div class="invalid-feedback" id="emailWarning"></div> <!-- Para el mensaje de advertencia -->
                </div>

                <!-- nickname -->
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingInputNickname" name="nickname" placeholder="Nickname" required>
                    <label for="floatingInputNickname">Nickname</label>
                    <div class="invalid-feedback" id="nicknameWarning"></div> <!-- Para el mensaje de advertencia -->
                </div>

                <p id="IniciarSesion" class="text-primary text-decoration-underline" style="cursor: pointer;">
                    ¿Ya tienes una cuenta? Click aquí para iniciar sesión.
                </p>            

                <div class="row g-2">
                    <div class="col-md-6">
                        <button type="button" class="button-cancelar" onclick="window.location.href='${pageContext.request.contextPath}/home'"> Cancelar</button>   
                    </div>    

                    <div class="col-md-6">
                        <button type="submit" class="button-registrar"> Registrar</button>    
                    </div> 
                </div>
            </form>
        </div>    
    </main>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
