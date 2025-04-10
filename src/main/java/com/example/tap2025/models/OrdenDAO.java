package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

public class OrdenDAO {
    private int id_orden;
    private int id_cliente;
    private LocalDate fecha;
    private int id_mesa;
    private int id_empleado;

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDate getFecha(){
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    public void INSERT(){
        String query = "INSERT INTO orden(id_cliente, fecha, id_mesa, id_empleado)"
                + "values('"+id_cliente+"','"+fecha+"','"+id_mesa+"', '"+id_empleado+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE orden SET id_cliente = '"+id_cliente+"', fecha = '"+fecha+"', mesa = '"+id_mesa+"', empleado = '"+id_empleado+"' WHERE id_orden = "+id_orden;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM orden WHERE id_orden = " +id_orden;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ObservableList<OrdenDAO> SELECT(){
        String query = "SELECT * FROM orden";
        ObservableList<OrdenDAO> listaO = FXCollections.observableArrayList();
        OrdenDAO objO;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objO = new OrdenDAO();
                objO.setId_orden(res.getInt("id_orden"));
                // Convertir Timestamp a LocalDate
                Timestamp ts = res.getTimestamp("fecha");
                if (ts != null) {
                    objO.setFecha(ts.toLocalDateTime().toLocalDate());
                }
                objO.setId_mesa(res.getInt("id_mesa"));
                objO.setId_empleado(res.getInt("id_empleado"));
                listaO.add(objO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaO;
    }
}
