package com.example.tap2025.vistas;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.tap2025.models.ProductoDAO;

import java.math.BigDecimal;

public class FormProducto extends Stage {
    private Button btnGuardar;
    private TextField txtproducto, txtprecio,txtcategoria;
    private VBox vBox;
    private Scene escena;
    private ProductoDAO objP;
    private TableView<ProductoDAO> tbvProducto;
    private TextField txtImagen; // NUEVO


    public FormProducto(TableView<ProductoDAO> tbvProducto, ProductoDAO obj){
        this.tbvProducto = tbvProducto;
        CrearUI();
        if( obj == null ){
            objP = new ProductoDAO();
        }else{
            objP = obj;
            txtproducto.setText(objP.getProducto());
            txtprecio.setText(objP.getPrecio().toString());
            txtcategoria.setText(String.valueOf(objP.getId_categoria()));
            txtImagen.setText(objP.getImagen());
        }
        this.setTitle("Registrar Producto");
        this.setScene(escena);
        this.show();
    }
    public void CrearUI(){
        txtproducto = new TextField();
        txtproducto.setPromptText("Nombre del producto");
        txtprecio = new TextField();
        txtprecio.setPromptText("Precio del producto");
        txtcategoria = new TextField();
        txtcategoria.setPromptText("Categoria del producto");
        txtImagen = new TextField();
        txtImagen.setPromptText("Ruta de la imagen");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> {
            objP.setProducto(txtproducto.getText());
            objP.setPrecio(new BigDecimal(txtprecio.getText()));
            objP.setId_categoria(Integer.parseInt(txtcategoria.getText()));
            objP.setImagen(txtImagen.getText());
            if( objP.getId_producto() > 0 )
                objP.UPDATE();
            else
                objP.INSERT();
            tbvProducto.setItems(objP.SELECT());
            tbvProducto.refresh();
            this.close();
        });
        vBox = new VBox(txtproducto,txtprecio,txtcategoria, txtImagen, btnGuardar);
        escena = new Scene(vBox,320,150);
    }
}
