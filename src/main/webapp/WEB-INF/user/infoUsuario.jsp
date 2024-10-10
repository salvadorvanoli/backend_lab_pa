<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="media/css/infoUsuario.css">
</head>
<body>
    
    <jsp:include page="/WEB-INF/template/header.jsp" />
    
    <main>

        <section id="cliente-section">

            <h1>Información personal</h1>

            <div class="container-md container-fluid">

                <form>
                    <div class="row">
                        <div class="col-md-6 col-12">
            
                            <div class="form-group d-flex">
                                <div class="w-50 me-2">
                                    <input type="text" class="form-control mt-2" id="inputNombre" placeholder="Ingrese su nombre" required>
                                </div>
                                <div class="w-50">
                                    <input type="text" class="form-control mt-2" id="inputApellido" placeholder="Ingrese su apellido" required>
                                </div>
                            </div>
            
                            <div class="form-group">
                                <input type="text" class="form-control mt-2" id="inputNickname" placeholder="Ingrese su nickname" required>
                            </div>
            
                            <div class="form-group">
                                <input type="email" class="form-control mt-2" id="inputEmail" placeholder="Ingrese su correo electrónico" required>
                            </div>

                            <div class="form-group">
                                <div class="mr-2 w-50">
                                    <input type="date" class="form-control mt-2" id="inputFecha" required>
                                </div>
                            </div>

                            <div class="form-group d-flex">
                                <div class="mr-2 w-50">
                                    <button type="button" class="btn btn-primary botonRosado mt-2" id="actualizar-datos-usuario">Actualizar datos</button>
                                </div>
                                <div class="w-50">
                                    <button class="btn btn-outline-success mt-2" type="submit" id="cerrar-sesion">Cerrar sesión</button>
                                </div>
                            </div>
                        </div>
            
                        <div class="col-md-6 col-12 centro">
                            <div> 
                                <img src="img/Flamin-Go.webp" alt="Foto de perfil" id="fotoPerfilUsuario" class="img-fluid">
                            </div>
                            <div>
                                <input type="file" id="inputFile" class="btn btn-secondary mt-2" accept=".png, .jpg, .jpeg, .webp">
                            </div>
                        </div>
                    </div>
                </form>
            </div>

        </section>

        <div class="linea-resumen"></div>

        <section id="ver-orden-section">

            <h2>
                Ver orden de compra
            </h2>

            <div class="container-md container-fluid">
                <div class="row">
                    <div id="ver-orden-container">
                        <div class="form-group d-flex align-items-center">
                            <div class="mr-2 w-100">
                                <label for="selectOrdenes">Seleccione una orden de compra</label>
                                <select class="form-control mt-2" id="selectOrdenes">
                                </select>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary botonRosado mt-2" id="ver-orden-btn">Ver orden</button>
                    </div>
                </div>
            </div>
        </section>

        <div class="linea-resumen"></div>

        <section id="proveedor-section">

        </section>

    </main>

    <jsp:include page="/WEB-INF/template/footer.jsp" />
    
    <script src="media/js/infoUsuario.js"></script>
</html>