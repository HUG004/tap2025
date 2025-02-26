package com.example.tap2025.models;

public class ClienteDAO {
    private int id_cliente;
    private String nom_cliente;
    private String apellido1;
    private String apellido2;
    private String direccion;
    private String tel_cliente;
    private String email_cliente;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }

    public void setNom_cliente(String nom_cliente) {
        this.nom_cliente = nom_cliente;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTel_cliente() {
        return tel_cliente;
    }

    public void setTel_cliente(String tel_cliente) {
        this.tel_cliente = tel_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }
    public void INSERT(){
        String query = "INSERT INTO cliente(nom_cliente,apellido1,apellido2, direccion, tel_cliente, email_cliente, )"
                + "values('"+nom_cliente+"','"+apellido1+"', '"+apellido2+"', '"+direccion+"', '"+tel_cliente+"', '"+email_cliente+"') ";
    }
    public void UPDATE(){

    }
    public void DELETE(){

    }
    public void SELECT(){

    }
}
