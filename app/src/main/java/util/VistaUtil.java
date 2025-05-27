
package util;

import javax.swing.JOptionPane;


public class VistaUtil {
    public static String pedirTexto(String mensaje) {
        String input = JOptionPane.showInputDialog(mensaje);
        if (input == null || input.trim().isEmpty()) {
            return null; // usuario canceló o dejó vacío
        }
        return input.trim();
    }

    public static Integer pedirEntero(String mensaje) {
        while (true) {
            String input = JOptionPane.showInputDialog(mensaje);
            if (input == null || input.trim().isEmpty()) {
                return null;
            }
            try {
                return Integer.valueOf(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            }
        }
    }
}

