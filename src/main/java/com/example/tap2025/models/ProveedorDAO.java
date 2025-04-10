package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProveedorDAO {
    private int id_proveedor;
    private String nombre;
    private String telefono;
    private String direccion;
    private String email;
    private String nota;

    public int getId_proveedor() {return id_proveedor;}

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void INSERT(){
        String query = "INSERT INTO proveedor(nombre,telefono,direccion,email,nota)"
                + "values('"+nombre+"','"+telefono+"','"+direccion+"','"+email+"','"+nota+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE proveedor SET nombre = '"+nombre+"', telefono = '"+telefono+"', direccion = '"+direccion+"', email = '"+email+"', nota = '"+nota+"' WHERE id_proveedor = "+id_proveedor;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM proveedor WHERE id_proveedor = " +id_proveedor;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ObservableList<ProveedorDAO> SELECT(){
        String query = "SELECT * FROM proveedor";
        ObservableList<ProveedorDAO> listaPr = FXCollections.observableArrayList();
        ProveedorDAO objPr;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objPr = new ProveedorDAO();
                objPr.setId_proveedor(res.getInt("id_proveedor"));
                objPr.setNombre(res.getString("nombre"));
                objPr.setTelefono(res.getString("telefono"));
                objPr.setDireccion(res.getString("direccion"));
                objPr.setEmail(res.getString("email"));
                objPr.setNota(res.getString("nota"));
                listaPr.add(objPr);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaPr;
    }
}
