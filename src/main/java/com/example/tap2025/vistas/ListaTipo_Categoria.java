package com.example.tap2025.vistas;
import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.ProductoDAO;
import com.example.tap2025.models.tipo_categoriaDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaTipo_Categoria extends Stage {
    private ToolBar tlbMenu;
    private TableView<tipo_categoriaDAO> tbvTipo_Categoria;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaTipo_Categoria(){
        CrearUI();
        this.setTitle("Listado de tipos de categoria");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvTipo_Categoria = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormTipo_Categoria(tbvTipo_Categoria, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvTipo_Categoria);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        tipo_categoriaDAO objtc = new tipo_categoriaDAO();
        TableColumn<tipo_categoriaDAO,String> tbcTipo_Categoria = new TableColumn<>("Nombre del Tipo de Categoria");
        tbcTipo_Categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        TableColumn<tipo_categoriaDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, tipo_categoria) -> new FormTipo_Categoria(table, tipo_categoria),
                tc -> null,
                tc -> tipo_categoriaDAO.SELECT()
        ));

        TableColumn<tipo_categoriaDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, tipo_categoria) -> {}, // No se usa en eliminar
                tipo_categoria -> {
                    tipo_categoria.DELETE();
                    return null;
                },
                c -> tipo_categoriaDAO.SELECT()
        ));
        tbvTipo_Categoria.getColumns().addAll(tbcTipo_Categoria,  tbcEditar, tbcEliminar);
        tbvTipo_Categoria.setItems(objtc.SELECT());
    }

}
