
package vistas;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import javax.swing.JOptionPane;
import controlador.Controlador;
import util.VistaUtil;

public class Vista{
    //Atributos Residente
    private static Integer textoDNI;
    private static String textoNombre;
    private static String textoApellido;
    private static String textoEmail;
    //Atributos Departamentos
    private static String textoDescripcion;
    private static Integer textoNumeroPiso;
    private static String textoNumeroDepartamento;
    
    
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
    
    private static void opcionElegida(int opcion){
        switch (opcion) {
            case 0 -> {
                ingresarResidente();
            }
            case 1 -> {
                mostrarResidente();
            }
            case 2 -> {
                ingresarDepartamento();
            }
            case 3 -> {
                mostrarDepartamentos();
            }
            case 4 -> {
                mudarResidente();
            }
            case 5 -> {
                salir();
            }
        }
    }
    
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
        Controlador.mostrarResidente(textoNombre);
    }
    
    public static void ingresarDepartamento(){
        textoNumeroPiso = VistaUtil.pedirEntero("Ingrese el Número del piso");
        if (textoNumeroPiso == null) return;

        textoNumeroDepartamento = VistaUtil.pedirTexto("Ingrese el Número del departamento \n(Solo dos caracteres, ej: 1A)");
        if (textoNumeroDepartamento == null) return;

        textoDescripcion = VistaUtil.pedirTexto("Ingrese la descripción del Departamento");
        if (textoDescripcion == null) return;

        Controlador.ingresarDepartamento(textoNumeroPiso, textoNumeroDepartamento, textoDescripcion);

    }

    public static void mostrarDepartamentos(){
        textoNumeroPiso = VistaUtil.pedirEntero("Ingrese el Número del piso");
        if (textoNumeroPiso == null) return;
        textoNumeroDepartamento = VistaUtil.pedirTexto("Ingrese el Número del departamento \n(Solo dos caracteres, ej: 1A)");
        if (textoNumeroDepartamento == null) return;
        Controlador.mostrarDepartamentos(textoNumeroPiso, textoNumeroDepartamento);
    }
    
    public static void mudarResidente(){
        textoDNI = VistaUtil.pedirEntero("Ingrese el DNI del Residente a mudar");
        if (textoDNI == null) return;
        textoNumeroPiso = VistaUtil.pedirEntero("Ingrese el Número del piso para mudar al Residente");
        if (textoNumeroPiso == null) return;
        textoNumeroDepartamento = VistaUtil.pedirTexto("Ingrese el Número del departamento \n(Solo dos caracteres, ej: 1A)");
        if (textoNumeroDepartamento == null) return;
        Controlador.mudarResidente(textoDNI, textoNumeroPiso, textoNumeroDepartamento);
    }
    
    public static void salir(){
        Controlador.salir();
    }
}
