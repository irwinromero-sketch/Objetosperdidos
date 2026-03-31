const BASE_URL = 'http://localhost:8080';

// =====================
// USUARIOS
// =====================
const USUARIOS = {
    admin: { password: 'admin123', rol: 'ADMIN' },
    user:  { password: 'user123',  rol: 'USER'  }
};

let currentUser = null;
let currentRol  = null;
let credentials = null;

// =====================
// LOGIN / LOGOUT
// =====================
function login() {
    const user = document.getElementById('loginUser').value.trim();
    const pass = document.getElementById('loginPass').value.trim();

    if (USUARIOS[user] && USUARIOS[user].password === pass) {
        currentUser = user;
        currentRol  = USUARIOS[user].rol;
        credentials = btoa(user + ':' + pass);

        document.getElementById('loginPage').style.display    = 'none';
        document.getElementById('dashboard').style.display    = 'block';
        document.getElementById('userBadge').textContent      = '👤 ' + user + ' (' + currentRol + ')';
        document.getElementById('loginError').style.display   = 'none';

        aplicarPermisos();
        showSection('objetos');
        cargarTodos();
    } else {
        document.getElementById('loginError').style.display = 'block';
    }
}

function logout() {
    currentUser = null;
    currentRol  = null;
    credentials = null;
    document.getElementById('loginUser').value = '';
    document.getElementById('loginPass').value = '';
    document.getElementById('loginPage').style.display = 'flex';
    document.getElementById('dashboard').style.display = 'none';
}

function aplicarPermisos() {
    const esAdmin = currentRol === 'ADMIN';
    document.getElementById('formAgregar').style.display = esAdmin ? 'block' : 'none';
    document.getElementById('thAcciones').style.display  = esAdmin ? '' : 'none';
}

// =====================
// NAVEGACIÓN
// =====================
function showSection(section) {
    document.getElementById('sectionObjetos').style.display = section === 'objetos' ? 'block' : 'none';
    document.getElementById('sectionApis').style.display    = section === 'apis'    ? 'block' : 'none';
    document.getElementById('btnObjetos').classList.toggle('btn-dark',         section === 'objetos');
    document.getElementById('btnObjetos').classList.toggle('btn-outline-dark',  section !== 'objetos');
    document.getElementById('btnApis').classList.toggle('btn-dark',            section === 'apis');
    document.getElementById('btnApis').classList.toggle('btn-outline-dark',     section !== 'apis');
}

// =====================
// HEADERS CON AUTH
// =====================
function authHeaders(contentType) {
    const h = { 'Authorization': 'Basic ' + credentials };
    if (contentType) h['Content-Type'] = contentType;
    return h;
}

// =====================
// CARGAR TODOS
// =====================
function cargarTodos() {
    fetch(`${BASE_URL}/objetos`, { headers: authHeaders() })
        .then(res => res.json())
        .then(data => renderTabla(data))
        .catch(() => alert('Error al cargar objetos'));
}

function renderTabla(objetos) {
    const tbody = document.getElementById('tablaObjetos');
    tbody.innerHTML = '';
    const esAdmin = currentRol === 'ADMIN';

    if (objetos.length === 0) {
        tbody.innerHTML = `<tr><td colspan="${esAdmin ? 6 : 5}" class="text-muted">No hay objetos registrados</td></tr>`;
        return;
    }

    objetos.forEach(obj => {
        const estadoClass = obj.estado === 'ENCONTRADO' ? 'bg-success' : 'bg-danger';
        const alumno = obj.alumno ? `${obj.alumno.nombre} ${obj.alumno.apellido}` : '—';
        const acciones = esAdmin ? `
            <td>
                <button class="btn btn-sm btn-warning me-1" onclick="abrirEditar(${obj.id}, '${obj.descripcion}', '${obj.estado}')">Editar</button>
                <button class="btn btn-sm btn-danger" onclick="eliminarObjeto(${obj.id})">Eliminar</button>
            </td>` : '';

        tbody.innerHTML += `
            <tr>
                <td>${obj.id}</td>
                <td>${obj.descripcion}</td>
                <td>${obj.fecha}</td>
                <td><span class="badge ${estadoClass}">${obj.estado}</span></td>
                <td>${alumno}</td>
                ${acciones}
            </tr>`;
    });
}

// =====================
// GUARDAR NUEVO
// =====================
function guardarObjeto() {
    const descripcion = document.getElementById('inputDescripcion').value.trim();
    const alumnoId    = document.getElementById('inputAlumno').value.trim();
    const msg         = document.getElementById('mensajeGuardar');

    if (!descripcion || !alumnoId) {
        msg.innerHTML = '<span class="text-danger">Completa todos los campos.</span>';
        return;
    }

    fetch(`${BASE_URL}/objetos`, {
        method: 'POST',
        headers: authHeaders('application/json'),
        body: JSON.stringify({ descripcion, alumno: { id: parseInt(alumnoId) } })
    })
    .then(res => { if (!res.ok) throw new Error(); return res.json(); })
    .then(() => {
        msg.innerHTML = '<span class="text-success">Objeto guardado correctamente.</span>';
        document.getElementById('inputDescripcion').value = '';
        document.getElementById('inputAlumno').value = '';
        cargarTodos();
    })
    .catch(() => { msg.innerHTML = '<span class="text-danger">Error al guardar.</span>'; });
}

