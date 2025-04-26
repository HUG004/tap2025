package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
public class InsumoDAO {
    private int id_insumo;
    private int id_proveedor;
    private String nombre;
    private BigDecimal costo;

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
    public void INSERT(){
        String query = "INSERT INTO insumo(id_proveedor,nombre,costo)"
                + "values('"+id_proveedor+"','"+nombre+"','"+costo+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE insumo SET id_proveedor = '"+id_proveedor+"', nombre = '"+nombre+"', costo = '"+costo+"' WHERE id_insumo = "+id_insumo;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM insumo WHERE id_insumo = " +id_insumo;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public  static ObservableList<InsumoDAO> SELECT(){
        String query = "SELECT * FROM insumo";
        ObservableList<InsumoDAO> listaI = FXCollections.observableArrayList();
        InsumoDAO objI;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objI = new InsumoDAO();
                objI.setId_insumo(res.getInt("id_insumo"));
                objI.setId_proveedor(res.getInt("id_proveedor"));
                objI.setNombre(res.getString("nombre"));
                objI.setCosto(res.getBigDecimal("costo"));
                listaI.add(objI);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaI;
    }

}
