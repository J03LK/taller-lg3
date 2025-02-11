document.addEventListener('DOMContentLoaded', function () {
    // PRIMERO: Asegurarse de que los modales estén ocultos al cargar la página
    document.querySelectorAll('.modal').forEach(modal => {
        modal.style.display = 'none';
    });

    // Manejo de modales para los cursos
    const botonesVerMas = document.querySelectorAll('.btn-ver-mas');
    botonesVerMas.forEach(boton => {
        boton.onclick = function(e) {
            e.preventDefault();
            const modalId = this.getAttribute('data-modal');
            const modal = document.getElementById(modalId);
            if (modal) {
                modal.style.display = 'flex';
            }
        };
    });

    // Botones de autenticación (abrir modal de login o registro)
    const authButtons = document.querySelectorAll('[data-auth-modal]');
    authButtons.forEach(button => {
        button.onclick = function (e) {
            e.preventDefault();
            const modalId = this.getAttribute('data-auth-modal');
            const modal = document.getElementById(modalId);
            if (modal) modal.style.display = 'flex'; // Cambiado a flex para consistencia
        };
    });

    // Manejo del modal de selección de registro
    const btnRegistroEstudiante = document.getElementById('btn-registro-estudiante');
    const btnRegistroProfesor = document.getElementById('btn-registro-profesor');
    const modalSeleccionRegistro = document.getElementById('modal-seleccion-registro');
    const modalRegistroEstudiante = document.getElementById('modal-registro');
    const modalRegistroProfesor = document.getElementById('modal-registro-profesor');

    if (btnRegistroEstudiante && btnRegistroProfesor) {
        btnRegistroEstudiante.onclick = function () {
            modalSeleccionRegistro.style.display = 'none';
            modalRegistroEstudiante.style.display = 'flex'; // Cambiado a flex
        };

        btnRegistroProfesor.onclick = function () {
            modalSeleccionRegistro.style.display = 'none';
            modalRegistroProfesor.style.display = 'flex'; // Cambiado a flex
        };
    }

    // UN SOLO manejo unificado de botones de cierre
    const closeButtons = document.querySelectorAll('.close, .modal-close');
    closeButtons.forEach(button => {
        button.onclick = function() {
            const modal = this.closest('.modal');
            if (modal) {
                modal.style.display = 'none';
            }
        };
    });

    // UN SOLO manejador para cerrar al hacer clic fuera
    window.onclick = function(event) {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    };

    // Manejo del formulario de registro de estudiante
    document.querySelector('#modal-registro form').addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(this);
        fetch(this.action, {
                        method: this.method.toUpperCase(),
                        body: JSON.stringify(Object.fromEntries(formData)),
                        headers: { 'Content-Type': 'application/json' }
                    })
        .then(response => {
            if (response.redirected) {  // Si el servidor redirige, seguimos la redirección
                window.location.href = response.url;
            } else {
                return response.text();
            }
        })
        .then(data => {
            if (data) alert(data);  // Si hay un mensaje, mostrarlo
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en el registro. Verifique su conexión.');
        });
    });


    // Manejo del formulario de registro de profesor
    const registroFormProfesor = document.querySelector('#modal-registro-profesor form');
    if (registroFormProfesor) {
        registroFormProfesor.addEventListener('submit', function (e) {
            e.preventDefault();
            const formData = new FormData(this);
            fetch(this.action, {
                method: this.method.toUpperCase(),
                body: JSON.stringify(Object.fromEntries(formData)),
                headers: { 'Content-Type': 'application/json' }
            })
            .then(response => response.json())
            .then(data => {
                if (data.message === "Registro exitoso!") {
                    alert('Registro exitoso.');
                    window.location.href = data.redirectUrl;
                } else {
                    alert('Error en el registro: ' + (data.message || 'Intente nuevamente.'));
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error en el registro. Verifique su conexión.');
            });
        });
    }
});