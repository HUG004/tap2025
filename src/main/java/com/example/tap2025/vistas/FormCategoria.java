package com.example.tap2025.vistas;

import com.example.tap2025.models.CategoriaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormCategoria extends Stage {
    private Button btnGuardar;
    private TextField txttipo_categoria, txtdescripcion;
    private VBox vBox;
    private Scene escena;
    private CategoriaDAO objc;
    private TableView<CategoriaDAO> tbvCategoria;

    public FormCategoria(TableView<CategoriaDAO> tbvCategoria, CategoriaDAO obj){
        this.tbvCategoria = tbvCategoria;
        CrearUI();
        if( obj == null ){
            objc = new CategoriaDAO();
        }else{
            objc = obj;
            txttipo_categoria.setText(String.valueOf(objc.getTipo_categoria()));
            txtdescripcion.setText(objc.getDescripcion());
        }
        this.setTitle("Registrar la Categoria");
        this.setScene(escena);
        this.show();
    }
    public void CrearUI(){
        txttipo_categoria = new TextField();
        txttipo_categoria.setPromptText("id del tipo de la categoria");
        txtdescripcion = new TextField();
        txtdescripcion.setPromptText("Nombre de la categoria");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> {
            objc.setTipo_categoria(Integer.parseInt(txttipo_categoria.getText()));
            objc.setDescripcion(txtdescripcion.getText());
            if( objc.getId_categoria() > 0 )
                objc.UPDATE();
            else
                objc.INSERT();
            tbvCategoria.setItems(objc.SELECT());
            tbvCategoria.refresh();
            this.close();
        });
        vBox = new VBox(txttipo_categoria,txtdescripcion, btnGuardar);
        escena = new Scene(vBox,320,150);
    }
}
