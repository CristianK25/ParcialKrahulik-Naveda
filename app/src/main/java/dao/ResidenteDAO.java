
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Residente;
import util.DatabaseManager;
import util.Log;


public class ResidenteDAO implements GenericDAO<Residente>{

    @Override
    public boolean insertar(Residente r) {
        String sql = "INSERT INTO residente "
                + "(DNI,nombre,email,fecha_ingreso,numero_piso,numero_departamento) "
                + "VALUES (?,?,?,?,?,?)";
        try(PreparedStatement ps = DatabaseManager.obtenerConexion().prepareStatement(sql)){
            ps.setInt(1, r.getDni());
            ps.setString(2,r.getNombre());
            ps.setString(3,r.getEmail());
            ps.setDate(4,Date.valueOf(r.getFechaIngreso()));
            ps.setInt(5,r.getDepartamento().getNumeroPiso());
            ps.setString(6,r.getDepartamento().getNumeroDepartamento());
            if(ps.executeUpdate() != 0){
                Log.debug("Datos insertados correctamente: " + r);
                return true;
            }else{
                Log.warn("Datos no insertados: " + r);
                return false;
            }
        }catch(SQLException e){
            Log.error("No se pudo insertar el Residente ", e);
            return false;
        }
    }

    @Override
    public Residente buscar(String nombre, int dni) {
            String sql = "SELECT * FROM residente WHERE nombre = ? AND DNI = ?";
        try (PreparedStatement ps = DatabaseManager.obtenerConexion().prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setInt(2, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Residente r = new Residente(
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getInt("DNI"),
                    rs.getDate("fecha_ingreso").toLocalDate()
                    );
                    return r;
                }
            }
        } catch (SQLException e) {
            Log.error("Error al buscar residente por nombre y DNI", e);
        }

        return null;
    }

    @Override
    public List<Residente> buscarTodos() {
            List<Residente> residentes = new ArrayList<>();
        String sql = "SELECT * FROM residente";
        try (PreparedStatement ps = DatabaseManager.obtenerConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {        
            while (rs.next()) {
                Residente r = new Residente(
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getInt("DNI"),
                rs.getDate("fecha_ingreso").toLocalDate()
                );
                residentes.add(r);
                Log.debug("Residente encontrado: "+ r.getDni());
            }
        } catch (SQLException e) {
            Log.error("Error al buscar todos los residentes", e);
        }
        return residentes;
    }



}
