
package vistas;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import javax.swing.JOptionPane;
import controlador.Controlador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.*;
import util.Log;
import util.VistaUtil;

@SuppressWarnings("ALL")
public final class Vista{
    //Atributos Residente
    private Integer textoDNI;
    private static String textoNombre;
    private static String textoEmail;
    //Atributos Departamentos
    private static String textoDescripcion;
    private static Integer textoNumeroPiso;
    private static String textoNumeroDepartamento;
    private List<Departamento> infoDepartamentos;

    public Vista() {
        actualizarDepartamentosList();
    }
    
    public void actualizarDepartamentosList(){
        this.infoDepartamentos = Controlador.buscarDepartamentos();
    }
    
    /**
     * Un menu lanzado con JOptionPane conla elecciones que el usuario puede elegir.
     */
    public void menuPrincipal(){
        FlatCarbonIJTheme.setup();
        String[] opciones = {"Ingresar Residente",
            "Mostrar Residente",
            "Ingresar Departamento",
            "Mostrar Departamentos",
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
        }while(opcion!=4);
    }
    
    /**
     * El metodo recibe un entero haciendo referencia a la posicion de la eleccion que el
     * usuario eligio
     * @param opcion 
     */
    private void opcionElegida(int opcion){
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
                mostrarDepartamento();
            }
            case 4 -> {
                Log.debug("Eleccion: \'salir\'");
                salir();
            }
        }
    }
    
    /**
     * Se ingresan el dni, nombre y email verificando que el usuario no cancele
     * o quede vacio
     */
    private void ingresarResidente(){
        actualizarDepartamentosList(); // ← muy importante
        textoDNI = VistaUtil.pedirEntero("Ingrese el DNI del Residente");
        if (textoDNI == null) return;

        textoNombre = VistaUtil.pedirTexto("Ingrese el nombre del Residente");
        if (textoNombre == null) return;

        textoEmail = VistaUtil.pedirTexto("Ingrese el email del Residente");
        if (textoEmail == null) return;
        
        Integer[] pisosDisponibles = getNumerosPisos();
        Integer seleccionPiso = (Integer) JOptionPane.showInputDialog(
            null,
            "Seleccione el piso",
            "Elección de Piso",
            JOptionPane.PLAIN_MESSAGE,
            null,
            pisosDisponibles, // Integer[]
            pisosDisponibles[0]);

        if (seleccionPiso == null) return;


        // Mostrar lista de números de departamento
        String[] departamentosDisponibles = getNumerosDepartamentosPorPiso(seleccionPiso);
        if (departamentosDisponibles.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay departamentos en ese piso.");
            return;
        }

        String seleccionNumero = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el número de departamento",
                "Elección de Departamento",
                JOptionPane.PLAIN_MESSAGE,
                null,
                departamentosDisponibles, // solo deptos del piso seleccionado
                null);

        if (seleccionNumero == null) return;
      
        
        Departamento dp = Controlador.mostrarDepartamento(seleccionPiso, seleccionNumero);
         
        if (Controlador.ingresarResidente(textoDNI, textoNombre, textoEmail, dp)) 
            JOptionPane.showMessageDialog(null,"Residente Ingresado correctamente");
        else
            JOptionPane.showMessageDialog(null, "Residente no ingresado");
    }
    
    private void mostrarResidente(){
        textoDNI = VistaUtil.pedirEntero("Ingrese el DNI del Residente");
        if (textoDNI == null) return;
        textoNombre = VistaUtil.pedirTexto("Ingrese el nombre del Residente a buscar");
        if (textoNombre == null) return;
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
    
    private void ingresarDepartamento(){
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

    private void mostrarDepartamento(){
        textoNumeroPiso = VistaUtil.pedirEntero("Ingrese el Número del piso");
        if (textoNumeroPiso == null) return;
        textoNumeroDepartamento = VistaUtil.pedirTexto("Ingrese el Número del departamento \n(Solo dos caracteres, ej: 1A)");
        if (textoNumeroDepartamento == null) return;
        
        Departamento departamento = Controlador.mostrarDepartamento(textoNumeroPiso, textoNumeroDepartamento);
        if (departamento != null){
            JOptionPane.showMessageDialog(null, departamento.toString());
            Log.debug("Departamento mostrado correctamente: ");
        }else{
            JOptionPane.showMessageDialog(null, "No se encontro el departamento");
            Log.warn("No se encontro el departamento");
        }
    }
    
    
    public static void salir(){
        Controlador.salir();
    }
    
    private String[] getNumerosDepartamentosPorPiso(int pisoSeleccionado) {
        List<String> lista = new ArrayList<>();
        for (Departamento d : infoDepartamentos) {
            if (d.getNumeroPiso() == pisoSeleccionado) {
                lista.add(d.getNumeroDepartamento());
            }
        }
        return lista.toArray(new String[0]);
    }

   

    private Integer[] getNumerosPisos() {
        Set<Integer> set = new HashSet<>();
        for (Departamento d : infoDepartamentos) {
          set.add(d.getNumeroPiso());
        }
        return set.toArray(new Integer[0]);
    }
}
