
package model;

import java.time.LocalDate;


public class Residente {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private int dni;
    private LocalDate fechaIngreso;
    private Departamento departamento;

    public Residente() {
    }

    
    public Residente(int id, String nombre, String apellido, String email, int dni, Departamento departamento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = LocalDate.now();
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public int getDni() {
        return dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{");
        sb.append("\nid = ").append(id);
        sb.append("\nNombre = ").append(nombre);
        sb.append("\nApellido = ").append(apellido);
        sb.append("\nEmail = ").append(email);
        sb.append("\nDNI = ").append(dni);
        sb.append("\nFecha de Ingreso = ").append(fechaIngreso);
        sb.append("\nDepartamento = ").append(departamento);
        sb.append('}');
        return sb.toString();
    }
    
    
}
