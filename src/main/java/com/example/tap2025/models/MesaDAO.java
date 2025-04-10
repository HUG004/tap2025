package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class MesaDAO {
    private int id_mesa;
    private int capacidad;

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public void INSERT(){
        String query = "INSERT INTO mesa(capacidad)"
                + "values('"+capacidad+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE mesa SET capacidad = '"+capacidad+"' WHERE id_mesa = "+id_mesa;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM mesa WHERE id_mesa = " +id_mesa;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ObservableList<MesaDAO> SELECT(){
        String query = "SELECT * FROM mesa";
        ObservableList<MesaDAO> listaM = FXCollections.observableArrayList();
        MesaDAO objM;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objM = new MesaDAO();
                objM.setId_mesa(res.getInt("id_mesa"));
                objM.setCapacidad(res.getInt("capacidad"));
                listaM.add(objM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaM;
    }

}
