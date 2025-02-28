package com.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormCliente extends Stage {
    private Button btnGuardar;
    private TextField txtnombre, txtapellido,txtdireccion, txtemail, txtTel;
    private VBox vBox;
    private Scene escena;

    public FormCliente(){
        this.setTitle("Registrar Cliente");
        this.setScene(escena);
        this.show();
    }
    public void CrearUI(){
        txtnombre = new TextField();
        txtapellido = new TextField();
        txtdireccion = new TextField();
        txtemail = new TextField();
        txtTel = new TextField();
        btnGuardar = new Button("Guardar");
        vBox = new VBox(txtnombre,txtapellido,txtdireccion,txtemail,txtTel, btnGuardar);
        escena = new Scene(vBox,120,150);
    }
}
