
package vistas;

import javax.swing.JOptionPane;


public class Vista {
    public void menuPrincipal(){
        String[] opciones = {"Ingresar Residente",
            "Mostrar Residente",
            "Ingresar Departamento",
            "Mostrar Departamentos",
            "Mover Residente",
            "Salir"};
        int opcion = 0;
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
        }while(opcion!=5);
    }
    
    public void ingresarResidente(){
        
    }
    
    public void eliminarResidente(){
        
    }
    
    public void ingresarDepartamento(){
        
    }
    
    public void mostrarInquilinos(){
        
    }
}
