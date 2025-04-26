package com.example.tap2025.vistas;

import com.example.tap2025.models.InsumoDAO;
import com.example.tap2025.models.ProveedorDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class FormInsumo extends Stage {
    private Button btnGuardar;
    private TextField txtproveedor, txtnombre, txtcosto;
    private VBox vBox;
    private Scene escena;
    private InsumoDAO obji;
    private TableView<InsumoDAO> tbvInsumo;

    public FormInsumo(TableView<InsumoDAO> tbvInsumo, InsumoDAO obj){
        this.tbvInsumo = tbvInsumo;
        CrearUI();
        if( obj == null ){
            obji = new InsumoDAO();
        }else{
            obji = obj;
            txtproveedor.setText(String.valueOf(obji.getId_proveedor()));
            txtnombre.setText(obji.getNombre());
            txtcosto.setText(obji.getCosto().toString());
        }
        this.setTitle("Registrar el insumo");
        this.setScene(escena);
        this.show();
    }
    public void CrearUI(){
        txtproveedor = new TextField();
        txtproveedor.setPromptText("id del proveedor");
        txtnombre = new TextField();
        txtnombre.setPromptText("Nombre del insumo");
        txtcosto = new TextField();
        txtcosto.setPromptText("Costo del insumo");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> {
            obji.setId_proveedor(Integer.parseInt(txtproveedor.getText()));
            obji.setNombre(txtnombre.getText());
            obji.setCosto(new BigDecimal(txtcosto.getText()));
            if( obji.getId_insumo() > 0 )
                obji.UPDATE();
            else
                obji.INSERT();
            tbvInsumo.setItems(obji.SELECT());
            tbvInsumo.refresh();
            this.close();
        });
        vBox = new VBox(txtproveedor, txtnombre, txtcosto, btnGuardar);
        escena = new Scene(vBox,320,150);
    }
}
