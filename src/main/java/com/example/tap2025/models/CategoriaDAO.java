package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriaDAO {
    private int id_categoria;
    private int tipo_categoria;
    private String descripcion;
    private boolean activo;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getTipo_categoria() {
        return tipo_categoria;
    }

    public void setTipo_categoria(int tipo_categoria) {
        this.tipo_categoria = tipo_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void INSERT(){
        String query = "INSERT INTO categoria(tipo_categoria,descripcion)"
                + "values('"+tipo_categoria+"','"+descripcion+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE categoria SET tipo_categoria = '"+tipo_categoria+"', descripcion = '"+descripcion+"' WHERE id_categoria = "+id_categoria;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM categoria WHERE id_categoria = " +id_categoria;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void DESACTIVAR() {
        String query = "UPDATE categoria SET activo = FALSE WHERE id_categoria = " + id_categoria;
        try {
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<CategoriaDAO> SELECT(){
        String query = "SELECT * FROM categoria WHERE activo = TRUE";
        ObservableList<CategoriaDAO> listaCa = FXCollections.observableArrayList();
        CategoriaDAO objCa;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objCa = new CategoriaDAO();
                objCa.setId_categoria(res.getInt("id_categoria"));
                objCa.setTipo_categoria(res.getInt("tipo_categoria"));
                objCa.setDescripcion(res.getString("descripcion"));
                objCa.setActivo(res.getBoolean("activo"));
                listaCa.add(objCa);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaCa;
    }
}
