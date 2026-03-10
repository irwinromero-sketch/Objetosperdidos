package mx.uam.cua.tysi.integracion.alumnos.dto;

import java.time.LocalDate;

public class ObjetoDTO {

    private Long id;
    private String descripcion;
    private LocalDate fecha;
    private String estado;
    private AlumnoDTO alumno;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public AlumnoDTO getAlumno() { return alumno; }
    public void setAlumno(AlumnoDTO alumno) { this.alumno = alumno; }
}