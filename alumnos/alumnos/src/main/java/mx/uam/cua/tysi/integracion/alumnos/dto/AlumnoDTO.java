package mx.uam.cua.tysi.integracion.alumnos.dto;

public class AlumnoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String carrera;
    private Double promedio;
    private String email;

    // GETTERS Y SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public Double getPromedio() { return promedio; }
    public void setPromedio(Double promedio) { this.promedio = promedio; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
