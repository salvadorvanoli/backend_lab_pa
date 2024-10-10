document.getElementById("icono-usuario").addEventListener("click", () => {
    let usuario = JSON.parse(localStorage.getItem("usuarioActual")) || null;
    if(usuario == null || usuario == ""){
        event.preventDefault();
        window.location.href = "iniciarSesion.html";
    }
});

document.getElementById("icono-usuario").addEventListener("hover", () => {
    
});