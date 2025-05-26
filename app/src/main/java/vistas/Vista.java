
package vistas;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import javax.swing.JOptionPane;
import controlador.Controlador;

public class Vista {
    //Atributos Residente
    private static int textoDNI;
    private static String textoNombre;
    private static String textoApellido;
    private static String textoEmail;
    //Atributos Departamentos
    private static String textoDescripcion;
    private static int textoNumeroPiso;
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
        textoDNI = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DNI del Residente"));
        textoNombre = JOptionPane.showInputDialog("Ingrese el nombre del Residente");
        textoEmail = JOptionPane.showInputDialog("Ingrese el email del Residente");
        Controlador.ingresarResidente(textoDNI, textoNombre, textoEmail);
    }
    
    public static void mostrarResidente(){
        textoNombre = JOptionPane.showInputDialog("Ingrese el nombre del Residente a buscar");
        Controlador.mostrarResidente(textoNombre);
    }
    
    public static void ingresarDepartamento(){
        textoNumeroPiso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Numero del piso"));
        textoNumeroDepartamento = JOptionPane.showInputDialog("Ingrese el Numero del departamento \n(Solo dos caracteres, ej: 1A)");
        textoDescripcion = JOptionPane.showInputDialog("Ingrese la descripcion del Departamento");

    }

    public static void mostrarDepartamentos(){
        textoNumeroPiso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Numero del piso"));
        textoNumeroDepartamento = JOptionPane.showInputDialog("Ingrese el Numero del departamento \n(Solo dos caracteres, ej: 1A)");
    }
    
    public static void mudarResidente(){
        textoDNI = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DNI del Residente a mudar"));
        textoNumeroPiso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Numero del piso para mudar al Residente"));
        textoNumeroDepartamento = JOptionPane.showInputDialog("Ingrese el Numero del departamento \n(Solo dos caracteres, ej: 1A)");
    }
    
    public static void salir(){
        Controlador.salir();
    }
}
