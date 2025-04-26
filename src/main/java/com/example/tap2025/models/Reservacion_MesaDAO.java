package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Reservacion_MesaDAO {
    private int id_reservacion;
    private int id_mesa;
    private String comentarios;

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public int INSERT(){
        String query = "INSERT INTO reservacion_mesa(id_reservacion, id_mesa, comentarios)"
                + "values(?, ?, ?) ";
        try(PreparedStatement  stmt =  conexion.connection.prepareStatement(query)){

            stmt.setInt(1, this.id_reservacion);
            stmt.setInt(2,this.id_mesa);
            stmt.setString(3,this.comentarios);
            return stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public void UPDATE(){
        String query = "UPDATE reservacion_mesa SET comentarios = ? WHERE id_reservacion = ? AND id_mesa = ?";
        try (PreparedStatement stmt = conexion.connection.prepareStatement(query)) {
            stmt.setString(1, this.comentarios);
            stmt.setInt(2, this.id_reservacion);
            stmt.setInt(3, this.id_mesa);
            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DELETE(){
        String query = "DELETE FROM reservacion_mesa WHERE id_mesa = " +id_mesa + " and id_reservacion = " +id_reservacion;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static ObservableList<Reservacion_MesaDAO> SELECT(){
        String query = "SELECT * FROM reservacion_mesa";
        ObservableList<Reservacion_MesaDAO> listaRM = FXCollections.observableArrayList();
        Reservacion_MesaDAO objRM;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objRM = new Reservacion_MesaDAO();
                objRM.setId_reservacion(res.getInt("id_reservacion"));
                objRM.setId_mesa(res.getInt("id_mesa"));
                objRM.setComentarios(res.getString("comentarios"));
                listaRM.add(objRM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaRM;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservacion_MesaDAO that = (Reservacion_MesaDAO) o;
        return id_reservacion == that.id_reservacion && id_mesa == that.id_mesa;
    }

    @Override
    public int hashCode() {
        return 31 * id_reservacion + id_mesa;
    }

}
