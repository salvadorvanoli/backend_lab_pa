let usuarios = [
    {
        "nombre": "Salvador",
        "apellido": "Vanoli",
        "nickname": "salvaelpro777",
        "tipo": "proveedor",
        "email": "salva@salva.com",
        "fecha": "2004-05-01",
        "foto": "img/test.jpg",
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
                          "/img/test.jpg",
                          "/img/test.jpg"
                        ],
                        "id": "777",
                        "cantidad": 1
                    },
                    {
                        "nombre": "WATAFAK",
                        "precio": 200,
                        "descripcion": "sii",
                        "imagenes": [
                          "/img/test.jpg",
                          "/img/test.jpg"
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
                          "/img/test.jpg",
                          "/img/test.jpg"
                        ],
                        "id": "777",
                        "cantidad": 6
                    },
                    {
                        "nombre": "SSSSSSSSSSSESx",
                        "precio": 200,
                        "descripcion": "sii",
                        "imagenes": [
                          "/img/test.jpg",
                          "/img/test.jpg"
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
                    "/img/test.jpg",
                    "/img/test.jpg"
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
            "img/Zucaritas.webp"
        ],
        "comentarios": [
            {
                "usuario": "Vale20m",
                "estrellas": 3,
                "comentario": "No me gusta esto 0 proteína",
                "foto": "/img/Flamin-Go.webp",
                "fecha": "04/05/2004",
                "id": 1,
                "respuestas": [
                    {
                        "usuario": "kingstolas",
                        "comentario": "Callate o te tiro un cuchillo",
                        "foto": "/img/Flamin-Go.webp",
                        "fecha": "05/05/2004",
                        "id": 2,
                        "respuestas": [
                            {
                                "usuario": "kingstolas",
                                "comentario": "Callate o te tiro un cuchillo",
                                "foto": "/img/Flamin-Go.webp",
                                "fecha": "05/05/2004",
                                "id": 3,
                                "respuestas": [
                                    {
                                        "usuario": "kingstolas",
                                        "comentario": "Callate o te tiro un cuchillo",
                                        "foto": "/img/Flamin-Go.webp",
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
                "foto": "/img/Flamin-Go.webp",
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
            "img/cereal1.webp"
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
            "img/oreo.webp",
            "img/oreo1.webp"
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
            "img/pasta.webp"
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
            "img/chocolate.webp",
            "img/chocolate1.webp"
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
            "img/cereal.webp"
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
            "img/cerveza_artesanal.webp"
        ],
        "comentarios": [
            {
                "usuario": "OttoOcta12",
                "estrellas": 5,
                "comentario": "¡La mejor cerveza que he probado!",
                "foto": "/img/cerveza1.webp",
                "fecha": "15/09/2024",
                "id": 1,
                "respuestas": []
            },
            {
                "usuario": "Bellardo",
                "estrellas": 4,
                "comentario": "Sabor excelente, aunque un poco caro.",
                "foto": "/img/cerveza2.webp",
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