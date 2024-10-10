<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.flamingo.models.Usuario"%>
<%@page import="com.flamingo.models.Cliente"%>
<%@page import="com.flamingo.models.Cantidad"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" type="text/css" href="media/css/carrito.css">
    <title>Carrito</title>
</head>
<body>

	<!-- NAVBAR -->
	<jsp:include page="/WEB-INF/template/header.jsp"/>

	<%
		
	%>

	<main>
	<%	
		Object obj = request.getAttribute("usuarioActual");
		if (obj != null) {
			if (obj instanceof Usuario) {
				Usuario user = (Usuario) obj;
				HashMap<Integer, Cantidad> carrito = user.getCarrito();
				if (!carrito.isEmpty()) {
					%>
					
					<!-- ENCABEZADO DE FASES -->
				    <div class="container-fluid p-0" id="encabezado1">
				        <div id="rectangulo-fase-estetica">
				            <div class="container-lg">
					            <div class="row rectangulo-fase align-items-end">
					                <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
					                    <h1 class="titulo-fase-principal">1. Carrito de compra</h1>
					                </div>
					                <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
					                    <h1 class="titulo-fase-secundaria">2. Detalles del envío</h1>
					                </div>
					                <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
					                    <h1 class="titulo-fase-secundaria">3. Método de pago</h1>
					                </div>
					                <div class="row d-flex align-items-end m-0 p-0">
					                    <div class="col-4 linea-principal"></div>
					                    <div class="col-8 linea-secundario"></div>
					                </div>
					            </div>
				        	</div>
				        </div>
				    </div>
				    
				    <div class="container-md container-fluid" id="seccion1">
				        <div class="row d-flex justify-content-center">
				
				            <form class="row g-3 needs-validation" novalidate id="formCarrito"> <!-- DUDOSOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO -->
				
				                <div class="col-md-8 col-12 mt-5">
				
				                    <h1 id="titulo-carrito">Carrito de Compra</h1>
				
				                    <!-- CONTENEDOR DE LOS PRODUCTOS DEL CARRITO -->
				                    <div class="contenedor-productos" id="carrito">

				                    </div>
				
				
				                </div>
				
				                <!-- RESUMEN DEL CARRITO -->
				                <div class="col-md-4 col-12 mt-5">
				                    <h1 class="titulo-resumen">Resumen</h1>
				                    <div class="linea-resumen my-2"></div>
				                    <div class="row texto-resumen mt-3">
				                        <p class="col-6">SUBTOTAL</p>
				                        <p class="col-6 text-end precio-subtotal">$0</p>
				                    </div>
				                    <div class="row texto-resumen">
				                        <p class="col-6">ENVÍO</p>
				                        <p class="col-6 text-end precio-envio">GRATIS</p>
				                    </div>
				                    <div class="row texto-resumen">
				                        <p class="col-6">IMPUESTOS</p>
				                        <p class="col-6 text-end precio-impuestos">$0</p>
				                    </div>
				                    <div class="linea-resumen mb-3"></div>
				                    <div class="row texto-total">
				                        <p class="col-6">TOTAL</p>
				                        <p class="col-6 text-end precio-total">$0</p>
				                    </div>
				                </div>
				
				                <!-- BOTONES PARA CONTINUAR O CANCELAR -->
				                <div class="row">
				                    <div class="col-12">
				                        
				                        <input type="submit" class="btn siguiente-button my-4 me-4" id="botonSiguiente1" value="Siguiente">
				                        <input type="button" class="btn cancelar-button my-4" id="botonVolver" value="Volver">
				
				                    </div>
				                </div>
				            </form>
				
				        </div>
				    </div>
				
				    <div class="container-fluid p-0 d-none" id="encabezado2">
				        <div id="rectangulo-fase-estetica">
				            <div class="container-lg">
				                <div class="row rectangulo-fase align-items-end">
				                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
				                        <h1 class="titulo-fase-secundaria">1. Carrito de compra</h1>
				                    </div>
				                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
				                        <h1 class="titulo-fase-principal">2. Detalles del envío</h1>
				                    </div>
				                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
				                        <h1 class="titulo-fase-secundaria">3. Método de pago</h1>
				                    </div>
				                    <div class="row d-flex align-items-end m-0 p-0">
				                        <div class="col-4 linea-secundario"></div>
				                        <div class="col-4 linea-principal"></div>
				                        <div class="col-4 linea-secundario"></div>
				                    </div>
				                </div>
				            </div>
				        </div>
				    </div>
				
				    <div class="container-md container-fluid d-none" id="seccion2">
				        <div class="row d-flex justify-content-center">
				
				            <form class="row g-3 needs-validation" novalidate id="formEnvio"> <!-- DUDOSOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO -->
				
				                <div class="col-md-8 col-12 mt-5">
				
				                    <h1 class="titulo-resumen">Detalles del envío</h1>
				                    
				                    <div class="row mb-md-4">
				                        <div class="form-floating col-md-6 mb-md-0 mb-4">
				                            <input type="text" class="form-control estetica-input" id="nombre" placeholder="Nombre" required pattern="[A-Za-z]+(( ?([A-Za-z]+))?)+">
				                            <label for="nombre" class="ms-2 label-input">Nombre</label>
				                            <div class="invalid-feedback">
				                                <i class="fa-solid fa-triangle-exclamation"></i> "Nombre" no puede estar vacío (solo se aceptan letras y espacios).
				                            </div>
				                        </div>
				                        <div class="form-floating col-md-6 mb-md-0 mb-4">
				                            <input type="text" class="form-control estetica-input" id="apellido" placeholder="Apellido" required pattern="[A-Za-z]+(( ?([A-Za-z]+))?)+">
				                            <label for="apellido" class="ms-2 label-input">Apellido</label>
				                            <div class="invalid-feedback">
				                                <i class="fa-solid fa-triangle-exclamation"></i> "Apellido" no puede estar vacío (solo se aceptan letras).
				                            </div>
				                        </div>
				                    </div>
				
				                    
				                    <div class="form-floating mb-4">
				                        <input type="text" class="form-control estetica-input" id="direccion1" placeholder="Dirección" required pattern="[A-Za-z0-9]+(( ?([A-Za-z0-9]+))?)+ [0-9]+">
				                        <label for="direccion" class="label-input">Dirección</label>
				                        <div class="invalid-feedback">
				                            <i class="fa-solid fa-triangle-exclamation"></i> "Dirección" no puede estar vacío (calle seguido de un número).
				                        </div>
				                    </div>
				
				
				                    <div class="form-floating mb-4">
				                        <input type="text" class="form-control estetica-input" id="direccion2" placeholder="Dirección 2">
				                        <label for="direccion2" class="label-input">Dirección 2</label>
				                    </div>
				
				                    <div class="row direcciones mb-md-4">
				                        <div class="col-md-6 mb-md-0 mb-4">
				                            <select class="form-select estetica-input" aria-label="Selecciona un departamento" id="departamento" required>
				                                <option value="" selected>Elige un departamento</option>
				                                <option value="Artigas">Artigas</option>
				                                <option value="Canelones">Canelones</option>
				                                <option value="Cerro Largo">Cerro Largo</option>
				                                <option value="Colonia">Colonia</option>
				                                <option value="Durazno">Durazno</option>
				                                <option value="Flores">Flores</option>
				                                <option value="Florida">Florida</option>
				                                <option value="Lavalleja">Lavalleja</option>
				                                <option value="Maldonado">Maldonado</option>
				                                <option value="Montevideo">Montevideo</option>
				                                <option value="Paysandú">Paysandú</option>
				                                <option value="Río Negro">Río Negro</option>
				                                <option value="Rivera">Rivera</option>
				                                <option value="Rocha">Rocha</option>
				                                <option value="Salto">Salto</option>
				                                <option value="San José">San José</option>
				                                <option value="Soriano">Soriano</option>
				                                <option value="Tacuarembó">Tacuarembó</option>
				                                <option value="Treinta Y Tres">Treinta Y Tres</option>
				                            </select>
				                            <div class="invalid-feedback">
				                                <i class="fa-solid fa-triangle-exclamation"></i> Se debe seleccionar un departamento.
				                            </div>
				                        </div>
				                        <div class="col-md-6 mb-md-0 mb-4">
				                            <select class="form-select estetica-input" aria-label="Selecciona una ciudad" id="ciudad" disabled required>
				                                <option value="" selected>Elige una ciudad</option>
				                            </select>
				                            <div class="invalid-feedback">
				                                <i class="fa-solid fa-triangle-exclamation"></i> Se debe seleccionar una ciudad.
				                            </div>
				                        </div>
				                    </div>
				
				                    <div class="row mb-md-4">
				                        <div class="form-floating col-md-6 mb-md-0 mb-4">
				                            <input type="text" class="form-control estetica-input" id="codPostal" placeholder="Código Postal" required pattern="[0-9]{5}">
				                            <label for="codPostal" class="ms-2 label-input">Código Postal</label>
				                            <div class="invalid-feedback">
				                                <i class="fa-solid fa-triangle-exclamation"></i> "Código Postal" no puede estar vacío (5 dígitos).
				                            </div>
				                        </div>
				                        <div class="form-floating col-md-6 mb-md-0 mb-4">
				                            <input type="text" class="form-control estetica-input" id="numTelefono" placeholder="Número de teléfono" required pattern="09[1-9][0-9]{6}">
				                            <label for="numTelefono" class="ms-2 label-input">Número de teléfono</label>
				                            <div class="invalid-feedback">
				                                <i class="fa-solid fa-triangle-exclamation"></i> "Número de teléfono" no puede estar vacío (ejemplo: 091234567).
				                            </div>
				                        </div>
				                    </div>
				
				                    <div class="linea-resumen my-4"></div>
				
				                    <div class="row d-flex justify-content-around">
				                        <div class="col-md-5 col-8 mb-md-0 mb-4 opcion-box d-flex align-items-center justify-content-center py-2">
				                            <div class="row w-100">
				                                <div class="col-md-2 col-3 d-flex align-items-center justify-content-end">
				                                    <input class="form-check-input mt-0" type="radio" name="tipoEnvio" id="envioGratis" value="free" aria-label="Radio button for following text input" checked onchange="modificarEnvio()">
				                                </div>
				                                <div class="col-md-10 col-9">
				                                    <p class="p-0 m-0">Envío gratuito</p>
				                                    <p class="p-0 m-0">Entre 2 - 5 días</p>
				                                </div>
				                            </div>
				                        </div>
				
				                        <div class="col-md-5 col-8 mb-md-0 mb-4 opcion-box d-flex align-items-center justify-content-center py-2">
				                            <div class="row w-100">
				                                <div class="col-md-2 col-3 d-flex align-items-center justify-content-end">
				                                    <input class="form-check-input mt-0" type="radio" name="tipoEnvio" id="envioVip" value="vip" aria-label="Radio button for following text input" onchange="modificarEnvio()">
				                                </div>
				                                <div class="col-md-10 col-9">
				                                    <p class="p-0 m-0">Envío VIP - $20</p>
				                                    <p class="p-0 m-0">24 horas luego de la compra</p>
				                                </div>
				                            </div>
				                        </div>
				                    </div>
				
				                    <div class="linea-resumen my-4"></div>
				
				                </div>
				
				                <!-- RESUMEN DEL CARRITO -->
				                <div class="col-md-4 col-12 mt-5">
				
				                    <h1 class="titulo-resumen">Resumen</h1>
				                    <!-- CONTENEDOR DE LOS PRODUCTOS DEL CARRITO -->
				                    <div class="contenedor-productos contenedor-secundario">
				
				                        
				
				                    </div>
				
				                    <div class="linea-resumen my-2 mt-4"></div>
				                    <div class="row texto-resumen mt-3">
				                        <p class="col-6">SUBTOTAL</p>
				                        <p class="col-6 text-end precio-subtotal">$0</p>
				                    </div>
				                    <div class="row texto-resumen">
				                        <p class="col-6">ENVÍO</p>
				                        <p class="col-6 text-end precio-envio">GRATIS</p>
				                    </div>
				                    <div class="row texto-resumen">
				                        <p class="col-6">IMPUESTOS</p>
				                        <p class="col-6 text-end precio-impuestos">$0</p>
				                    </div>
				                    <div class="linea-resumen mb-3"></div>
				                    <div class="row texto-total">
				                        <p class="col-6">TOTAL</p>
				                        <p class="col-6 text-end precio-total">$0</p>
				                    </div>
				
				                </div>
				
				                <!-- BOTONES PARA CONTINUAR O CANCELAR -->
				                <div class="row">
				                    <div class="col-12">
				                        
				                        <input type="submit" class="btn siguiente-button my-4 me-4" value="Siguiente" id="botonSiguiente2">
				                        <input type="button" class="btn cancelar-button my-4" value="Atrás" id="botonAtras1">
				                        
				
				                    </div>
				                </div>
				            </form>
				        </div>
				    </div>
				
				
				    <div class="container-fluid p-0 d-none" id="encabezado3">
				        <div id="rectangulo-fase-estetica">
				            <div class="container-lg">
				                <div class="row rectangulo-fase align-items-end">
				                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
				                        <h1 class="titulo-fase-secundaria">1. Carrito de compra</h1>
				                    </div>
				                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
				                        <h1 class="titulo-fase-secundaria">2. Detalles del envío</h1>
				                    </div>
				                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
				                        <h1 class="titulo-fase-principal">3. Método de pago</h1>
				                    </div>
				                    <div class="row d-flex align-items-end m-0 p-0">
				                        <div class="col-8 linea-secundario"></div>
				                        <div class="col-4 linea-principal"></div>
				                    </div>
				                </div>
				            </div>
				        </div>
				    </div>
				
				
				    <div class="container-md container-fluid d-none" id="seccion3">
				        <div class="row d-flex justify-content-center">
				
				            <form class="row g-3 needs-validation" novalidate id="formPago">
				
				                <div class="col-md-8 col-12 mt-5">
				
				                    <h1 class="titulo-resumen">Método de pago</h1>
				
				                    <div class="mb-4 opcion-box d-flex align-items-center justify-content-center py-2">
				                        <div class="row w-100">
				                            <div class="col-1 d-flex align-items-center justify-content-center">
				                                <input class="form-check-input mt-0" type="radio" name="tipoPago" id="pagoTarjeta" value="credito" aria-label="Radio button for following text input" checked>
				                            </div>
				                            <div class="col-11">
				                                <p class="p-0 m-0">Tarjeta de crédito</p>
				                                <p class="p-0 m-0">Realiza pagos rápidos y seguros con tu tarjeta de crédito. Aceptamos Visa, MasterCard, y más.</p>
				                            </div>
				                            <div class="row col-12">
				                                <div class="col-sm-6 col-12">
				                                    <div class="input-group m-3">
				                                        <span class="input-group-text" id="addon-wrapping"><i class="fa-regular fa-credit-card"></i></span>
				                                        <input type="text" class="form-control estetica-input" id="numTarjeta" placeholder="0000 0000 0000 0000" aria-label="Número de tarjeta" pattern="([0-9]{16}|(([0-9]){4} ){3}[0-9]{4})" required>
				                                        <div class="invalid-feedback">
				                                            <i class="fa-solid fa-triangle-exclamation"></i> "Número de tarjeta" no puede estar vacío (16 dígitos).
				                                        </div>
				                                    </div>
				                                </div>
				                                <div class="col-sm-3 col-6">
				                                    <div class="input-group m-3">
				                                        <span class="input-group-text" id="addon-wrapping"><i class="fa-solid fa-calendar-days"></i></span>
				                                        <input type="text" class="form-control estetica-input" id="fecVencimiento" placeholder="MM/YY" aria-label="Fecha de vencimiento" pattern="(0[1-9]|1[0-2])/[0-9]{2}" required maxlength="5">
				                                        <div class="invalid-feedback">
				                                            <i class="fa-solid fa-triangle-exclamation"></i> "Fecha de vencimiento" no puede estar vacío (la fecha no puede ser posterior a la fecha actual).
				                                        </div>
				                                    </div>
				                                </div>
				                                <div class="col-sm-3 col-6">
				                                    <div class="input-group m-3">
				                                        <span class="input-group-text" id="addon-wrapping"><i class="fa-solid fa-lock"></i></span>
				                                        <input type="text" class="form-control estetica-input" id="cvv" placeholder="CVV" aria-label="Código de verificación" pattern="[0-9]{3}" required maxlength="3">
				                                        <div class="invalid-feedback">
				                                            <i class="fa-solid fa-triangle-exclamation"></i> "Código de verificación" no puede estar vacío (3 dígitos).
				                                        </div>
				                                    </div>
				                                </div>
				                            </div>
				                            <div class="row col-12">
				                                <div class="input-group m-3">
				                                    <span class="input-group-text" id="addon-wrapping"><i class="fa-solid fa-user"></i></span>
				                                    <input type="text" class="form-control estetica-input" id="nomTitular" placeholder="Nombre del titular" aria-label="Nombre del titular" required pattern="[A-Za-z]+(( ?([A-Za-z]+))?)+">
				                                    <div class="invalid-feedback">
				                                        <i class="fa-solid fa-triangle-exclamation"></i> "Nombre del titular" no puede estar vacío.
				                                    </div>
				                                </div>
				                            </div>
				                        </div>
				                    </div>
				
				                    <div class="mb-4 opcion-box d-flex align-items-center justify-content-center py-2">
				                        <div class="row w-100">
				                            <div class="col-1 d-flex align-items-center justify-content-center">
				                                <input class="form-check-input mt-0" type="radio" name="tipoPago" id="pagoPaypal" value="paypal" aria-label="Radio button for following text input" disabled>
				                            </div>
				                            <div class="col-11">
				                                <p class="p-0 m-0">PayPal</p>
				                                <p class="p-0 m-0">Usa PayPal para pagar de forma segura sin compartir tu información financiera. Fácil, rápido y seguro.</p>
				                            </div>
				                        </div>
				                    </div>
				
				                </div>
				
				                <!-- RESUMEN DEL CARRITO -->
				                <div class="col-md-4 col-12 mt-5">
				
				                    <h1 class="titulo-resumen">Resumen</h1>
				                    <!-- CONTENEDOR DE LOS PRODUCTOS DEL CARRITO -->
				                    <div class="contenedor-productos contenedor-secundario">
				
				                        
				
				                    </div>
				
				                    <div class="linea-resumen my-2 mt-4"></div>
				                    <div class="row texto-resumen mt-3">
				                        <p class="col-6">SUBTOTAL</p>
				                        <p class="col-6 text-end precio-subtotal">$0</p>
				                    </div>
				                    <div class="row texto-resumen">
				                        <p class="col-6">ENVÍO</p>
				                        <p class="col-6 text-end precio-envio">GRATIS</p>
				                    </div>
				                    <div class="row texto-resumen">
				                        <p class="col-6">IMPUESTOS</p>
				                        <p class="col-6 text-end precio-impuestos">$0</p>
				                    </div>
				                    <div class="linea-resumen mb-3"></div>
				                    <div class="row texto-total">
				                        <p class="col-6">TOTAL</p>
				                        <p class="col-6 text-end precio-total">$0</p>
				                    </div>
				
				                </div>
				
				                <!-- BOTONES PARA CONTINUAR O CANCELAR -->
				                <div class="row">
				                    <div class="col-12">
				                        
				                        <input type="submit" class="btn siguiente-button my-4 me-4" value="Pagar ahora" id="botonPagar">
				                        <input type="button" class="btn cancelar-button my-4" value="Atrás" id="botonAtras2">
				
				                    </div>
				                </div>
				
				            </form>
				
				        </div>
				    </div>
						
				<%
				} else {
					%>
					<div class="text-center fs-1 m-5">
						No hay productos en el carrito actualmente.
					</div>
					<%
				}
			} else {
				%>
				<div class="text-center fs-1 m-5">
					El usuario ingresado no es válido.
				</div>
				<%
			}
		} else {
			%>
				<div class="text-center fs-1 m-5">
					Aún no tienes la sesión iniciada. Serás redirigido a inicio en unos segundos.
				</div>
			<%
			// Falta redirigir al usuario
		}
	
	%>
	</main>


    <!-- FOOTER -->
	<jsp:include page="/WEB-INF/template/footer.jsp"/>
    <script src="/media/js/carrito.js"></script>

</body>
</html>