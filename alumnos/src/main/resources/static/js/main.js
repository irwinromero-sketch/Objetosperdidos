const BASE_URL = 'http://localhost:8080';


function cargarTodos() {
    fetch(`${BASE_URL}/objetos`)
        .then(res => res.json())
        .then(data => renderTabla(data))
        .catch(() => alert('Error al cargar objetos'));
}

function renderTabla(objetos) {
    const tbody = document.getElementById('tablaObjetos');
    tbody.innerHTML = '';

    if (objetos.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" class="text-muted">No hay objetos registrados</td></tr>';
        return;
    }

    objetos.forEach(obj => {
        const estadoClass = obj.estado === 'ENCONTRADO' ? 'bg-success' : 'bg-danger';
        const alumno = obj.alumno ? `${obj.alumno.nombre} ${obj.alumno.apellido}` : '—';

        tbody.innerHTML += `
            <tr>
                <td>${obj.id}</td>
                <td>${obj.descripcion}</td>
                <td>${obj.fecha}</td>
                <td><span class="badge ${estadoClass}">${obj.estado}</span></td>
                <td>${alumno}</td>
                <td>
                    <button class="btn btn-sm btn-warning me-1"
                        onclick="abrirEditar(${obj.id}, '${obj.descripcion}', '${obj.estado}')">
                        Editar
                    </button>
                    <button class="btn btn-sm btn-danger"
                        onclick="eliminarObjeto(${obj.id})">
                        Eliminar
                    </button>
                </td>
            </tr>
        `;
    });
}


function guardarObjeto() {
    const descripcion = document.getElementById('inputDescripcion').value.trim();
    const alumnoId = document.getElementById('inputAlumno').value.trim();
    const msg = document.getElementById('mensajeGuardar');

    if (!descripcion || !alumnoId) {
        msg.innerHTML = '<span class="text-danger">Completa todos los campos.</span>';
        return;
    }

    fetch(`${BASE_URL}/objetos`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            descripcion: descripcion,
            alumno: { id: parseInt(alumnoId) }
        })
    })
    .then(res => {
        if (!res.ok) throw new Error();
        return res.json();
    })
    .then(() => {
        msg.innerHTML = '<span class="text-success">Objeto guardado correctamente.</span>';
        document.getElementById('inputDescripcion').value = '';
        document.getElementById('inputAlumno').value = '';
        cargarTodos();
    })
    .catch(() => {
        msg.innerHTML = '<span class="text-danger">Error al guardar el objeto.</span>';
    });
}


function buscarPorId() {
    const id = document.getElementById('inputBuscarObjeto').value.trim();
    const div = document.getElementById('resultadoBusqueda');
    if (!id) return;

    fetch(`${BASE_URL}/objetos/buscar/${id}`)
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(obj => renderResultado([obj]))
        .catch(() => {
            div.innerHTML = '<span class="text-danger">No se encontró ningún objeto con ese ID.</span>';
        });
}

// =====================
// BUSCAR POR ALUMNO
// =====================
function buscarPorAlumno() {
    const id = document.getElementById('inputBuscarAlumno').value.trim();
    const div = document.getElementById('resultadoBusqueda');
    if (!id) return;

    fetch(`${BASE_URL}/objetos/alumno/${id}`)
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(data => {
            if (!data || data.length === 0) {
                div.innerHTML = '<span class="text-danger">No se encontraron objetos para ese alumno.</span>';
                return;
            }
            renderResultado(data);
        })
        .catch(() => {
            div.innerHTML = '<span class="text-danger">Error al buscar.</span>';
        });
}

function renderResultado(objetos) {
    const div = document.getElementById('resultadoBusqueda');
    let html = `
        <table class="table table-bordered text-center">
            <thead class="table-warning">
                <tr><th>ID</th><th>Descripción</th><th>Fecha</th><th>Estado</th><th>Alumno</th></tr>
            </thead>
            <tbody>
    `;
    objetos.forEach(obj => {
        const estadoClass = obj.estado === 'ENCONTRADO' ? 'bg-success' : 'bg-danger';
        const alumno = obj.alumno ? `${obj.alumno.nombre} ${obj.alumno.apellido}` : '—';
        html += `
            <tr>
                <td>${obj.id}</td>
                <td>${obj.descripcion}</td>
                <td>${obj.fecha}</td>
                <td><span class="badge ${estadoClass}">${obj.estado}</span></td>
                <td>${alumno}</td>
            </tr>
        `;
    });
    html += '</tbody></table>';
    div.innerHTML = html;
}

// =====================
// EDITAR
// =====================
function abrirEditar(id, descripcion, estado) {
    document.getElementById('editId').value = id;
    document.getElementById('editDescripcion').value = descripcion;
    document.getElementById('editEstado').value = estado;
    document.getElementById('mensajeEditar').innerHTML = '';
    new bootstrap.Modal(document.getElementById('modalEditar')).show();
}

function guardarEdicion() {
    const id = document.getElementById('editId').value;
    const descripcion = document.getElementById('editDescripcion').value.trim();
    const estado = document.getElementById('editEstado').value;
    const msg = document.getElementById('mensajeEditar');

    fetch(`${BASE_URL}/objetos/${id}/descripcion`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'text/plain' },
        body: descripcion
    })
    .then(res => { if (!res.ok) throw new Error(); return res.json(); })
    .then(() => {
        return fetch(`${BASE_URL}/objetos/${id}/estado`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'text/plain' },
            body: estado
        });
    })
    .then(res => { if (!res.ok) throw new Error(); return res.json(); })
    .then(() => {
        msg.innerHTML = '<span class="text-success">Objeto actualizado correctamente.</span>';
        cargarTodos();
        setTimeout(() => bootstrap.Modal.getInstance(document.getElementById('modalEditar')).hide(), 1000);
    })
    .catch(() => {
        msg.innerHTML = '<span class="text-danger">Error al actualizar el objeto.</span>';
    });
}

// =====================
// ELIMINAR
// =====================
function eliminarObjeto(id) {
    if (!confirm(`¿Eliminar el objeto con ID ${id}?`)) return;

    fetch(`${BASE_URL}/objetos/${id}`, { method: 'DELETE' })
        .then(res => { if (!res.ok) throw new Error(); })
        .then(() => cargarTodos())
        .catch(() => alert('Error al eliminar el objeto'));
}