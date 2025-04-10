package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class Detalle_ProductoDAO {
    private int id_insumo;
    private int id_producto;
    private int cantidad;

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void INSERT(){
        String query = "INSERT INTO detalle_producto(id_producto, id_insumo, cantidad)"
                + "values('"+id_producto+"','"+id_insumo+"','"+cantidad+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE detalle_producto SET id_producto = '"+id_producto+"', id_insumo = '"+id_insumo+"', cantidad = '"+cantidad+"' WHERE id_producto= "+id_producto + " and id_insumo = "+id_insumo;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM detalle_producto WHERE id_producto = " +id_producto + " and id_insumo = " +id_insumo;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ObservableList<Detalle_ProductoDAO> SELECT(){
        String query = "SELECT * FROM detalle_producto";
        ObservableList<Detalle_ProductoDAO> listadp = FXCollections.observableArrayList();
        Detalle_ProductoDAO objdp;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objdp = new Detalle_ProductoDAO();
                objdp.setId_producto(res.getInt("id_producto"));
                objdp.setId_insumo(res.getInt("id_insumo"));
                objdp.setCantidad(res.getInt("cantidad"));
                listadp.add(objdp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listadp;
    }

}
