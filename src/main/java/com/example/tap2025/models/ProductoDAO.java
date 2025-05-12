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
    private String imagen;
    private boolean activo;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

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
        String query = "INSERT INTO producto(producto, precio, id_categoria, imagen)"
                + "values('"+producto+"','"+precio+"','"+id_categoria+"', '"+imagen+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE producto SET producto = '"+producto+"', precio = '"+precio+"', id_categoria = '"+id_categoria+"', imagen = '"+imagen+"' WHERE id_producto = "+id_producto;
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

    public void DESACTIVAR() {
        String query = "UPDATE producto SET activo = FALSE WHERE id_producto = " + id_producto;
        try {
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static ObservableList<ProductoDAO> SELECT(){
        String query = "SELECT * FROM producto WHERE activo = TRUE";
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
                objp.setActivo(res.getBoolean("activo"));
                objp.setImagen(res.getString("imagen"));
                listap.add(objp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listap;
    }
    public static ObservableList<ProductoDAO> selectByCategoria(int idCategoria) {
        String query = "SELECT * FROM producto WHERE id_categoria = " + idCategoria;
        ObservableList<ProductoDAO> listaFiltrada = FXCollections.observableArrayList();
        ProductoDAO producto;
        try {
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                producto = new ProductoDAO();
                producto.setId_producto(res.getInt("id_producto"));
                producto.setProducto(res.getString("producto"));
                producto.setPrecio(res.getBigDecimal("precio"));
                producto.setId_categoria(res.getInt("id_categoria"));
                listaFiltrada.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFiltrada;
    }

    public boolean tieneRelacionEnDetalleProducto() {
        String query = "SELECT COUNT(*) FROM detalle_producto WHERE id_producto = " + id_producto;
        try {
            Statement stmt = conexion.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
