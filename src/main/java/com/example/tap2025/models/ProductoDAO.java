package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductoDAO {
    private int id_producto;
    private String producto;
    private BigDecimal precio;
    private int id_categoria;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    public String getProducto(){
        return  producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    public void INSERT(){
        String query = "INSERT INTO producto(producto, precio, id_categoria)"
                + "values('"+producto+"','"+precio+"','"+id_categoria+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE producto SET producto = '"+producto+"', precio = '"+precio+"', id_categoria = '"+id_categoria+"' WHERE id_producto = "+id_producto;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM producto WHERE id_producto = " +id_producto;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public  static ObservableList<ProductoDAO> SELECT(){
        String query = "SELECT * FROM producto";
        ObservableList<ProductoDAO> listap = FXCollections.observableArrayList();
        ProductoDAO objp;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objp = new ProductoDAO();
                objp.setId_producto(res.getInt("id_producto"));
                objp.setProducto(res.getString("producto"));
                objp.setPrecio(res.getBigDecimal("precio"));
                objp.setId_categoria(res.getInt("id_categoria"));
                listap.add(objp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listap;
    }

}
