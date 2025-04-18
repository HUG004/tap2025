package com.example.tap2025.vistas;
import com.example.tap2025.models.tipo_categoriaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormTipo_Categoria extends Stage {
    private Button btnGuardar;
    private TextField txttipo_categoria;
    private VBox vBox;
    private Scene escena;
    private tipo_categoriaDAO objtc;
    private TableView<tipo_categoriaDAO> tbvTipo_Categoria;

    public FormTipo_Categoria(TableView<tipo_categoriaDAO> tbvTipo_Categoria, tipo_categoriaDAO obj){
        this.tbvTipo_Categoria = tbvTipo_Categoria;
        CrearUI();
        if( obj == null ){
            objtc = new tipo_categoriaDAO();
        }else{
            objtc = obj;
            txttipo_categoria.setText(objtc.getCategoria());
        }
        this.setTitle("Registrar El tipo de Categoria");
        this.setScene(escena);
        this.show();
    }
    public void CrearUI(){
        txttipo_categoria = new TextField();
        txttipo_categoria.setPromptText("Nombre del tipo de la categoria");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> {
            objtc.setCategoria(txttipo_categoria.getText());
            if( objtc.getId_tipo_categoria() > 0 )
                objtc.UPDATE();
            else
                objtc.INSERT();
            tbvTipo_Categoria.setItems(objtc.SELECT());
            tbvTipo_Categoria.refresh();
            this.close();
        });
        vBox = new VBox(txttipo_categoria, btnGuardar);
        escena = new Scene(vBox,320,150);
    }
}


