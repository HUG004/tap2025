package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class Detalle_OrdenDAO {
    private int id_producto;
    private int id_orden;
    private int cantidad;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void INSERT(){
        String query = "INSERT INTO detalle_orden(id_producto, id_orden, cantidad)"
                + "values('"+id_producto+"','"+id_orden+"','"+cantidad+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE detalle_orden SET id_producto = '"+id_producto+"', id_orden = '"+id_orden+"', cantidad = '"+cantidad+"' WHERE id_producto= "+id_producto + " and id_orden = "+id_orden;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM detalle_orden WHERE id_producto = " +id_producto + " and id_orden = " +id_orden;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ObservableList<Detalle_OrdenDAO> SELECT(){
        String query = "SELECT * FROM detalle_orden";
        ObservableList<Detalle_OrdenDAO> listaDO = FXCollections.observableArrayList();
        Detalle_OrdenDAO objDO;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objDO = new Detalle_OrdenDAO();
                objDO.setId_producto(res.getInt("id_producto"));
                objDO.setId_orden(res.getInt("id_orden"));
                objDO.setCantidad(res.getInt("cantidad"));
                listaDO.add(objDO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaDO;
    }

}
