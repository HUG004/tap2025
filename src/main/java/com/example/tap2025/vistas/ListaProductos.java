package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaProductos extends Stage {
    private ToolBar tlbMenu;
    private TableView<ProductoDAO> tbvProducto;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaProductos(){
        CrearUI();
        this.setTitle("Listado de Productos");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvProducto = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormProducto(tbvProducto, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu,tbvProducto);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        ProductoDAO objP = new ProductoDAO();
        TableColumn<ProductoDAO,String> tbcNombre = new TableColumn<>("Nombre del producto");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("producto"));
        TableColumn<ProductoDAO,String> tbcPrecio = new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TableColumn<ProductoDAO,String> tbcCategoria = new TableColumn<>("Categoria");
        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
        TableColumn<ProductoDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, producto) -> new FormProducto(table, producto),
                c -> null,
                c -> ProductoDAO.SELECT()
        ));

        TableColumn<ProductoDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, producto) -> {}, // No se usa en eliminar
                cliente -> {
                    cliente.DELETE();
                    return null;
                },
                c -> ProductoDAO.SELECT()
        ));
        tbvProducto.getColumns().addAll(tbcNombre, tbcPrecio, tbcCategoria,  tbcEditar, tbcEliminar);
        tbvProducto.setItems(objP.SELECT());
    }

}
