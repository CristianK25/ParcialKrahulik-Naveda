
package model;

import java.time.LocalDate;


public class Residente {
    private String nombre;
    private String email;
    private int dni;
    private LocalDate fechaIngreso;
    private Departamento departamento;

    public Residente() {
    }

    public Residente(String nombre, String email, int dni, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
    }

    
    
    public Residente(int dni,String nombre, String email, Departamento departamento) {
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = LocalDate.now();
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
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

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{");
        sb.append("\nDNI = ").append(dni);
        sb.append("\nNombre = ").append(nombre);
        sb.append("\nEmail = ").append(email);
        sb.append("\nFecha de Ingreso = ").append(fechaIngreso);
        sb.append("\nDepartamento = ").append(departamento);
        sb.append('}');
        return sb.toString();
    }
    
    
}
