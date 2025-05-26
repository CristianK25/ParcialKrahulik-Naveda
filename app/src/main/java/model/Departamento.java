
package model;

import java.util.ArrayList;
import java.util.List;


public class Departamento {
    private int id;
    private String descripcion;
    private int numeroPiso;
    private String numeroDepartamento;
    private List<Residente> empleados; 

    public Departamento() {
    }

    public Departamento(int id, String descripcion, int numeroPiso, String numeroDepartamento) {
        this.id = id;
        this.descripcion = descripcion;
        this.numeroPiso = numeroPiso;
        this.numeroDepartamento = numeroDepartamento;
        this.empleados = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public List<Residente> getEmpleados() {
        return empleados;
    }

    public int getNumeroPiso() {
        return numeroPiso;
    }

    public String getNumeroDepartamento() {
        return numeroDepartamento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Departamento{");
        sb.append("id = ").append(id);
        sb.append("\nDescripcion = ").append(descripcion);
        sb.append("\nNumeroPiso = ").append(numeroPiso);
        sb.append("\nNumeroDepartamento = ").append(numeroDepartamento);
        sb.append("\nEmpleados = ").append(empleados).append("\n");
        sb.append('}');
        return sb.toString();
    }
        
}
