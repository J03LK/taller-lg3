document.addEventListener("DOMContentLoaded", () => {
    // Abrir modal
    const openButtons = document.querySelectorAll(".btn-ver-mas");
    openButtons.forEach(button => {
        button.addEventListener("click", () => {
            const modalId = button.getAttribute("data-modal");
            const modal = document.getElementById(modalId);
            modal.style.display = "flex";
        });
    });

    // Cerrar modal
    const closeButtons = document.querySelectorAll(".modal .close");
    closeButtons.forEach(button => {
        button.addEventListener("click", () => {
            const modalId = button.getAttribute("data-close");
            const modal = document.getElementById(modalId);
            modal.style.display = "none";
        });
    });

    // Cerrar modal al hacer clic fuera del contenido
    const modals = document.querySelectorAll(".modal");
    modals.forEach(modal => {
        modal.addEventListener("click", (e) => {
            if (e.target === modal) {
                modal.style.display = "none";
            }
        });
    });
});