// =====================
// BUSCAR POR ID
// =====================
function buscarPorId() {
    const id  = document.getElementById('inputBuscarObjeto').value.trim();
    const div = document.getElementById('resultadoBusqueda');
    if (!id) return;

    fetch(`${BASE_URL}/objetos/buscar/${id}`, { headers: authHeaders() })
        .then(res => { if (!res.ok) throw new Error(); return res.json(); })
        .then(obj => renderResultado([obj]))
        .catch(() => { div.innerHTML = '<span class="text-danger">No se encontró ningún objeto con ese ID.</span>'; });
}

// =====================
// BUSCAR POR ALUMNO
// =====================
function buscarPorAlumno() {
    const id  = document.getElementById('inputBuscarAlumno').value.trim();
    const div = document.getElementById('resultadoBusqueda');
    if (!id) return;

    fetch(`${BASE_URL}/objetos/alumno/${id}`, { headers: authHeaders() })
        .then(res => { if (!res.ok) throw new Error(); return res.json(); })
        .then(data => {
            if (!data || data.length === 0) {
                div.innerHTML = '<span class="text-danger">No se encontraron objetos para ese alumno.</span>';
                return;
            }
            renderResultado(data);
        })
        .catch(() => { div.innerHTML = '<span class="text-danger">Error al buscar.</span>'; });
}

function renderResultado(objetos) {
    const div = document.getElementById('resultadoBusqueda');
    let html = `
        <table class="table table-bordered text-center">
            <thead class="table-warning">
                <tr><th>ID</th><th>Descripción</th><th>Fecha</th><th>Estado</th><th>Alumno</th></tr>
            </thead><tbody>`;
    objetos.forEach(obj => {
        const estadoClass = obj.estado === 'ENCONTRADO' ? 'bg-success' : 'bg-danger';
        const alumno = obj.alumno ? `${obj.alumno.nombre} ${obj.alumno.apellido}` : '—';
        html += `<tr>
            <td>${obj.id}</td><td>${obj.descripcion}</td><td>${obj.fecha}</td>
            <td><span class="badge ${estadoClass}">${obj.estado}</span></td>
            <td>${alumno}</td>
        </tr>`;
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
    const id          = document.getElementById('editId').value;
    const descripcion = document.getElementById('editDescripcion').value.trim();
    const estado      = document.getElementById('editEstado').value;
    const msg         = document.getElementById('mensajeEditar');

    fetch(`${BASE_URL}/objetos/${id}/descripcion`, {
        method: 'PATCH', headers: authHeaders('text/plain'), body: descripcion
    })
    .then(res => { if (!res.ok) throw new Error(); return res.json(); })
    .then(() => fetch(`${BASE_URL}/objetos/${id}/estado`, {
        method: 'PATCH', headers: authHeaders('text/plain'), body: estado
    }))
    .then(res => { if (!res.ok) throw new Error(); return res.json(); })
    .then(() => {
        msg.innerHTML = '<span class="text-success">Objeto actualizado correctamente.</span>';
        cargarTodos();
        setTimeout(() => bootstrap.Modal.getInstance(document.getElementById('modalEditar')).hide(), 1000);
    })
    .catch(() => { msg.innerHTML = '<span class="text-danger">Error al actualizar.</span>'; });
}

// =====================
// ELIMINAR
// =====================
function eliminarObjeto(id) {
    if (!confirm(`¿Eliminar el objeto con ID ${id}?`)) return;

    fetch(`${BASE_URL}/objetos/${id}`, {
        method: 'DELETE', headers: authHeaders()
    })
    .then(res => { if (!res.ok) throw new Error(); })
    .then(() => cargarTodos())
    .catch(() => alert('Error al eliminar el objeto'));
}

// =====================
// APIs EXTERNAS — PAIS
// =====================
function buscarPais() {
    const nombre = document.getElementById('inputPais').value.trim();
    if (!nombre) return;

    document.getElementById('resultadoPais').style.display = 'none';
    document.getElementById('errorPais').style.display     = 'none';

    fetch(`${BASE_URL}/pais/${nombre}`, { headers: authHeaders() })
        .then(res => { if (!res.ok) throw new Error(); return res.json(); })
        .then(data => mostrarPais(data))
        .catch(() => { document.getElementById('errorPais').style.display = 'block'; });
}

function mostrarPais(p) {
    document.getElementById('bandera').src       = p.banderaUrl;
    document.getElementById('nombrePais').textContent = p.nombre;
    document.getElementById('moneda').textContent     = 'Moneda: ' + p.moneda;
    document.getElementById('capital').textContent    = p.capital;
    document.getElementById('region').textContent     = p.region;
    document.getElementById('area').textContent       = p.area.toLocaleString() + ' km²';
    document.getElementById('coordenadas').textContent = p.latitud + ', ' + p.longitud;
    document.getElementById('mapaUrl').href            = p.mapaUrl;
    document.getElementById('temperatura').textContent = p.temperatura + ' °C';
    document.getElementById('humedad').textContent     = p.humedad + ' %';
    document.getElementById('clima').textContent       = p.clima;
    document.getElementById('dolarEnMxn').textContent  = '$ ' + p.dolarEnMxn.toFixed(2);
    document.getElementById('bitcoinEnUsd').textContent = '$ ' + p.bitcoinEnUsd.toLocaleString();
    document.getElementById('bitcoinEnMxn').textContent = '$ ' + p.bitcoinEnMxn.toLocaleString();

    document.getElementById('resultadoPais').style.display = 'block';
}

document.getElementById('inputPais')?.addEventListener('keydown', e => {
    if (e.key === 'Enter') buscarPais();
});