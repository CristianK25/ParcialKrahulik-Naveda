
package model;


public class Departamento {
    private int id;
    private String descripcion;
    private int numeroPiso;
    private String numeroDepartamento;

    public Departamento() {
    }

    public Departamento(int numeroPiso, String numeroDepartamento,String descripcion) {
        this.descripcion = descripcion;
        this.numeroPiso = numeroPiso;
        this.numeroDepartamento = numeroDepartamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getDescripcion() {
        return descripcion;
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
        sb.append("\nNumero del Piso = ").append(numeroPiso);
        sb.append("\nNumero del Departamento = ").append(numeroDepartamento);
        sb.append("\nDescripcion = ").append(descripcion);
        sb.append('}');
        return sb.toString();
    }
        
}
