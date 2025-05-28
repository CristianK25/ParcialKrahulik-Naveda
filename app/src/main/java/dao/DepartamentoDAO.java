
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Departamento;
import util.DatabaseManager;
import util.Log;


public class DepartamentoDAO implements GenericDAO<Departamento>{

    @Override
    public boolean insertar(Departamento departamento) {
        String sql = "INSERT INTO departamento"
                + "(numero_piso, numero_departamento, descripcion)"
                + " VALUES (?,?,?)";
        try(PreparedStatement ps = DatabaseManager.obtenerConexion().prepareStatement(sql)){
            ps.setString(1,departamento.getDescripcion());
            ps.setInt(2, departamento.getNumeroPiso());
            ps.setString(3, departamento.getNumeroDepartamento());
            if(ps.executeUpdate() != 0){
                Log.debug("Datos insertados correctamente: " + departamento);
                return true;
            }else{
                Log.warn("Datos no insertados: "+ departamento);
                return false;
            }    
        }catch(SQLException e){
            Log.error("No se pudo insertar " + departamento, e);
            return false;
        }
    }


    @Override
    public Departamento buscar(String numero_departamento, int numero_piso) {
        String sql = "SELECT * FROM departamento WHERE numero_departamento = ? AND numero_piso = ?";
        try(PreparedStatement ps = DatabaseManager.obtenerConexion().prepareStatement(sql)){
            ps.setString(1, numero_departamento);
            ps.setInt(2, numero_piso);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    int nroPiso = rs.getInt("numero_piso");
                    String nroDep = rs.getString("numero_departamento");
                    String descr = rs.getString("descripcion");
                    Departamento d = new Departamento(nroPiso,nroDep,descr);
                    Log.debug("Objeto Departamento creado correctamente: "+d);
                    return d;
                }
                Log.debug("Objeto Departamento no encontrado");
                return null;
            }
        }catch(SQLException e){
            Log.error("Error buscando el nombre: "+numero_departamento, e);
            return null;
        }
    }

    @Override
    public List<Departamento> buscarTodos() {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM departamento";

        try (PreparedStatement ps = DatabaseManager.obtenerConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Departamento d = new Departamento(
                    rs.getInt("numero_piso"),
                    rs.getString("numero_departamento"),
                    rs.getString("descripcion") // <- orden correcto según tu constructor
                );
                departamentos.add(d);
                Log.debug("Departamento encontrado: Piso " + d.getNumeroPiso() + ", Nº " + d.getNumeroDepartamento());
            }

        } catch (SQLException e) {
            Log.error("Error al buscar todos los departamentos", e);
        }

        return departamentos;
    }

}
