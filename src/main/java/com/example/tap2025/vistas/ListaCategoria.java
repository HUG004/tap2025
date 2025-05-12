package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.CategoriaDAO;
import com.example.tap2025.models.ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaCategoria extends Stage {
    private ToolBar tlbMenu;
    private TableView<CategoriaDAO> tbvCategoria;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaCategoria(){
        CrearUI();
        this.setTitle("Listado de Categorias");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvCategoria = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormCategoria(tbvCategoria, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvCategoria);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        CategoriaDAO objc = new CategoriaDAO();
        TableColumn<CategoriaDAO,String> tbcCategoria = new TableColumn<>("id del tipo de Categoria");
        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("tipo_categoria"));
        TableColumn<CategoriaDAO,String> tbcDescripcion = new TableColumn<>("Nombre de la Categoria");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TableColumn<CategoriaDAO,String> tbcActivo = new TableColumn<>("Activo");
        tbcActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));

        TableColumn<CategoriaDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, categoria) -> new FormCategoria(table, categoria),
                tc -> null,
                tc -> CategoriaDAO.SELECT()
        ));

        TableColumn<CategoriaDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, tipo_categoria) -> {},
                categoria -> {
                    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmacion.setTitle("Confirmar eliminación");
                    confirmacion.setHeaderText("¿Está seguro que desea eliminar esta categoria?");
                    confirmacion.setContentText("Esta categoria se desactivará, pero no se eliminará de la base de datos.");

                    confirmacion.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            categoria.DESACTIVAR();
                            tbvCategoria.setItems(CategoriaDAO.SELECT());
                        }
                    });
                    return null;
                },
                c -> CategoriaDAO.SELECT()
        ));
        tbvCategoria.getColumns().addAll(tbcDescripcion, tbcActivo ,tbcEditar, tbcEliminar);
        tbvCategoria.setItems(objc.SELECT());
    }

}
