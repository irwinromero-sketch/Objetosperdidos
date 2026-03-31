const BASE_URL = 'https://objetosperdidos-production-d46f.up.railway.app';

function buscarPais() {
    const nombre = document.getElementById('inputPais').value.trim();
    if (!nombre) return;

    document.getElementById('resultado').style.display = 'none';
    document.getElementById('errorMsg').style.display = 'none';

    fetch(`${BASE_URL}/pais/${nombre}`)
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(data => mostrarPais(data))
        .catch(() => {
            document.getElementById('errorMsg').style.display = 'block';
        });
}

function mostrarPais(p) {
    document.getElementById('bandera').src = p.banderaUrl;
    document.getElementById('nombrePais').textContent = p.nombre;
    document.getElementById('moneda').textContent = 'Moneda: ' + p.moneda;
    document.getElementById('capital').textContent = p.capital;
    document.getElementById('region').textContent = p.region;
    document.getElementById('area').textContent = p.area.toLocaleString() + ' km²';
    document.getElementById('coordenadas').textContent = p.latitud + ', ' + p.longitud;
    document.getElementById('mapaUrl').href = p.mapaUrl;

    document.getElementById('temperatura').textContent = p.temperatura + ' °C';
    document.getElementById('humedad').textContent = p.humedad + ' %';
    document.getElementById('clima').textContent = p.clima;

    document.getElementById('dolarEnMxn').textContent = '$ ' + p.dolarEnMxn.toFixed(2);
    document.getElementById('bitcoinEnUsd').textContent = '$ ' + p.bitcoinEnUsd.toLocaleString();
    document.getElementById('bitcoinEnMxn').textContent = '$ ' + p.bitcoinEnMxn.toLocaleString();

    document.getElementById('resultado').style.display = 'block';
}

document.getElementById('inputPais').addEventListener('keydown', function(e) {
    if (e.key === 'Enter') buscarPais();
});