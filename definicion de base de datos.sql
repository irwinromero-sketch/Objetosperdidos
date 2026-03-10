-- Crear base de datos
CREATE DATABASE objetos_perdidos;
USE objetos_perdidos;

-- Tabla: alumno

CREATE TABLE alumno (
idalumno INT PRIMARY KEY,
nombre VARCHAR(100),
apellido VARCHAR(100),
matricula VARCHAR(50),
emal VARCHAR(150),
carrera VARCHAR(100),
password VARCHAR(100),
promedio DOUBLE
);

-- Tabla: objeto

CREATE TABLE objeto (
idobjeto INT PRIMARY KEY,
descripcion VARCHAR(255),
estado VARCHAR(50),
fecha DATE,
foto VARCHAR(255)
);

-- Tabla: reportes

CREATE TABLE reportes (
id_reporte INT PRIMARY KEY,
fecha_reporte DATE,
objeto_idobjeto INT,
id_alumno_recibe INT,
id_alumno_reporta INT,

```
-- Llaves foráneas
FOREIGN KEY (objeto_idobjeto) REFERENCES objeto(idobjeto),
FOREIGN KEY (id_alumno_recibe) REFERENCES alumno(idalumno),
FOREIGN KEY (id_alumno_reporta) REFERENCES alumno(idalumno)
```

);
