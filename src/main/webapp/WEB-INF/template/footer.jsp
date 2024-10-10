<%@page contentType="text/html" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="media/css/footer.css">

<footer class="row col-12">
	<br>
	<br>
	<br>
	<hr>
	<br>
	<br>
	<br>
	<div class="col-4">
	    <img class="imagen-utec" src="media/images/utec.webp" alt="UTEC en la vida real">
	    <p class="color-rosa">Calle Dr Evaristo Ciganda 461, 80000 San José de Mayo, Departamento de San José</p>
	    <p class="color-marron">©Flamin-Go</p>
	</div>
	<div class="col-12 col-md footer-segundaParte">
	    <h6 class="color-marron">Navegar</h6>
	    <br>
	    <h6 class="color-rosa" onclick="window.location.href='index.html'" style="cursor: pointer;">Inicio</h6>                
	    <h6 class="color-rosa" onclick="window.location.href='catalogo.html'" style="cursor: pointer;">Catálogo</h6>               
	    <h6 class="color-rosa" onclick="window.location.href='infoUsuario.html'" style="cursor: pointer;">Perfil</h6>
	    <h6 class="color-rosa" onclick="window.location.href='carrito.html'" style="cursor: pointer;">Carrito</h6>
	</div>
</footer>

<!-- SCRIPTS -->
<script>
	let usuarios = [
	    {
	        "nombre": "Salvador",
	        "apellido": "Vanoli",
	        "nickname": "salvaelpro777",
	        "tipo": "proveedor",
	        "email": "salva@salva.com",
	        "fecha": "2004-05-01",
	        "foto": "media/images/test.jpg",
	        "web": "salva.com",
	        "empresa": "salvaEnterprise",
	        "id": "0",
	        "ordenes": [
	            {
	                "id": 0,
	                "fecha": "2024-09-24",
	                "productos": [
	                    {
	                        "nombre": "Zucaritas",
	                        "precio": 300,
	                        "descripcion": "Muy ricas, sisi muy muy ricas",
	                        "imagenes": [
	                          "media/images/test.jpg",
	                          "media/images/test.jpg"
	                        ],
	                        "id": "777",
	                        "cantidad": 1
	                    },
	                    {
	                        "nombre": "WATAFAK",
	                        "precio": 200,
	                        "descripcion": "sii",
	                        "imagenes": [
	                          "media/images/test.jpg",
	                          "media/images/test.jpg"
	                        ],
	                        "id": "778",
	                        "cantidad": 5
	                    }
	                ]
	            },
	            {
	                "id": 1,
	                "fecha": "2024-09-27",
	                "productos": [
	                    {
	                        "nombre": "Zucaritas",
	                        "precio": 400,
	                        "descripcion": "Muy ricas, sisi muy muy ricas",
	                        "imagenes": [
	                          "media/images/test.jpg",
	                          "media/images/test.jpg"
	                        ],
	                        "id": "777",
	                        "cantidad": 6
	                    },
	                    {
	                        "nombre": "SSSSSSSSSSSESx",
	                        "precio": 200,
	                        "descripcion": "sii",
	                        "imagenes": [
	                          "media/images/test.jpg",
	                          "media/images/test.jpg"
	                        ],
	                        "id": "778",
	                        "cantidad": 8
	                    }
	                ]
	            },
	        ],
	        "productos": [
	            {
	                "nombre": "Zucaritas",
	                "estrellas": 3,
	                "precio": 300,
	                "descripcion": "Muy ricas, sisi muy muy ricas",
	                "id": "777",
	                "categorias": [
	                    {
	                        "nombre": "Comida",
	                        "hijas": [
	                            {
	                                "nombre": "Dulce",
	                                "hijas": [
	                                    {
	                                        "nombre": "Cereales",
	                                        "hijas": []
	                                    }
	                                ]
	                            }
	                        ]
	                    }
	                ],
	                "especificacion": [
	                    "Cereal dulce de maíz",
	                    "0 proteína 100% lípidos",
	                    "Totalmente mortal para el cuerpo"
	                ],
	                "imagenes": [
	                    "media/images/test.jpg",
	                    "media/images/test.jpg"
	                ]
	            }
	        ]
	    }
	]
	
	let categorias = [
	    {
	        "nombre": "Comida",
	        "hijas": [
	            {
	                "nombre": "Dulce",
	                "hijas": [
	                    {
	                        "nombre": "Cereales",
	                        "hijas": []
	                    },
	                    {
	                        "nombre": "Galletas",
	                        "hijas": []
	                    },
	                    {
	                        "nombre": "Chocolate",
	                        "hijas": []
	                    }
	                ]
	            },
	            {
	                "nombre": "Saludable",
	                "hijas": [
	                    {
	                        "nombre": "Cereales Fitness",
	                        "hijas": []
	                    },
	                    {
	                        "nombre": "Pasta",
	                        "hijas": []
	                    }
	                ]
	            }
	        ]
	    },
	    {
	        "nombre": "Bebida",
	        "hijas": [
	            {
	                "nombre": "Alcohólica",
	                "hijas": []
	            }
	        ]
	    }
	];
	
	
	let productos = [
	    {
	        "nombre": "Zucaritas",
	        "estrellas": 3,
	        "precio": 300,
	        "tienda": "DD Market",
	        "descripcion": "Cereal dulce y crujiente con mucha azúcar, ideal para empezar el día con energía.",
	        "id": "777",
	        "categorias": [
	            {
	                "nombre": "Comida",
	                "hijos": [
	                    {
	                        "nombre": "Dulce",
	                        "hijos": [
	                            {
	                                "nombre": "Cereales",
	                                "hijos": []
	                            }
	                        ]
	                    }
	                ]
	            }
	        ],
	        "imagenes": [
	            "media/images/Zucaritas.webp"
	        ],
	        "comentarios": [
	            {
	                "usuario": "Vale20m",
	                "estrellas": 3,
	                "comentario": "No me gusta esto 0 proteína",
	                "foto": "media/images/Flamin-Go.webp",
	                "fecha": "04/05/2004",
	                "id": 1,
	                "respuestas": [
	                    {
	                        "usuario": "kingstolas",
	                        "comentario": "Callate o te tiro un cuchillo",
	                        "foto": "media/images/Flamin-Go.webp",
	                        "fecha": "05/05/2004",
	                        "id": 2,
	                        "respuestas": [
	                            {
	                                "usuario": "kingstolas",
	                                "comentario": "Callate o te tiro un cuchillo",
	                                "foto": "media/images/Flamin-Go.webp",
	                                "fecha": "05/05/2004",
	                                "id": 3,
	                                "respuestas": [
	                                    {
	                                        "usuario": "kingstolas",
	                                        "comentario": "Callate o te tiro un cuchillo",
	                                        "foto": "media/images/Flamin-Go.webp",
	                                        "id": 4,
	                                        "fecha": "05/05/2004",
	                                        "respuestas": []
	                                    }
	                                ]
	                            }
	                        ]
	                    }
	                ]
	            },
	            {
	                "usuario": "BlckDev",
	                "estrellas": 5,
	                "comentario": "Mmm q ñicooo",
	                "foto": "media/images/Flamin-Go.webp",
	                "fecha": "20/09/2024",
	                "id": 8,
	                "respuestas": []
	            }
	        ],
	        "especificacion": [
	            "Mucha azúcar",
	            "Para desayunar",
	            "Poco saludable"
	        ],
	        "cantCompras": 250
	    },
	    {
	        "nombre": "Cereal Dulce",
	        "estrellas": 1,
	        "precio": 150,
	        "tienda": "Tienda A",
	        "descripcion": "Cereal con sabor dulce para los amantes del azúcar. Ideal para el desayuno.",
	        "id": "778",
	        "categorias": [
	            {
	                "nombre": "Comida",
	                "hijos": [
	                    {
	                        "nombre": "Dulce",
	                        "hijos": [
	                            {
	                                "nombre": "Cereales",
	                                "hijos": []
	                            }
	                        ]
	                    }
	                ]
	            }
	        ],
	        "imagenes": [
	            "media/images/cereal1.webp"
	        ],
	        "comentarios": [],
	        "especificacion": [
	            "Dulce al paladar",
	            "Ideal para desayuno",
	            "Contiene gluten"
	        ],
	        "cantCompras": 100
	    },
	    {
	        "nombre": "Oreo",
	        "estrellas": 5,
	        "precio": 200,
	        "tienda": "Tienda B",
	        "descripcion": "Galletas de chocolate con un relleno cremoso, perfectas para acompañar con leche.",
	        "id": "779",
	        "categorias": [
	            {
	                "nombre": "Comida",
	                "hijos": [
	                    {
	                        "nombre": "Dulce",
	                        "hijos": [
	                            {
	                                "nombre": "Galletas",
	                                "hijos": []
	                            }
	                        ]
	                    }
	                ]
	            }
	        ],
	        "imagenes": [
	            "media/images/oreo.webp",
	            "media/images/oreo1.webp"
	        ],
	        "comentarios": [],
	        "especificacion": [
	            "Sabor chocolate intenso",
	            "Relleno cremoso",
	            "Perfecto para postre"
	        ],
	        "cantCompras": 500
	    },
	    {
	        "nombre": "Pasta Integral",
	        "estrellas": 3,
	        "precio": 180,
	        "tienda": "Tienda C",
	        "descripcion": "Pasta hecha con harina integral, ideal para una alimentación saludable.",
	        "id": "780",
	        "categorias": [
	            {
	                "nombre": "Comida",
	                "hijos": [
	                    {
	                        "nombre": "Saludable",
	                        "hijos": [
	                            {
	                                "nombre": "Pasta",
	                                "hijos": []
	                            }
	                        ]
	                    }
	                ]
	            }
	        ],
	        "imagenes": [
	            "media/images/pasta.webp"
	        ],
	        "comentarios": [],
	        "especificacion": [
	            "Alta en fibra",
	            "Baja en calorías",
	            "Ideal para dietas"
	        ],
	        "cantCompras": 150
	    },
	    {
	        "nombre": "Chocolate Amargo",
	        "estrellas": 5,
	        "precio": 250,
	        "tienda": "Tienda D",
	        "descripcion": "Chocolate con alto contenido de cacao, perfecto para quienes prefieren un sabor intenso.",
	        "id": "781",
	        "categorias": [
	            {
	                "nombre": "Comida",
	                "hijos": [
	                    {
	                        "nombre": "Dulce",
	                        "hijos": [
	                            {
	                                "nombre": "Chocolate",
	                                "hijos": []
	                            }
	                        ]
	                    }
	                ]
	            }
	        ],
	        "imagenes": [
	            "media/images/chocolate.webp",
	            "media/images/chocolate1.webp"
	        ],
	        "comentarios": [],
	        "especificacion": [
	            "Alto en cacao",
	            "Poco azúcar",
	            "Apto para veganos"
	        ],
	        "cantCompras": 300
	    },
	    {
	        "nombre": "Cereal Saludable",
	        "estrellas": 4,
	        "precio": 220,
	        "tienda": "Tienda E",
	        "descripcion": "Cereal bajo en azúcar y alto en fibra, ideal para quienes cuidan su alimentación.",
	        "id": "782",
	        "categorias": [
	            {
	                "nombre": "Comida",
	                "hijos": [
	                    {
	                        "nombre": "Saludable",
	                        "hijos": [
	                            {
	                                "nombre": "Cereales Fitness",
	                                "hijos": []
	                            }
	                        ]
	                    }
	                ]
	            }
	        ],
	        "imagenes": [
	            "media/images/cereal.webp"
	        ],
	        "comentarios": [],
	        "especificacion": [
	            "Bajo en azúcar",
	            "Alto en fibra",
	            "Ideal para dietas"
	        ],
	        "cantCompras": 400
	    },
	    {
	        "nombre": "Cerveza Artesanal",
	        "estrellas": 4,
	        "precio": 450,
	        "tienda": "Bebidas y Más",
	        "descripcion": "Cerveza artesanal elaborada con ingredientes naturales, sabor intenso y cuerpo robusto.",
	        "id": "888",
	        "categorias": [
	            {
	                "nombre": "Bebida",
	                "hijos": [
	                    {
	                        "nombre": "Alcohólica",
	                        "hijos": []
	                    }
	                ]
	            }
	        ],
	        "imagenes": [
	            "media/images/cerveza_artesanal.webp"
	        ],
	        "comentarios": [
	            {
	                "usuario": "OttoOcta12",
	                "estrellas": 5,
	                "comentario": "¡La mejor cerveza que he probado!",
	                "foto": "media/images/cerveza1.webp",
	                "fecha": "15/09/2024",
	                "id": 1,
	                "respuestas": []
	            },
	            {
	                "usuario": "Bellardo",
	                "estrellas": 4,
	                "comentario": "Sabor excelente, aunque un poco caro.",
	                "foto": "media/images/cerveza2.webp",
	                "fecha": "16/09/2024",
	                "id": 2,
	                "respuestas": []
	            }
	        ],
	        "especificacion": [
	            "500ml de cerveza artesanal",
	            "5% de alcohol por volumen",
	            "Sabor fuerte y robusto"
	        ],
	        "cantCompras": 50
	    }
	];
	
	
	localStorage.setItem("productos", JSON.stringify(productos));
	localStorage.setItem("categorias", JSON.stringify(categorias)); // BORRAR SI SE QUIEREN AGREGAR MÁS
	if(!JSON.parse(localStorage.getItem("productos"))){
	    localStorage.setItem("productos", JSON.stringify(productos));
	}
	
	function mostrarAlerta(mensaje) {
	    let alertaExistente = document.getElementById("alerta");
	    
	    if (alertaExistente) {
	        alertaExistente.remove();
	    }
	
	    const alerta = document.createElement("div");
	    alerta.className = "alert alert-danger absoluto";
	    alerta.setAttribute("role", "alert");
	    alerta.id = "alerta";
	    alerta.innerHTML = `
	        ${mensaje}
	        <button type="button" class="close" aria-label="Close" onclick="this.parentElement.remove();">
	            <span aria-hidden="true">&times;</span>
	        </button>
	    `;
	
	    document.getElementsByTagName("body")[0].appendChild(alerta);
	}
</script>
<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>