document.addEventListener('DOMContentLoaded', function () {
    // Asegurarse de que los modales estén ocultos al cargar la página
    document.querySelectorAll('.modal').forEach(modal => {
        modal.style.display = 'none';
    });

    // Botones de autenticación (abrir modal de login o registro)
    const authButtons = document.querySelectorAll('[data-auth-modal]');
    authButtons.forEach(button => {
        button.onclick = function (e) {
            e.preventDefault();
            const modalId = this.getAttribute('data-auth-modal');
            const modal = document.getElementById(modalId);
            if (modal) modal.style.display = 'block';
        };
    });

    // Botones de cierre para todos los modales
    const closeButtons = document.querySelectorAll('.modal-close');
    closeButtons.forEach(button => {
        button.onclick = function () {
            const modal = this.closest('.modal');
            if (modal) modal.style.display = 'none';
        };
    });

    // Cerrar modales al hacer clic fuera del contenido
    window.onclick = function (event) {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    };

    // Manejo del modal de selección de registro
    const btnRegistroEstudiante = document.getElementById('btn-registro-estudiante');
    const btnRegistroProfesor = document.getElementById('btn-registro-profesor');
    const modalSeleccionRegistro = document.getElementById('modal-seleccion-registro');
    const modalRegistroEstudiante = document.getElementById('modal-registro');
    const modalRegistroProfesor = document.getElementById('modal-registro-profesor');

    if (btnRegistroEstudiante && btnRegistroProfesor) {
        btnRegistroEstudiante.onclick = function () {
            modalSeleccionRegistro.style.display = 'none';
            modalRegistroEstudiante.style.display = 'block';
        };

        btnRegistroProfesor.onclick = function () {
            modalSeleccionRegistro.style.display = 'none';
            modalRegistroProfesor.style.display = 'block';
        };
    }

    // Manejo del formulario de registro de estudiante
    const registroFormEstudiante = document.querySelector('#modal-registro form');
    if (registroFormEstudiante) {
        registroFormEstudiante.addEventListener('submit', function (e) {
            e.preventDefault();
            const formData = new FormData(this);
            fetch(this.action, {
                method: this.method.toUpperCase(),
                body: new URLSearchParams(formData),
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
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

    // Manejo del formulario de registro de profesor
    const registroFormProfesor = document.querySelector('#modal-registro-profesor form');
    if (registroFormProfesor) {
        registroFormProfesor.addEventListener('submit', function (e) {
            e.preventDefault();
            const formData = new FormData(this);
            fetch(this.action, {
                method: this.method.toUpperCase(),
                body: new URLSearchParams(formData),
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
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