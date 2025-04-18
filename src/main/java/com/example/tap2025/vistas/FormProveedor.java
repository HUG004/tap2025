package com.example.tap2025.vistas;

import com.example.tap2025.models.ProveedorDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormProveedor extends Stage {
    private Button btnGuardar;
    private TextField txtnombre,txtTelefono, txtdireccion, txtemail, txtnota;
    private VBox vBox;
    private Scene escena;
    private ProveedorDAO objpr;
    private TableView<ProveedorDAO> tbvProveedor;

    public FormProveedor(TableView<ProveedorDAO> tbvProveedor, ProveedorDAO obj){
        this.tbvProveedor = tbvProveedor;
        CrearUI();
        if( obj == null ){
            objpr = new ProveedorDAO();
        }else{
            objpr = obj;
            txtnombre.setText(objpr.getNombre());
            txtTelefono.setText(objpr.getTelefono());
            txtdireccion.setText(objpr.getDireccion());
            txtemail.setText(objpr.getEmail());
            txtnota.setText(objpr.getNota());
        }
        this.setTitle("Registrar el proveedor");
        this.setScene(escena);
        this.show();
    }
    public void CrearUI(){
        txtnombre = new TextField();
        txtnombre.setPromptText("Nombre del proveedor");
        txtTelefono = new TextField();
        txtTelefono.setPromptText("Telefono proveedor");
        txtdireccion = new TextField();
        txtdireccion.setPromptText("direccion proveedor");
        txtemail = new TextField();
        txtemail.setPromptText("email proveedor");
        txtnota = new TextField();
        txtnota.setPromptText("nota del proveedor");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> {
            objpr.setNombre(txtnombre.getText());
            objpr.setTelefono(txtTelefono.getText());
            objpr.setDireccion(txtdireccion.getText());
            objpr.setEmail(txtemail.getText());
            objpr.setNota(txtnota.getText());
            if( objpr.getId_proveedor() > 0 )
                objpr.UPDATE();
            else
                objpr.INSERT();
            tbvProveedor.setItems(objpr.SELECT());
            tbvProveedor.refresh();
            this.close();
        });
        vBox = new VBox(txtnombre,txtTelefono, txtdireccion,txtemail,txtnota, btnGuardar);
        escena = new Scene(vBox,320,150);
    }
}
