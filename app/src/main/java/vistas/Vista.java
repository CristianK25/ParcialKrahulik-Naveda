
package vistas;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import javax.swing.JOptionPane;
import controlador.Controlador;
import model.*;
import util.Log;
import util.VistaUtil;

public class Vista{
    //Atributos Residente
    private static Integer textoDNI;
    private static String textoNombre;
    private static String textoEmail;
    //Atributos Departamentos
    private static String textoDescripcion;
    private static Integer textoNumeroPiso;
    private static String textoNumeroDepartamento;
    
    /**
     * Un menu lanzado con JOptionPane conla elecciones que el usuario puede elegir.
     */
    public void menuPrincipal(){
        FlatCarbonIJTheme.setup();
        String[] opciones = {"Ingresar Residente",
            "Mostrar Residente",
            "Ingresar Departamento",
            "Mostrar Departamentos",
            "Mudar Residente",
            "Salir"};
        int opcion = 0;
        JOptionPane.showMessageDialog(null,
                """
                \u00a1Bienvenido al Edificio Argentino!
                Pulse aceptar para realizar consultas y transacciones""");
        do{
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Eliga una opcion",
                    "Menu Edificio",
                    0,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]); 
            opcionElegida(opcion);
        }while(opcion!=5);
    }
    
    /**
     * El metodo recibe un entero haciendo referencia a la posicion de la eleccion que el
     * usuario eligio
     * @param opcion 
     */
    private static void opcionElegida(int opcion){
        switch (opcion) {
            case 0 -> {
                Log.debug("Eleccion: \'ingresar residente\'");
                ingresarResidente();
            }
            case 1 -> {
                Log.debug("Eleccion: \'mostrar residente\'");
                mostrarResidente();
            }
            case 2 -> {
                Log.debug("Eleccion: \'ingresar departamento\'");
                ingresarDepartamento();
            }
            case 3 -> {
                Log.debug("Eleccion: \'mostrar departamentos\'");
                mostrarDepartamentos();
            }
            case 4 -> {
                Log.debug("Eleccion: \'mudar residente\'");
                mudarResidente();
            }
            case 5 -> {
                Log.debug("Eleccion: \'salir\'");
                salir();
            }
        }
    }
    
    /**
     * Se ingresan el dni, nombre y email verificando que el usuario no cancele
     * o quede vacio
     */
    public static void ingresarResidente(){
        textoDNI = VistaUtil.pedirEntero("Ingrese el DNI del Residente");
        if (textoDNI == null) return;

        textoNombre = VistaUtil.pedirTexto("Ingrese el nombre del Residente");
        if (textoNombre == null) return;

        textoEmail = VistaUtil.pedirTexto("Ingrese el email del Residente");
        if (textoEmail == null) return;
        if (Controlador.ingresarResidente(textoDNI, textoNombre, textoEmail)) 
            JOptionPane.showMessageDialog(null,"Residente Ingresado correctamente");
        else
            JOptionPane.showMessageDialog(null, "Residente no ingresado");
    }
    
    public static void mostrarResidente(){
        textoNombre = VistaUtil.pedirTexto("Ingrese el nombre del Residente a buscar");
        if (textoNombre == null) return;
        textoDNI = VistaUtil.pedirEntero("Ingrese el DNI del Residente");
        if (textoDNI == null) return;
        Residente residente = Controlador.mostrarResidente(textoNombre,textoDNI);
        Log.debug("Residente creado correctamente");
        if (residente != null) {
            JOptionPane.showMessageDialog(null, "Residente\n"+residente.toString());
            Log.debug("Residente creado correctamente");
        }
        else {
            JOptionPane.showMessageDialog(null, "El Residente no existe");
            Log.warn("Residente no encontrado");
        }
        
    }
    
    public static void ingresarDepartamento(){
        textoNumeroPiso = VistaUtil.pedirEntero("Ingrese el Número del piso");
        if (textoNumeroPiso == null) return;

        textoNumeroDepartamento = VistaUtil.pedirTexto("Ingrese el Número del departamento \n(Solo dos caracteres, ej: 1A)");
        if (textoNumeroDepartamento == null) return;

        textoDescripcion = VistaUtil.pedirTexto("Ingrese la descripción del Departamento");
        if (textoDescripcion == null) return;

        if (Controlador.ingresarDepartamento(textoNumeroPiso, textoNumeroDepartamento, textoDescripcion)){
            JOptionPane.showMessageDialog(null, "Departamento ingresado correctamente");
            Log.debug("Departamento ingresado correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Error ingresando el departamento");
            Log.warn("No se ingreso el departamento");
        }

    }

    public static void mostrarDepartamentos(){
        textoNumeroPiso = VistaUtil.pedirEntero("Ingrese el Número del piso");
        if (textoNumeroPiso == null) return;
        textoNumeroDepartamento = VistaUtil.pedirTexto("Ingrese el Número del departamento \n(Solo dos caracteres, ej: 1A)");
        if (textoNumeroDepartamento == null) return;
        
        Departamento departamento = Controlador.mostrarDepartamentos(textoNumeroPiso, textoNumeroDepartamento);
        if (departamento != null){
            JOptionPane.showMessageDialog(null, "");
            Log.debug("Departamento mostrado correctamente: ");
        }else{
            JOptionPane.showMessageDialog(null, "No se encontro el departamento");
            Log.warn("No se encontro el departamento");
        }
    }
    
    public static void mudarResidente(){
        textoDNI = VistaUtil.pedirEntero("Ingrese el DNI del Residente a mudar");
        if (textoDNI == null) return;
        textoNumeroPiso = VistaUtil.pedirEntero("Ingrese el Número del piso para mudar al Residente");
        if (textoNumeroPiso == null) return;
        textoNumeroDepartamento = VistaUtil.pedirTexto("Ingrese el Número del departamento \n(Solo dos caracteres, ej: 1A)");
        if (textoNumeroDepartamento == null) return;
        if (Controlador.mudarResidente(textoDNI, textoNumeroPiso, textoNumeroDepartamento)){
            JOptionPane.showMessageDialog(null, "Residente mudado correctamente");
            Log.debug("Residente mudado correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Error mudando al Residente");
            Log.warn("Error mudando al residente");
        }
    }
    
    public static void salir(){
        Controlador.salir();
    }
}
