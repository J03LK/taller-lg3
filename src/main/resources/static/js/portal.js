document.addEventListener("DOMContentLoaded", () => {
    // Animar barras de progreso al cargar la página
    document.querySelectorAll(".progress").forEach(bar => {
        const width = bar.style.width;
        bar.style.width = "0";
        setTimeout(() => {
            bar.style.width = width;
        }, 300);
    });

    // Filtrado en la tabla de estudiantes
    const searchInput = document.querySelector(".search-bar input");
    if (searchInput) {
        searchInput.addEventListener("input", () => {
            const filter = searchInput.value.toLowerCase();
            document.querySelectorAll(".students-table tbody tr").forEach(row => {
                const studentName = row.cells[0].textContent.toLowerCase();
                row.style.display = studentName.includes(filter) ? "" : "none";
            });
        });
    }

    // Confirmación antes de eliminar módulos
    document.querySelectorAll(".btn-delete").forEach(button => {
        button.addEventListener("click", (event) => {
            if (!confirm("¿Estás seguro de que deseas eliminar este módulo?")) {
                event.preventDefault();
            }
        });
    });
});
