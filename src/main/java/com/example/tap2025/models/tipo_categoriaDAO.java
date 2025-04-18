package com.example.tap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class tipo_categoriaDAO {
    private int id_tipo_categoria;
    private String categoria;

    public int getId_tipo_categoria() {
        return id_tipo_categoria;
    }

    public void setId_tipo_categoria(int id_tipo_categoria) {
        this.id_tipo_categoria = id_tipo_categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void INSERT(){
        String query = "INSERT INTO tipo_categoria(categoria)"
                + "values('"+categoria+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE tipo_categoria SET categoria = '"+categoria+"' WHERE id_tipo_categoria = "+id_tipo_categoria;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM tipo_categoria WHERE id_tipo_categoria = " +id_tipo_categoria;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static ObservableList<tipo_categoriaDAO> SELECT(){
        String query = "SELECT * FROM tipo_categoria";
        ObservableList<tipo_categoriaDAO> listaTC = FXCollections.observableArrayList();
        tipo_categoriaDAO objTC;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objTC = new tipo_categoriaDAO();
                objTC.setId_tipo_categoria(res.getInt("id_tipo_categoria"));
                objTC.setCategoria(res.getString("categoria"));
                listaTC.add(objTC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaTC;
    }
}
