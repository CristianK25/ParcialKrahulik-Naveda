
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection conn;
    private static final String url = "jdbc:h2:./Base de datos/DB_ParcialNaveda";
    private static final String user = "sa";
    private static final String password = "";
    
    /**
     * Constructor privado para eliminar la posible creacion de un objeto de clase
     */
    private DatabaseManager(){}
    
    public static void iniciarBaseDatos(){
        String tablaResidente = "CREATE TABLE IF NOT EXISTS residente("
                + "DNI INT,"
                + "nombre VARCHAR(50),"
                + "email VARCHAR(60),"
                + "fecha_ingreso DATE,"
                + "id_departamento INT,"
                + "PRIMARY KEY (DNI),"
                + "FOREIGN KEY (id_departamento) REFERENCES departamento(id)"
                + ");";
        String tablaDepartamento = "CREATE TABLE IF NOT EXISTS departamento("
                + "id INT AUTO_INCREMENT,"
                + "numero_piso INT,"
                + "numero_departamento VARCHAR(2),"
                + "descripcion VARCHAR(100),"
                + " PRIMARY KEY (id)"
                + ");";
        crearTabla(tablaDepartamento,"Departamento");
        crearTabla(tablaResidente, "Residente");
    }
    
    
    public static void crearTabla(String sql, String nombreTabla){
        try(PreparedStatement ps = DatabaseManager.obtenerConexion().prepareStatement(sql)){
            ps.executeUpdate();
            Log.info("Tabla creada correctamente: " + nombreTabla);
        }catch(SQLException e){
            Log.error("No se pudo crear la tabla", e);
        }
    }
    
    /**
     * Obtiene la conexion a la base de datos
     * @return La connexion
     */
    
    public static Connection obtenerConexion(){
        if(conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            } catch (SQLException ex) {
                Log.error("No se pudo conectar a " + url, ex);
                return null;
            }
        }else
            return conn;
    }
    
    public void iniciarDepartamentos(){
        String[][] departamentos = {
            {"1","A1","Tres ambientes"},
            {"1","A2","Dos ambientes"},
            {"1","A3","Dos ambientes"},
            {"2","B1","Tres ambientes"},
            {"2","B2","Dos ambientes"},
            {"2","B3","Dos ambientes"}
        };
        String sql = "INSERT INTO departamentos(numero_piso, numero_departamento, descripcion) VALUES (?,?,?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        for (String[] departamento : departamentos) {
            pstmt.setInt(1, Integer.parseInt(departamento[0]));  // numero
            pstmt.setString(2, departamento[1]);  // titulo
            pstmt.setString(3, departamento[2]);  // clasificacion
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        System.out.println("Libros de ejemplo insertados correctamente.");
        } catch (SQLException e) {
            Log.error("Error al insertar libros de ejemplo: ",e);
        }
    }
}