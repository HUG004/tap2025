package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public void INSERT(){
        String query = "INSERT INTO reservacion_mesa(id_reservacion, id_mesa, comentarios)"
                + "values('"+id_reservacion+"', '"+id_mesa+"', '"+comentarios+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE reservacion_mesa SET id_reservacion = '"+id_reservacion+"'," +
                " id_mesa = '"+ id_mesa+"', comentarios = '"+comentarios+"' WHERE id_reservacion= "+id_reservacion + " and id_mesa = "+id_mesa;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
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
    public ObservableList<Reservacion_MesaDAO> SELECT(){
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

}
