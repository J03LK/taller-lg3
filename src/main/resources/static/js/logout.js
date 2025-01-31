// Crear un nuevo archivo js/logout.js y agregar este código

document.addEventListener('DOMContentLoaded', function() {
    // Verifica si hay un mensaje de cierre al cargar la página
    const mensajeCierreElement = document.querySelector('.mensaje-cierre');
    if (mensajeCierreElement) {
        setTimeout(() => {
            mensajeCierreElement.remove();
        }, 3000);
    }
});

function confirmarCierreSesion(event) {
    event.preventDefault();
    if (confirm('¿Estás seguro que deseas cerrar sesión?')) {
        window.location.href = '/logout';
    }
}

// Función para mostrar el modal con un mensaje
function mostrarModalCierre(mensaje) {
    const modal = document.getElementById('logoutModal');
    const mensajeElement = document.getElementById('mensajeCierre');
    mensajeElement.textContent = mensaje;
    modal.style.display = 'block';

    // Cerrar el modal cuando se hace clic en la X
    const span = modal.querySelector('.close');
    span.onclick = function() {
        modal.style.display = 'none';
    }

    // Cerrar el modal cuando se hace clic fuera de él
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    }
}