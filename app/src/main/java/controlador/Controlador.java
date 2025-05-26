
package controlador;

import model.Departamento;
import model.Residente;
import util.Log;
import vistas.Vista;

public class Controlador {
    private static Vista vista;
    private static Residente residente;
    private static Departamento departamento;
    
    /**
     * Inicia la aplicacion llamando al metodo menu principal de la vista e iniciando
     * la base de datos con sus tablas.
     */
    public static void iniciarApp(){
        vista = new Vista();
        vista.menuPrincipal();
    }
    
    public static void ingresarResidente(int dni, String nombre, String email){
        
    }
    
    public static void mostrarResidente(String nombre){
        
    }
    
    public static void ingresarDepartamento(int nroPiso,String desc,String nroDepartamento){
        
    }

    public static void mostrarDepartamentos(int nroPiso,String nroDepartamento){
        
    }
    
    public static void mudarResidente(int dni,int nroPiso,String nroDepartamento){
        
    }
    
    public static void salir(){
        Log.debug("Cerrando app....");
    }
}
