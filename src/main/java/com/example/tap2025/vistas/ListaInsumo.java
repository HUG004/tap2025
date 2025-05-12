package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.InsumoDAO;
import com.example.tap2025.models.ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaInsumo extends Stage {
    private ToolBar tlbMenu;
    private TableView<InsumoDAO> tbvInsumo;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaInsumo(){
        CrearUI();
        this.setTitle("Listado de Insumos");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvInsumo = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormInsumo(tbvInsumo, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvInsumo);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        InsumoDAO objI = new InsumoDAO();
        TableColumn<InsumoDAO,String> tbcProveedor = new TableColumn<>("id del proveedor");
        tbcProveedor.setCellValueFactory(new PropertyValueFactory<>("id_proveedor"));
        TableColumn<InsumoDAO,String> tbcnombre = new TableColumn<>("Nombre del insumo");
        tbcnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<InsumoDAO,String> tbcCosto = new TableColumn<>("Costo");
        tbcCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
        TableColumn<InsumoDAO,String> tbcActivo = new TableColumn<>("Activo");
        tbcActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        TableColumn<InsumoDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, insumo) -> new FormInsumo(table, insumo),
                c -> null,
                c -> InsumoDAO.SELECT()
        ));

        TableColumn<InsumoDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, insumo) -> {},
                insumo -> {
                    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmacion.setTitle("Confirmar eliminación");
                    confirmacion.setHeaderText("¿Está seguro que desea eliminar este producto?");
                    confirmacion.setContentText("Este producto se desactivará, pero no se eliminará de la base de datos.");

                    confirmacion.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    insumo.DESACTIVAR();
                                    tbvInsumo.setItems(InsumoDAO.SELECT());
                                }
                    });
                    return null;
                },
                c -> InsumoDAO.SELECT()
        ));
        tbvInsumo.getColumns().addAll(tbcProveedor, tbcnombre, tbcCosto,tbcActivo,  tbcEditar, tbcEliminar);
        tbvInsumo.setItems(objI.SELECT());
    }

}
