package com.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.tap2025.models.ClienteDAO;

public class FormCliente extends Stage {
    private Button btnGuardar;
    private TextField txtnombre, txtapellido,txtdireccion, txtemail, txtTel;
    private VBox vBox;
    private Scene escena;
    private ClienteDAO objC;
    private TableView<ClienteDAO> tbvClientes;

    public FormCliente(TableView<ClienteDAO> tbvClientes,ClienteDAO obj){
        this.tbvClientes = tbvClientes;
        CrearUI();
        if( obj == null ){
            objC = new ClienteDAO();
        }else{
            objC = obj;
            txtnombre.setText(objC.getNom_cliente());
            txtapellido.setText(objC.getApellido1());
            txtdireccion.setText(objC.getDireccion());
            txtemail.setText(objC.getEmail_cliente());
            txtTel.setText(objC.getTel_cliente());
        }
        this.setTitle("Registrar Cliente");
        this.setScene(escena);
        this.show();
    }
    public void CrearUI(){
        txtnombre = new TextField();
        txtnombre.setPromptText("Nombre del cliente");
        txtapellido = new TextField();
        txtapellido.setPromptText("Apellido del cliente");
        txtdireccion = new TextField();
        txtdireccion.setPromptText("Direccion del cliente");
        txtemail = new TextField();
        txtemail.setPromptText("Email del cliente");
        txtTel = new TextField();
        txtTel.setPromptText("Telefono del cliente");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> {
            objC.setNom_cliente(txtnombre.getText());
            objC.setApellido1(txtapellido.getText());
            objC.setDireccion(txtdireccion.getText());
            objC.setEmail_cliente(txtemail.getText());
            objC.setTel_cliente(txtTel.getText());
            if( objC.getId_cliente() > 0 )
                objC.UPDATE();
            else
                objC.INSERT();
            tbvClientes.setItems(objC.SELECT());
            tbvClientes.refresh();
            this.close();
        });
        vBox = new VBox(txtnombre,txtapellido,txtdireccion,txtemail,txtTel, btnGuardar);
        escena = new Scene(vBox,320,150);
    }
}
