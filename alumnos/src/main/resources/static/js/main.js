const BASE_URL = 'http://localhost:8080';
let searchMode = 'objeto';

function setSearchMode(mode) {
    searchMode = mode;
    ocultarResultados();
}

function buscar() {
    const id = document.getElementById('searchInput').value.trim();
    if (!id) return;
    ocultarResultados();

    if (searchMode === 'objeto') {
        buscarPorObjeto(id);
    } else {
        buscarPorAlumno(id);
    }
}

function buscarPorObjeto(id) {
    fetch(`${BASE_URL}/objetos/buscar/${id}`)
        .then(res => {
            if (!res.ok) throw new Error('No encontrado');
            return res.json();
        })
        .then(data => mostrarObjeto(data))
        .catch(() => mostrarError());
}

function buscarPorAlumno(id) {
    fetch(`${BASE_URL}/objetos/alumno/${id}`)
        .then(res => {
            if (!res.ok) throw new Error('No encontrado');
            return res.json();
        })
        .then(data => {
            if (!data || data.length === 0) { mostrarError(); return; }
            mostrarLista(data);
        })
        .catch(() => mostrarError());
}

function mostrarObjeto(obj) {
    const badge = document.getElementById('estadoBadge');
    badge.textContent = obj.estado;
    badge.className = 'badge ' + (obj.estado === 'ENCONTRADO' ? 'bg-success' : 'bg-danger');

    document.getElementById('objId').textContent = obj.id;
    document.getElementById('objDescripcion').textContent = obj.descripcion;
    document.getElementById('objFecha').textContent = obj.fecha;
    document.getElementById('objAlumno').textContent = obj.alumno
        ? `${obj.alumno.nombre} ${obj.alumno.apellido}` : '—';
    document.getElementById('objCarrera').textContent = obj.alumno?.carrera || '—';
    document.getElementById('objEmail').textContent = obj.alumno?.email || '—';

    const img = document.getElementById('objImagen');
    img.src = obj.imagenUrl ? obj.imagenUrl : 'assets/imagen.jpg';
    img.alt = obj.descripcion;

    document.getElementById('resultado').style.display = 'block';
    document.getElementById('cardObjeto').style.display = 'block';
}

function mostrarLista(objetos) {
    const container = document.getElementById('listaContainer');
    container.innerHTML = '';

    objetos.forEach(obj => {
        const badgeClass = obj.estado === 'ENCONTRADO' ? 'bg-success' : 'bg-danger';
        const imgSrc = obj.imagenUrl ? obj.imagenUrl : 'assets/imagen1.jpg';
        const alumno = obj.alumno ? `${obj.alumno.nombre} ${obj.alumno.apellido}` : '—';

        container.innerHTML += `
            <div class="col-md-4 mb-3">
                <div class="card">
                    <img src="${imgSrc}" class="card-img-top" style="height:180px; object-fit:cover;"
                         onerror="this.src='assets/imagen.jpg'">
                    <div class="card-body">
                        <h6 class="card-title">${obj.descripcion}</h6>
                        <p class="mb-1"><strong>Fecha:</strong> ${obj.fecha}</p>
                        <p class="mb-1"><strong>Alumno:</strong> ${alumno}</p>
                        <span class="badge ${badgeClass}">${obj.estado}</span>
                    </div>
                </div>
            </div>
        `;
    });

    document.getElementById('resultado').style.display = 'block';
    document.getElementById('listaObjetos').style.display = 'block';
}

function mostrarError() {
    document.getElementById('resultado').style.display = 'block';
    document.getElementById('errorMsg').style.display = 'block';
}

function ocultarResultados() {
    document.getElementById('resultado').style.display = 'none';
    document.getElementById('cardObjeto').style.display = 'none';
    document.getElementById('listaObjetos').style.display = 'none';
    document.getElementById('errorMsg').style.display = 'none';
}

document.getElementById('searchInput').addEventListener('keydown', function(e) {
    if (e.key === 'Enter') buscar();
});