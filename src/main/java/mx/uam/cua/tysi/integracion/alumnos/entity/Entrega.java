package mx.uam.cua.tysi.integracion.alumnos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "id_objeto", referencedColumnName = "id", nullable = false)
    private Objeto objeto;

    @ManyToOne
    @JoinColumn(name = "id_alumno_reporta", referencedColumnName = "id", nullable = false)
    private Alumno alumnoReporta;

    @ManyToOne
    @JoinColumn(name = "id_alumno_recibe", referencedColumnName = "id", nullable = false)
    private Alumno alumnoRecibe;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public Objeto getObjeto() { return objeto; }
    public void setObjeto(Objeto objeto) { this.objeto = objeto; }

    public Alumno getAlumnoReporta() { return alumnoReporta; }
    public void setAlumnoReporta(Alumno alumnoReporta) { this.alumnoReporta = alumnoReporta; }

    public Alumno getAlumnoRecibe() { return alumnoRecibe; }
    public void setAlumnoRecibe(Alumno alumnoRecibe) { this.alumnoRecibe = alumnoRecibe; }
}