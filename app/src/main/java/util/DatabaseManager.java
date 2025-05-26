
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String url = "jdbc:h2:./Base de datos/db";
    private static final String user = "";
    private static final String password = "";
    
    private DatabaseManager(){}
    public static void iniciarBaseDatos(){
        String tablaResidente = "CREATE TABLE IF NOT EXISTS residente("
                + "DNI INT,"
                + "nombre VARCHAR(50),"
                + "apellido VARCHAR(50),"
                + "email VARCHAR(60),"
                + "fecha_ingreso DATE,"
                + "PRIMARY KEY(DNI)"
                + ");";
        String tablaDepartamento = "CREATE TABLE IF NOT EXISTS departamento("
                + "idDepartamento INT AUTO_INCREMENT,"
                + "descripcion VARCHAR(100),"
                + "numero_piso INT,"
                + "numero_departamento VARCHAR(2),"
                + "PRIMARY KEY (idDepartamento)"
                + ");";
        String tablaResidenteDepartamento = "CREATE TABLE IF NOT EXISTS residente_departamento("
                + "DNI INT,"
                + "idDepartamento INT,"
                + "PRIMARY KEY (DNI,idDepartamento),"
                + "FOREIGN KEY (DNI) REFERENCES residente(DNI),"
                + "FOREIGN KEY (idDepartamento) REFERENCES departamento(idDepartamento)"
                + ");";
        crearTabla(tablaResidente, "Residente");
        crearTabla(tablaDepartamento,"Departamento");
        crearTabla(tablaResidenteDepartamento,"residente_departamento");
    }
    
    public static void crearTabla(String sql, String nombreTabla){
        try(PreparedStatement ps = DatabaseManager.obtenerConexion().prepareStatement(sql)){
            ps.executeUpdate();
            Log.debug("Tabla creada correctamente: " + nombreTabla);
        }catch(SQLException e){
            Log.error("No se pudo crear la tabla", e);
        }
    }
    public static Connection obtenerConexion(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Log.error("No se pudo conectar a " + url, ex);
            return null;
        }
    } 
}
