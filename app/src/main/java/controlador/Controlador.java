
package controlador;

import dao.DepartamentoDAO;
import dao.ResidenteDAO;
import java.time.LocalDate;
import model.Departamento;
import model.Residente;
import util.DatabaseManager;
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
        Log.debug("Iniciando Base de datos....");
        DatabaseManager.iniciarBaseDatos();
        residenteDAO = new ResidenteDAO();
        departamentoDAO = new DepartamentoDAO();
        vista = new Vista();
        vista.menuPrincipal();
    }
    
    public static boolean ingresarResidente(int dni, String nombre, String email){
        return residenteDAO.insertar(new Residente(nombre,email,dni,LocalDate.now()));
    }
    
    public static Residente mostrarResidente(String nombre,int dni){
        return residenteDAO.buscar(nombre, dni);
    }
    
    public static boolean ingresarDepartamento(int nroPiso,String desc,String nroDepartamento){
        return departamentoDAO.insertar(new Departamento(nroPiso,nroDepartamento,desc));
    }

    public static Departamento mostrarDepartamentos(int nroPiso,String nroDepartamento){
        return departamentoDAO.buscar(nroDepartamento, nroPiso);
    }
    
    public static boolean mudarResidente(int dni,int nroPiso,String nroDepartamento){
        return residenteDAO.mudarResidente(residente, nroPiso, nroDepartamento);
    }
    
    public static void salir(){
        Log.debug("Cerrando app....");
    }
}
