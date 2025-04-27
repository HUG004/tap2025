package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.CategoriaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
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
                    categoria.DELETE();
                    return null;
                },
                c -> CategoriaDAO.SELECT()
        ));
        tbvCategoria.getColumns().addAll(tbcDescripcion,  tbcEditar, tbcEliminar);
        tbvCategoria.setItems(objc.SELECT());
    }

}
