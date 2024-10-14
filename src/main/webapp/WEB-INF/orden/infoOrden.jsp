<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ingresarDatos</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'> <!-- Importamos la letra -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="media/css/infoOrdenCompra.css">
</head>
<body>
	
	<main class="container-fluid">
        <div class="rectangle-1">
            <div class="rectangle-2">
                <div class="row"> 
                    <div class="col-md-2 d-flex">
                        <div class="fotosProductos"></div>   
                    </div>
                    <div class="col-md-4 d-flex flex-column justify-content-start p-0">
                        <h1 class="nombresProductos text-start mt-3">Nombre Producto</h1>    
                        <h2 class="descripcionProductos text-start m-0">Descripción del producto hola soy un producto me estoy describiendo jeje hola bueno chau me voy ya me describi jeje</h2>
                        <h2 class="precioProductos text-start mt-3"> $666</h2>
                    </div>
                    <div class="col-md-4">
                        <h1 class="numProducto text-start mt-3">Nro. Producto</h1>
                        <div class="stepper-container d-flex align-items-end">
                             
                            <input type="number" id="quantity" class="stepper" value="0" min="0" max="100" disabled>
                            <label for="quantity" class="labelCantidad">Cantidad</label> 
                        </div>          
                    </div>
                    <div class="col-md-2">
                        <h1 class="textoSubtotal mt-3">Subtotal</h1>
                        <h1 class="precioProductosSubt mt-3"> $0</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="rectangle-3 row">
            <h1 class="textoResumen"> Resumen </h1>
            <div class="horizontal-line"></div>
            
                <div class="col-md-6 mt-3">
                    <h1 class="subtotal"> Subtotal </h1>
                    <h1 class="envio"> Envio </h1>
                    <h1 class="impuestos"> impuestos</h1>
                </div>
                <div class="col-md-6 mt-3 d-flex flex-column align-items-end">
                    <h1 class="subtotal2"> $0 </h1>
                    <h1 class="envio2"> Envio </h1>
                    <h1 class="impuestos2"> $0</h1>
                </div>
            <div class="horizontal-line"></div>
            <div class="col-md-6 mt-3">
                <h1 class="total"> total </h1>
            </div>
            <div class="col-md-6 mt-3 d-flex flex-column align-items-end">
                <h1 class="total2"> $0 </h1>
            </div>
            <div class="col-md-12 mt-5 d-flex flex-column align-items-end">
                <button type="button" class="button-volver" id="volver"> Volver </button>
            </div>
            
    
        </div>   
    </main>
</body>

<script src="js/infoOrdenCompra.js"></script>
<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="/media/js/infoOrdenCompra.js"></script>
</html>