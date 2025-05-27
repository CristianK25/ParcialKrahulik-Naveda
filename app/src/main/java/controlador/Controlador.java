
package controlador;

import dao.DepartamentoDAO;
import dao.ResidenteDAO;
import java.time.LocalDate;
import model.Departamento;
import model.Residente;
import util.Log;
import vistas.Vista;

public class Controlador {
    private static Vista vista;
    private static Residente residente;
    private static Departamento departamento;
    private static ResidenteDAO residenteDAO;
    private static DepartamentoDAO departamentoDAO;
    
    /**
     * Inicia la aplicacion llamando al metodo menu principal de la vista e iniciando
     * la base de datos con sus tablas.
     */
    public static void iniciarApp(){
        residenteDAO = new ResidenteDAO();
        departamentoDAO = new DepartamentoDAO();
        vista = new Vista();
        vista.menuPrincipal();
    }
    
    public static boolean ingresarResidente(int dni, String nombre, String email){
        Residente residente = new Residente(nombre,email,dni,LocalDate.now());
        return residenteDAO.insertar(residente);
    }
    
    public static boolean mostrarResidente(String nombre){
        return true;
    }
    
    public static boolean ingresarDepartamento(int nroPiso,String desc,String nroDepartamento){
        return true;
    }

    public static boolean mostrarDepartamentos(int nroPiso,String nroDepartamento){
        return true;
    }
    
    public static boolean mudarResidente(int dni,int nroPiso,String nroDepartamento){
        return true;
    }
    
    public static void salir(){
        Log.debug("Cerrando app....");
    }
}
