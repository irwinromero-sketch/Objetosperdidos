package mx.uam.cua.tysi.integracion.alumnos.dto;

import java.time.LocalDate;

public class EntregaDTO {

    private Long id;
    private LocalDate fechaEntrega;
    private ObjetoDTO objeto;
    private AlumnoDTO alumnoReporta;
    private AlumnoDTO alumnoRecibe;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public ObjetoDTO getObjeto() { return objeto; }
    public void setObjeto(ObjetoDTO objeto) { this.objeto = objeto; }

    public AlumnoDTO getAlumnoReporta() { return alumnoReporta; }
    public void setAlumnoReporta(AlumnoDTO alumnoReporta) { this.alumnoReporta = alumnoReporta; }

    public AlumnoDTO getAlumnoRecibe() { return alumnoRecibe; }
    public void setAlumnoRecibe(AlumnoDTO alumnoRecibe) { this.alumnoRecibe = alumnoRecibe; }
}