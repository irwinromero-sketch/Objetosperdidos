package mx.uam.cua.tysi.integracion.alumnos.dto;

import java.time.LocalDate;

public class PrestamoDTO {

    private Long id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private AlumnoDTO alumno;
    private LibroDTO libro;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public AlumnoDTO getAlumno() { return alumno; }
    public void setAlumno(AlumnoDTO alumno) { this.alumno = alumno; }

    public LibroDTO getLibro() { return libro; }
    public void setLibro(LibroDTO libro) { this.libro = libro; }
}